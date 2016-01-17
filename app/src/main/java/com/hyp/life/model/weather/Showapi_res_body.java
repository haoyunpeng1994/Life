package com.hyp.life.model.weather;

/**
 * Created by acer on 2015/12/6.
 */
public class Showapi_res_body {
    private CityInfo cityInfo;

    private F1 f1;

    private F2 f2;

    private F3 f3;

    private Now now;

    private int ret_code;

    private String time;

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public void setCityInfo(CityInfo cityInfo) {
        this.cityInfo = cityInfo;
    }

    public F1 getF1() {
        return f1;
    }

    public void setF1(F1 f1) {
        this.f1 = f1;
    }

    public F2 getF2() {
        return f2;
    }

    public void setF2(F2 f2) {
        this.f2 = f2;
    }

    public F3 getF3() {
        return f3;
    }

    public void setF3(F3 f3) {
        this.f3 = f3;
    }

    public Now getNow() {
        return now;
    }

    public void setNow(Now now) {
        this.now = now;
    }

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Showapi_res_body{" +
                "cityInfo=" + cityInfo +
                ", f1=" + f1 +
                ", f2=" + f2 +
                ", f3=" + f3 +
                ", now=" + now +
                ", ret_code=" + ret_code +
                ", time='" + time + '\'' +
                '}';
    }
}
