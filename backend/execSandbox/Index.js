const express = require('express');
const route  = require('./routes/routes');
const cors = require("cors");
const app = express();
const PORT = process.env.PORT || 5001

app.use(express.json());
app.use(express.urlencoded({extended: true}));
app.use("/", route);
app.use(
    cors({
      origin: ["http://localhost:3000"],
      methods: ["GET", "POST", "PUT", "DELETE"],
      credentials: true,
    })
  );
app.listen(PORT, () =>{

	console.log("Server listening on port 5001!");
});