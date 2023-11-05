const express = require('express');
const cors = require("cors");
const { dbConnect } = require('./database/db');
const User = require('./models/User.js');
const app = express();
const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const cookieParser = require("cookie-parser");


const PORT = process.env.PORT || 5000;
dbConnect();
app.use(
    cors({
      origin: ["http://localhost:3000"],
      methods: ["GET", "POST", "PUT", "DELETE"],
      credentials: true,
    })
  );
//Middlewares
app.use(express.json());
app.use(express.urlencoded({extended: true}));
app.use(cookieParser());


  app.listen(PORT, () => console.log("Server is up and running: " + PORT));




app.get("/", async (req, res)=>{
    res.send("Hello World");
});

app.post("/signup", async (req, res) => {
    try{

    //get all the data from the frontend
    const {email, username, password} = req.body; 

    //check that all the data should exits 
    if((!username && !email && !password)){
        return res.status(400).send("Please enter all the information.");
    }

    //check if user is already exists
    const existingUser = await User.findOne({username})
    if(existingUser){
        return res.status(200).send("User already exists")
    }

    //encrypt the password
    const hashedPassword = await bcrypt.hash(password, 10);

    //save the user in the db
    const user = await User.create({ 
        username, 
        email, 
        password: hashedPassword 
    });

    
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
        const {email, password} = req.body;

        //check if user entered all required data
        if((!email && !password)){
            return res.status(400).send("Username or Password is missing!!!");
        }
        
        //find the user(if available)
        const user = await User.findOne({email});
        if(!user){
            return res.status(404).send("User Not Found!!!");

        }

        //match the password
        const enteredPassword = await bcrypt.compare(password, user.password);
        if(!enteredPassword){
            return res.status(400).send("Password is incorrect.");
        }

        //generate a token for the user and share it. 
    const token = jwt.sign({id: user._id, email}, process.env.SECRET_KEY, {expiresIn: "1h"});
    user.token = token;
    user.password = undefined;

        //store cookies
        const options = {
            expires: new Date(Date.now() + 1*24*60*60*1000),
            httpOnly: true, //only manipulate by server not by your frontend/user
        };

        res.status(200).cookie("token", token, options).json({
            message: "You have succesfully logged in!",
            success: true,
            token,
            withCredentials: true
        });
        //send the token


    }catch(error){
        console.log(error.message)
    }



}
);
//need to implement logout as well
