const mongoose  = require('mongoose');

const problemSchema = new mongoose.Schema({
  title: {
    type: String,
    unique: true,
  },
  desc: {
    type: String, 
  },
  difficulty: {
    type: String, 
  }, 
  topic: {
    type: String,
  }, 
  authorId: {
    type: String, 
  }
});


module.exports = mongoose.model('problem', problemSchema);