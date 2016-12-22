package com.psb.ipack.splash.serializ;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mehdi on 11/30/16.
 */

public class ResponseDeviceInfo {

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     *
     */
    public ResponseDeviceInfo() {
    }

    /**
     *
     * @param message
     * @param status
     */
    public ResponseDeviceInfo(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     *
     * @return
     * The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The Status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     *
     * @return
     * The message
     */
    public String getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The Message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
