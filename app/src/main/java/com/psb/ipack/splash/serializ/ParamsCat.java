package com.psb.ipack.splash.serializ;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mehdi on 12/1/16.
 */

public class ParamsCat {

    @SerializedName("SecurityCode")
    @Expose
    private String securityCode;
    @SerializedName("UserId")
    @Expose
    private Integer userId;
    @SerializedName("UpdateTime")
    @Expose
    private String updateTime;

    /**
     * No args constructor for use in serialization
     *
     */
    public ParamsCat() {
    }

    /**
     *
     * @param securityCode
     * @param updateTime
     * @param userId
     */
    public ParamsCat(String securityCode, Integer userId, String updateTime) {
        this.securityCode = securityCode;
        this.userId = userId;
        this.updateTime = updateTime;
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
     * The userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     *
     * @param userId
     * The UserId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     *
     * @return
     * The updateTime
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     *
     * @param updateTime
     * The UpdateTime
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

}
