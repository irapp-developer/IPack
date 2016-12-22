package com.psb.ipack.splash.serializ;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by mehdi on 11/30/16.
 */

public class ParamsDeviceInfo {

    @SerializedName("SecurityCode")
    @Expose
    private String securityCode;
    @SerializedName("FCMToken")
    @Expose
    private String fCMToken;
    @SerializedName("ApplicationMode")
    @Expose
    private Integer applicationMode;
    @SerializedName("Location")
    @Expose
    private ParamsLocation location;

    /**
     * No args constructor for use in serialization
     *
     */
    public ParamsDeviceInfo() {
    }

    /**
     *
     * @param securityCode
     * @param applicationMode
     * @param location
     * @param fCMToken
     */
    public ParamsDeviceInfo(String securityCode, String fCMToken, Integer applicationMode, ParamsLocation location) {
        this.securityCode = securityCode;
        this.fCMToken = fCMToken;
        this.applicationMode = applicationMode;
        this.location = location;
    }

    /**
     *
     * @return
     * The securityCode
     */
    public String getSecurityCode() {
        return securityCode;
    }

    /**
     *
     * @param securityCode
     * The SecurityCode
     */
    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    /**
     *
     * @return
     * The fCMToken
     */
    public String getFCMToken() {
        return fCMToken;
    }

    /**
     *
     * @param fCMToken
     * The FCMToken
     */
    public void setFCMToken(String fCMToken) {
        this.fCMToken = fCMToken;
    }

    /**
     *
     * @return
     * The applicationMode
     */
    public Integer getApplicationMode() {
        return applicationMode;
    }

    /**
     *
     * @param applicationMode
     * The ApplicationMode
     */
    public void setApplicationMode(Integer applicationMode) {
        this.applicationMode = applicationMode;
    }

    /**
     *
     * @return
     * The location
     */
    public ParamsLocation getLocation() {
        return location;
    }

    /**
     *
     * @param location
     * The Location
     */
    public void setLocation(ParamsLocation location) {
        this.location = location;
    }

}
