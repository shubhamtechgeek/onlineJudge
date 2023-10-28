const express = require('express');
const {judgeHome, judgeExec } = require('../controller/judge');
const router = express.Router();

router.route("/hello").get(judgeHome);

router.route("/run").get(judgeExec);

module.exports = router;