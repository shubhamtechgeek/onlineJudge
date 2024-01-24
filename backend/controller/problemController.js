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

const updateProblem = async (req, res) => {
    const { title, desc, difficulty, topic } = req.body;

    try {
        // Check if the required fields are provided
        if (!title || !desc || !difficulty || !topic) {
            return res.status(400).json({ error: "Please provide all the problem details!!!" });
        }

        // Check if the problem with the given title exists
        const existingProblem = await Problem.findOne({ title });

        if (!existingProblem) {
            return res.status(404).json({ error: "Problem not found!" });
        }

        // Update the existing problem
        const updatedProblem = await Problem.findOneAndUpdate(
            { title },
            { $set: { desc, difficulty, topic } },
            { new: true }
        );

        res.status(200).json({
            message: "Problem successfully updated!",
            problem: updatedProblem,
        });
    } catch (error) {
        console.error("Error updating problem:", error);
        res.status(500).json({ error: "Internal Server Error" });
    }
};


const deleteProblem = async (req, res) => {
    const { title } = req.body;

    try {
        // Check if the required fields are provided
        if (!title) {
            return res.status(400).json({ error: "Please provide the problem title!" });
        }

        // Check if the problem with the given title exists
        const existingProblem = await Problem.findOne({ title });

        if (!existingProblem) {
            return res.status(404).json({ error: "Problem not found!" });
        }

        // Delete the existing problem
        await Problem.deleteOne({ title });

        res.status(200).json({
            message: "Problem successfully deleted!",
        });
    } catch (error) {
        console.error("Error deleting problem:", error);
        res.status(500).json({ error: "Internal Server Error" });
    }
};


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