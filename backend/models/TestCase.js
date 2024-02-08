const mongoose = require("mongoose");

const testCaseSchema = new mongoose.Schema({
    problemId: {
        type: mongoose.Schema.Types.ObjectId,
        ref: "problem",
    },
    input: {
        type: String,
        required: true
    },
    expectedOutput: {
        type: String,
        required: true
    }
});

const TestCase = mongoose.model("Testcase", testCaseSchema);

module.exports = TestCase;