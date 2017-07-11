package co.example.leo.coolweather.db;

import org.litepal.crud.DataSupport;

/**
 * Created by Leo on 2017/7/10.
 */

public class City extends DataSupport {

    private int id;

    private String cityName;

    private int cityCode;
    private int a;

    private int provinceId;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getCityCode() {
        return cityCode;
    }

    public void setCityCode(int cityCode) {
        this.cityCode = cityCode;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
