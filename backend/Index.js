const express = require('express');
const { dbConnect } = require('./database/db');
const User = require('./models/User.js');
const app = express();
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');


// app.use(express().json);
const PORT = process.env.PORT || 5000;
dbConnect();

//Middlewares
app.use(express.json());
app.use(express.urlencoded({extended: true}));

app.listen(PORT, () => console.log("Server is up and running"));

app.get("/", (req, res)=>{
    res.send("Hello World");
});

app.post("/register", async (req, res) => {
    try{

    //get all the data from the frontend
    const {firstName, lastName, userName, email, password} = req.body; 

    //check that all the data should exits 
    if(!(firstName && lastName && userName && email && password)){
        return res.status(400).send("Please enter all the information.");
    }

    //check if user is already exists
    const existingUser = await User.findOne({userName})
    if(existingUser){
        return res.status(200).send("User already exists")
    }

    //encrypt the password
    const hashedPassword = await bcrypt.hash(password, 10);

    //save the user in the db
    const user = await User.create({
        firstName, 
        lastName, 
        userName, 
        email, 
        password: hashedPassword 
    });

    //generate a token for the user and share it. 
    const token = jwt.sign({id: user._id, userName}, process.env.SECRET_KEY, {expiresIn: "1h"});
    user.token = token;
    user.password = undefined;
    res.status(200).json({
        message: "You Have Successfully Registred!",
        user
    });
     

    }catch (error){
        console.log(error.message);
    }
});

app.post("/login", async (req, res) => {

    try{
        //get user data
        const {userName, password} = req.body;

        //check if user entered all required data
        if(!(userName && password)){
            return res.status(400).send("Username or Password is missing!!!");
        }
        
        //find the user(if available)
        const user = await User.findOne({userName});
        if(!user){
            return res.status(404).send("User Not Found!!!");

        }

        //match the password
        const enteredPassword = await bcrypt.compare(password, user.password);
        if(!enteredPassword){
            return res.status(400).send("Password is incorrect.");
        }

        //store cookies


        //send the token
    }catch(error){

    // }
}});
//need to implement logout as well
