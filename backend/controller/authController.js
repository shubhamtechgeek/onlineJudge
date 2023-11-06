const bcrypt = require('bcryptjs');
const jwt = require('jsonwebtoken');
const User = require('../models/User');
var store = require('store')


const login = async (req, res) => {
    try{
        //get user data
        const {email, password} = req.body;

        //check if user entered all required data
        if((!email && !password)){
            return res.status(400).send("Email or Password is missing!!!");
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
        store.set('email', {name: user.email});
        res.status(200).cookie("token", token, user.email, options).json({
            message: "You have succesfully logged in!",
            success: true,
            token,
            email,
            withCredentials: true
            
        });
        //send the token


    }catch(error){
        console.log(error.message)
    }
}

const signup = async (req, res) => {
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
};

module.exports = {login, signup};