package co.example.leo.coolweather.gson;

/**
 * Created by Leo on 2017/7/11.
 */

public class AQI {

    public AQICity city;

    public class AQICity{
        public String aqi;
        public String pm25;
    }
}
