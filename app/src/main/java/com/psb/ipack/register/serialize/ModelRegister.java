package com.psb.ipack.register.serialize;

/**
 * Created by mehdi on 12/12/16.
 */


import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ModelRegister implements Serializable, Parcelable
{

    @SerializedName("Status")
    @Expose
    private Integer status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("IsOK")
    @Expose
    private Boolean isOK;
    public final static Creator<ModelRegister> CREATOR = new Creator<ModelRegister>() {


        @SuppressWarnings({
                "unchecked"
        })
        public ModelRegister createFromParcel(Parcel in) {
            ModelRegister instance = new ModelRegister();
            instance.status = ((Integer) in.readValue((Integer.class.getClassLoader())));
            instance.message = ((String) in.readValue((String.class.getClassLoader())));
            instance.isOK = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
            return instance;
        }

        public ModelRegister[] newArray(int size) {
            return (new ModelRegister[size]);
        }

    }
            ;
    private final static long serialVersionUID = -7687214364119493835L;

    /**
     * No args constructor for use in serialization
     *
     */
    public ModelRegister() {
    }

    /**
     *
     * @param message
     * @param isOK
     * @param status
     */
    public ModelRegister(Integer status, String message, Boolean isOK) {
        super();
        this.status = status;
        this.message = message;
        this.isOK = isOK;
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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(message);
        dest.writeValue(isOK);
    }

    public int describeContents() {
        return 0;
    }

}
