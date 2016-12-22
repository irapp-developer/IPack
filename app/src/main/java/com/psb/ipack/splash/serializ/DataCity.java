
package com.psb.ipack.splash.serializ;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class DataCity implements Serializable, Parcelable
{

    @SerializedName("ProvinceId")
    @Expose
    private Integer provinceId;
    @SerializedName("ProvinceName")
    @Expose
    private String provinceName;
    @SerializedName("Cities")
    @Expose
    private List<City> cities = null;
    @SerializedName("ModifiedOn")
    @Expose
    private String modifiedOn;
    public final static Creator<DataCity> CREATOR = new Creator<DataCity>() {


        @SuppressWarnings({
            "unchecked"
        })
        public DataCity createFromParcel(Parcel in) {
            DataCity instance = new DataCity();
            instance.provinceId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.provinceName = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.cities, (City.class.getClassLoader()));
            instance.modifiedOn = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public DataCity[] newArray(int size) {
            return (new DataCity[size]);
        }

    }
    ;
    private final static long serialVersionUID = -8750373808429088406L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public DataCity() {
    }

    /**
     * 
     * @param provinceId
     * @param modifiedOn
     * @param cities
     * @param provinceName
     */
    public DataCity(Integer provinceId, String provinceName, List<City> cities, String modifiedOn) {
        super();
        this.provinceId = provinceId;
        this.provinceName = provinceName;
        this.cities = cities;
        this.modifiedOn = modifiedOn;
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

    /**
     * 
     * @return
     *     The provinceName
     */
    public String getProvinceName() {
        return provinceName;
    }

    /**
     * 
     * @param provinceName
     *     The ProvinceName
     */
    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    /**
     * 
     * @return
     *     The cities
     */
    public List<City> getCities() {
        return cities;
    }

    /**
     * 
     * @param cities
     *     The Cities
     */
    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    /**
     * 
     * @return
     *     The modifiedOn
     */
    public String getModifiedOn() {
        return modifiedOn;
    }

    /**
     * 
     * @param modifiedOn
     *     The ModifiedOn
     */
    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(provinceId);
        dest.writeValue(provinceName);
        dest.writeList(cities);
        dest.writeValue(modifiedOn);
    }

    public int describeContents() {
        return  0;
    }

}
