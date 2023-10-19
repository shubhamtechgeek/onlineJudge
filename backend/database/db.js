const mongoose = require('mongoose');
require("dotenv").config();
//changes
const dbConnect = async () => {
    const MONGO_URL = process.env.MONGO_URL;
   

    try{
        await mongoose.connect(MONGO_URL, {useNewUrlParser : true});
        console.log("Database Connection Established");
    }catch(error){
        console.log("Error while connecting to db ", error.message)
    }

}

module.exports = { dbConnect };