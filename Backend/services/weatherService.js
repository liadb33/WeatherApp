import axios from 'axios';

const WEATHER_API_KEY = '48a3a275b57547bfab2120228240712';
const WEATHER_API_BASE_URL = 'https://api.weatherapi.com/v1';

class WeatherService{
    async getCurrentWeather(city){
        try{
            const response = await axios.get(`${WEATHER_API_BASE_URL}/current.json?key=${WEATHER_API_KEY}&q=${city}`);
            return response.data;
        }catch(error){
            throw new Error('Failed to fetch weather data');
        }
    }

    async getWeatherForecast(city,days){
        try{
            const response = await axios.get(`${WEATHER_API_BASE_URL}/forecast.json?key=${WEATHER_API_KEY}&q=${city}&days=${days}`);
            return response.data;
        }catch(error){
           throw new Error('Failed to fetch forecast data');;
        }
    }

    async searchWeather(city){
        try{
            const response = await axios.get(`${WEATHER_API_BASE_URL}/search.json?key=${WEATHER_API_KEY}&q=${city}`);
            return response.data;
        }catch(error){
            throw new Error('Failed to fetch search data');;
        }
    }
}

export default new WeatherService();