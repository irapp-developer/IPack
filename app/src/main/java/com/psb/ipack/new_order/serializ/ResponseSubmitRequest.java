
package com.psb.ipack.new_order.serializ;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseSubmitRequest {

    @SerializedName("Data")
    @Expose
    public Data data;
    @SerializedName("Status")
    @Expose
    public Integer status;
    @SerializedName("Message")
    @Expose
    public String message;
    @SerializedName("IsOK")
    @Expose
    public Boolean isOK;

}
