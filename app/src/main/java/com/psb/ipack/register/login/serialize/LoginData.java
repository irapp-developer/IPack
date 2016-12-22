
package com.psb.ipack.register.login.serialize;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class LoginData implements Serializable, Parcelable
{

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Message")
    @Expose
    private String message;
    public final static Creator<LoginData> CREATOR = new Creator<LoginData>() {


        @SuppressWarnings({
            "unchecked"
        })
        public LoginData createFromParcel(Parcel in) {
            LoginData instance = new LoginData();
            instance.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.message = ((String) in.readValue((String.class.getClassLoader())));
            return instance;
        }

        public LoginData[] newArray(int size) {
            return (new LoginData[size]);
        }

    }
    ;
    private final static long serialVersionUID = 4407952206865283945L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public LoginData() {
    }

    /**
     * 
     * @param message
     * @param status
     */
    public LoginData(Integer status, String message) {
        super();
        this.status = status;
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
    }

    public int describeContents() {
        return  0;
    }

}
