
package com.psb.ipack.register.login.serialize;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ResponseLogin implements Serializable, Parcelable
{

    @SerializedName("LoginData")
    @Expose
    private LoginData data;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("IsOK")
    @Expose
    private Boolean isOK;
    public final static Creator<ResponseLogin> CREATOR = new Creator<ResponseLogin>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ResponseLogin createFromParcel(Parcel in) {
            ResponseLogin instance = new ResponseLogin();
            instance.data = ((LoginData) in.readValue((LoginData.class.getClassLoader())));
            instance.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.message = ((String) in.readValue((String.class.getClassLoader())));
            instance.isOK = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            return instance;
        }

        public ResponseLogin[] newArray(int size) {
            return (new ResponseLogin[size]);
        }

    }
    ;
    private final static long serialVersionUID = 3369323227340105486L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseLogin() {
    }

    /**
     * 
     * @param message
     * @param isOK
     * @param status
     * @param data
     */
    public ResponseLogin(LoginData data, Integer status, String message, Boolean isOK) {
        super();
        this.data = data;
        this.status = status;
        this.message = message;
        this.isOK = isOK;
    }

    public LoginData getData() {
        return data;
    }

    public void setData(LoginData data) {
        this.data = data;
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

    public Boolean getIsOK() {
        return isOK;
    }

    public void setIsOK(Boolean isOK) {
        this.isOK = isOK;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(data);
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeValue(isOK);
    }

    public int describeContents() {
        return  0;
    }

}
