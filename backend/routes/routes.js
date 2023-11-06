const express = require('express');
const { login, signup } = require('../controller/authController');
const { addProblem, getAllProblems } = require('../controller/problemController');

const router = express.Router();

router.route("/login").post(login);

router.route("/signup").post(signup);

router.route("/addProblem").post(addProblem)

router.route("/getAllProblems").get(getAllProblems)

module.exports = router;