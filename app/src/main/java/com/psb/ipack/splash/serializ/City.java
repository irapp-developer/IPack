
package com.psb.ipack.splash.serializ;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class City implements Serializable, Parcelable
{

    @SerializedName("CityId")
    @Expose
    private Integer cityId;
    @SerializedName("CityName")
    @Expose
    private String cityName;
    @SerializedName("ModiFiedOn")
    @Expose
    private String modiFiedOn;
    @SerializedName("ProvinceId")
    @Expose
    private Integer provinceId;
    public final static Creator<City> CREATOR = new Creator<City>() {


        @SuppressWarnings({
            "unchecked"
        })
        public City createFromParcel(Parcel in) {
            City instance = new City();
            instance.cityId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.cityName = ((String) in.readValue((String.class.getClassLoader())));
            instance.modiFiedOn = ((String) in.readValue((String.class.getClassLoader())));
            instance.provinceId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            return instance;
        }

        public City[] newArray(int size) {
            return (new City[size]);
        }

    }
    ;
    private final static long serialVersionUID = 4715417721973299820L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public City() {
    }

    /**
     * 
     * @param provinceId
     * @param modiFiedOn
     * @param cityId
     * @param cityName
     */
    public City(Integer cityId, String cityName, String modiFiedOn, Integer provinceId) {
        super();
        this.cityId = cityId;
        this.cityName = cityName;
        this.modiFiedOn = modiFiedOn;
        this.provinceId = provinceId;
    }

    /**
     * 
     * @return
     *     The cityId
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 
     * @param cityId
     *     The CityId
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 
     * @return
     *     The cityName
     */
    public String getCityName() {
        return cityName;
    }

    /**
     * 
     * @param cityName
     *     The CityName
     */
    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    /**
     * 
     * @return
     *     The modiFiedOn
     */
    public String getModiFiedOn() {
        return modiFiedOn;
    }

    /**
     * 
     * @param modiFiedOn
     *     The ModiFiedOn
     */
    public void setModiFiedOn(String modiFiedOn) {
        this.modiFiedOn = modiFiedOn;
    }

    /**
     * 
     * @return
     *     The provinceId
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * 
     * @param provinceId
     *     The ProvinceId
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(cityId);
        dest.writeValue(cityName);
        dest.writeValue(modiFiedOn);
        dest.writeValue(provinceId);
    }

    public int describeContents() {
        return  0;
    }

}
