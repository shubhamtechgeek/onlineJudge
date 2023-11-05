const mongoose = require("mongoose");

const userSchema = new mongoose.Schema({
  
  username: {
    type: String,
    unique: true
  },
  email: {
    type: String,
    unique: true
  },
  password: {
    type: String
  },
  //changes
});

module.exports = mongoose.model("user", userSchema);
