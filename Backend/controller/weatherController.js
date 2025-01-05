import weatherService from '../services/weatherService.js';
import axios from 'axios';

export const getCurrentWeather = async (req,res) =>{
    const { q } = req.query;

    if(!q){
        return res.status(400).json({
            error : "City parameter (q) is required"
        });
    }

    try{
        const weatherData = await weatherService.getCurrentWeather(q);
        res.status(200).json(weatherData);
    }catch(error){
        res.status(500).json({
            error : "Failed to fetch weather data"
        });
    }
}

export const getWeatherForecast = async (req,res) =>{
    const { q, days } = req.query;

    if(!q || !days){
        return res.status(400).json({
            error : "City parameter (q) and days are required"
        });
    }

    try{
        const weatherData = await weatherService.getWeatherForecast(q,days);
        res.status(200).json(weatherData);
    }catch(error){
        res.status(500).json({
            error : "Failed to fetch weather forecast data"
        });
    }
}

export const searchLocations = async (req,res) =>{
    const { q } = req.query;

    if(!q){
        return res.status(400).json({
            error : "City parameter (q) is required"
        });
    }

    try{
        const weatherData = await weatherService.searchWeather(q);
        res.status(200).json(weatherData);
    }catch(error){
        res.status(500).json({
            error : "Failed to fetch weather search data"
        });
    }
}
 