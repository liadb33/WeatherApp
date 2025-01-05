import express from 'express';
import { getCurrentWeather, getWeatherForecast, searchLocations } from '../controller/weatherController.js';


const router = express.Router();
// defines a simple API endpoint
router.get('/',getCurrentWeather);
router.get('/forecast',getWeatherForecast);
router.get('/search',searchLocations);

export default router;