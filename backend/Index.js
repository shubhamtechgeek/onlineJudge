const express = require('express');
const cors = require("cors");
const { dbConnect } = require('./database/db');

const app = express();

const cookieParser = require("cookie-parser");
const route = require('./routes/routes');


const PORT = process.env.PORT || 5000;
dbConnect();
app.use(
    cors({
      origin: ["http://localhost:3000"],
      methods: ["GET", "POST", "PUT", "DELETE"],
      credentials: true,
    })
  );
//Middlewares
app.use(express.json());
app.use(express.urlencoded({extended: true}));
app.use(cookieParser());
app.listen(PORT, () => console.log("Server is up and running: " + PORT));
app.use("/", route);

