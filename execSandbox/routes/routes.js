const express = require('express');
const {judgeHome, judgeExec } = require('../controller/judge');
const cors = require('cors'); // Import the cors middleware

const router = express.Router();

router.route("/hello").get(judgeHome);

router.route("/run").post(judgeExec);

module.exports = router;