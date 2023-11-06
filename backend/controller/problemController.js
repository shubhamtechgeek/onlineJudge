const Problem = require("../models/Problem");
var store = require('store')

const addProblem = async (req, res) => {

    const {title, desc, difficulty, topic} = req.body;
   
    if(!title || !desc || !difficulty || !topic){
        return res.status(400).send("Please provide all the problem Details!!!");
    }
    const existingProblem = await Problem.findOne({title});
    if(existingProblem){
        return res.status(200).send("Problem Found!!!");
    }

    const problem = await Problem.create({ 
        title, 
        desc, 
        difficulty,
        topic,
    });

    res.status(200).json({
        message: "Problem Successfully Added!",
        problem
    });
}


const getAllProblems = async (req, res) => {

    
    
    const problemList = await Problem.find();
    console.log(problemList);
    if(problemList){
        return res.status(200).send(problemList);
    }

    const problem = await Problem.create({ 
        title, 
        desc, 
        difficulty,
        topic,
        authorId: store.get('email')
    });

    res.status(200).json({
        message: "Problem Successfully Added!",
        problem
    });
}




module.exports = {addProblem, getAllProblems};