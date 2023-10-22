const express = require('express');
const { route } = require('./routes/routes');

const app = express();


app.use(express.json());
app.use(express.urlencoded({extended: true}));
app.use('/', route);

app.listen(process.env.PORT, () =>{

	console.log("Server listening on port 5001!");
});