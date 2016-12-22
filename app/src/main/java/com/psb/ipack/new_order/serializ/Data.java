
package com.psb.ipack.new_order.serializ;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {


    @SerializedName("ServerRequestId")
    @Expose
    private Integer serverRequestId;
    @SerializedName("SubmitTime")
    @Expose
    private String submitTime;

    /**
     * No args constructor for use in serialization
     *
     */
    public Data() {
    }

    /**
     *
     * @param serverRequestId
     * @param submitTime
     */
    public Data(Integer serverRequestId, String submitTime) {
        super();
        this.serverRequestId = serverRequestId;
        this.submitTime = submitTime;
    }

    /**
     *
     * @return
     * The serverRequestId
     */
    public Integer getServerRequestId() {
        return serverRequestId;
    }

    /**
     *
     * @param serverRequestId
     * The ServerRequestId
     */
    public void setServerRequestId(Integer serverRequestId) {
        this.serverRequestId = serverRequestId;
    }

    /**
     *
     * @return
     * The submitTime
     */
    public String getSubmitTime() {
        return submitTime;
    }

    /**
     *
     * @param submitTime
     * The SubmitTime
     */
    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }


}
