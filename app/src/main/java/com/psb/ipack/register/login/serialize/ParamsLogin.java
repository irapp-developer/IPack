
package com.psb.ipack.register.login.serialize;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ParamsLogin implements Serializable, Parcelable
{

    @SerializedName("UserName")
    @Expose
    private String userName;
    @SerializedName("Password")
    @Expose
    private String password;
    @SerializedName("SecurityCode")
    @Expose
    private String securityCode;
    @SerializedName("AppMode")
    @Expose
    private Integer appMode;
    public final static Creator<ParamsLogin> CREATOR = new Creator<ParamsLogin>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ParamsLogin createFromParcel(Parcel in) {
            ParamsLogin instance = new ParamsLogin();
            instance.userName = ((String) in.readValue((String.class.getClassLoader())));
            instance.password = ((String) in.readValue((String.class.getClassLoader())));
            instance.securityCode = ((String) in.readValue((String.class.getClassLoader())));
            instance.appMode = ((Integer) in.readValue((Integer.class.getClassLoader())));
            return instance;
        }

        public ParamsLogin[] newArray(int size) {
            return (new ParamsLogin[size]);
        }

    }
    ;
    private final static long serialVersionUID = -8227995322842666822L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ParamsLogin() {
    }

    /**
     * 
     * @param securityCode
     * @param appMode
     * @param userName
     * @param password
     */
    public ParamsLogin(String userName, String password, String securityCode, Integer appMode) {
        super();
        this.userName = userName;
        this.password = password;
        this.securityCode = securityCode;
        this.appMode = appMode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public Integer getAppMode() {
        return appMode;
    }

    public void setAppMode(Integer appMode) {
        this.appMode = appMode;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(userName);
        dest.writeValue(password);
        dest.writeValue(securityCode);
        dest.writeValue(appMode);
    }

    public int describeContents() {
        return  0;
    }

}
