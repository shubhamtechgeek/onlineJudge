const express = require('express');
const route  = require('./routes/routes');
const cors = require("cors");
const cookieParser = require("cookie-parser");


const app = express();
const PORT = process.env.PORT || 5001

app.use(cors());

app.use(express.json());
app.use(express.urlencoded({extended: true}));
app.use(cookieParser());
app.listen(PORT, () => console.log("Server is up and running: " + PORT));
app.use("/", route);