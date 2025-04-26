import {WeatherLocation} from './weather-location';
import {WeatherCurrent} from './weather-current';
import {WeatherForecast} from './weather-forecast';

export interface WeatherResponse {
  location: WeatherLocation,
  current: WeatherCurrent,
  forecast: WeatherForecast
}
