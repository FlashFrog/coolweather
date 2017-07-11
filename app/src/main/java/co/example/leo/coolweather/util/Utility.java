package co.example.leo.coolweather.util;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import co.example.leo.coolweather.db.City;
import co.example.leo.coolweather.db.County;
import co.example.leo.coolweather.db.Province;
import co.example.leo.coolweather.gson.Weather;

import static android.content.ContentValues.TAG;

/**
 * Created by Leo on 2017/7/10.
 */

public class Utility {
    /*
     * 解析和处理服务器返回的省级数据
     */

    public static boolean handleProvinceResponse(String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allProvinces = new JSONArray(response);
                for(int i=0; i<allProvinces.length(); i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
     * 解析和处理服务器返回的市级数据
     */

    public static boolean handleCityResponse(String response, int provinceId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONArray allCities = new JSONArray(response);
                for(int i=0; i<allCities.length(); i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
     * 解析和处理服务器返回的县级数据
     */
    public static boolean handleCountyResponse(String response, int cityId){
        if(!TextUtils.isEmpty(response)){
            try{
                Log.d(TAG, "handleCountyResponse: ");
                JSONArray allCounties = new JSONArray(response);
                for(int i=0; i<allCounties.length(); i++){
                    JSONObject countryObject = allCounties.getJSONObject(i);
                    County country = new County();
                    country.setCountyName(countryObject.getString("name"));
                    country.setWeatherId(countryObject.getString("weather_id"));
                    country.setCityId(cityId);
                    Log.d(TAG, "handleCountyResponse: " + country.getCountyName());
                    country.save();
                }
                return true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return false;
    }

    /*
     * 将返回的JSON数据解析成Weather 实体类
     */
    public static Weather handleWeatherResponse(String response){
        try{
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
