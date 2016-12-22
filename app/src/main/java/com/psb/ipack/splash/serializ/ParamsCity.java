
package com.psb.ipack.splash.serializ;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ParamsCity implements Serializable, Parcelable
{

    @SerializedName("SecurityCode")
    @Expose
    private String securityCode;
    @SerializedName("UpdateTime")
    @Expose
    private String updateTime;
    public final static Creator<ParamsCity> CREATOR = new Creator<ParamsCity>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ParamsCity createFromParcel(Parcel in) {
            ParamsCity instance = new ParamsCity();
            instance.securityCode = ((String) in.readValue((String.class.getClassLoader())));
            instance.updateTime = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public ParamsCity[] newArray(int size) {
            return (new ParamsCity[size]);
        }

    }
    ;
    private final static long serialVersionUID = 5264951133708344342L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ParamsCity() {
    }

    /**
     * 
     * @param securityCode
     * @param updateTime
     */
    public ParamsCity(String securityCode, String updateTime) {
        super();
        this.securityCode = securityCode;
        this.updateTime = updateTime;
    }

    /**
     * 
     * @return
     *     The securityCode
     */
    public String getSecurityCode() {
        return securityCode;
    }

    /**
     * 
     * @param securityCode
     *     The SecurityCode
     */
    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    /**
     * 
     * @return
     *     The updateTime
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 
     * @param updateTime
     *     The UpdateTime
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(securityCode);
        dest.writeValue(updateTime);
    }

    public int describeContents() {
        return  0;
    }

}
