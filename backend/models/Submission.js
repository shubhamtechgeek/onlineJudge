const mongoose = require("mongoose");

const submissionSchema = new mongoose.Schema({
    problemId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "problem",
        required: true
    },
    userId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "User",
        required: true
    },
    language: {
        type: String,
        enum: ["CPP", "JAVA"],
        required: true
    },
    status: {
        type: String,
        enum: ["ACCEPTED", "WRONG_ANSWER", "TLE", "RUNTIME_ERROR", "COMPILATION_ERROR"],
        required: true
    },
    code: {
        type: String,
        required: true
    },
    submissionTime: {
        type: Date,
        default: Date.now
    },
    testCasesPassed: {
        type: Number
    }
});

const Submission = mongoose.model("Submission", submissionSchema);

module.exports = Submission;
