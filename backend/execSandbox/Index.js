const express = require('express');
const route  = require('./routes/routes');

const app = express();
const PORT = process.env.PORT || 5001

app.use(express.json());
app.use(express.urlencoded({extended: true}));
app.use("/", route);

app.listen(PORT, () =>{

	console.log("Server listening on port 5001!");
});