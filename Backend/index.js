import express from 'express';
import axios from 'axios';
import weatheRoute from './routes/weatherRoute.js';


const app = express();
const port = 3000;

app.use(express.json())
app.use('/weather',weatheRoute)


app.listen(port,() =>{
    console.log(`Server listening on port ${port}`);
});