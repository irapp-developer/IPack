package com.psb.ipack.splash.serializ;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mehdi on 12/1/16.
 */

public class ResponseCat {

    @SerializedName("Data")
    @Expose
    private List<ResponseCatData> data = new ArrayList<ResponseCatData>();
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Message")
    @Expose
    private Object message;
    @SerializedName("IsOK")
    @Expose
    private Boolean isOK;

    /**
     *
     * @return
     * The data
     */
    public List<ResponseCatData> getData() {
        return data;
    }

    /**
     *
     * @param data
     * The Data
     */
    public void setData(List<ResponseCatData> data) {
        this.data = data;
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
    public Object getMessage() {
        return message;
    }

    /**
     *
     * @param message
     * The Message
     */
    public void setMessage(Object message) {
        this.message = message;
    }

    /**
     *
     * @return
     * The isOK
     */
    public Boolean getIsOK() {
        return isOK;
    }

    /**
     *
     * @param isOK
     * The IsOK
     */
    public void setIsOK(Boolean isOK) {
        this.isOK = isOK;
    }

}
