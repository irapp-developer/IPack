
package com.psb.ipack.splash.serializ;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResponseCity implements Serializable, Parcelable
{

    @SerializedName("Data")
    @Expose
    private List<DataCity> data = null;
    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("IsOK")
    @Expose
    private Boolean isOK;
    public final static Creator<ResponseCity> CREATOR = new Creator<ResponseCity>() {


        @SuppressWarnings({
            "unchecked"
        })
        public ResponseCity createFromParcel(Parcel in) {
            ResponseCity instance = new ResponseCity();
            in.readList(instance.data, (DataCity.class.getClassLoader()));
            instance.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.message = ((String) in.readValue((String.class.getClassLoader())));
            instance.isOK = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            return instance;
        }

        public ResponseCity[] newArray(int size) {
            return (new ResponseCity[size]);
        }

    }
    ;
    private final static long serialVersionUID = -2349861095621716487L;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ResponseCity() {
    }

    /**
     * 
     * @param message
     * @param isOK
     * @param status
     * @param data
     */
    public ResponseCity(List<DataCity> data, Integer status, String message, Boolean isOK) {
        super();
        this.data = data;
        this.status = status;
        this.message = message;
        this.isOK = isOK;
    }

    /**
     * 
     * @return
     *     The data
     */
    public List<DataCity> getData() {
        return data;
    }

    /**
     * 
     * @param data
     *     The LoginData
     */
    public void setData(List<DataCity> data) {
        this.data = data;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The Status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The Message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 
     * @return
     *     The isOK
     */
    public Boolean getIsOK() {
        return isOK;
    }

    /**
     * 
     * @param isOK
     *     The IsOK
     */
    public void setIsOK(Boolean isOK) {
        this.isOK = isOK;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(data);
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeValue(isOK);
    }

    public int describeContents() {
        return  0;
    }

}
