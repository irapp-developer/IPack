package com.psb.ipack.splash.serializ;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mehdi on 11/30/16.
 */

public class ParamsLocation {

    @SerializedName("Latitude")
    @Expose
    private Double latitude;
    @SerializedName("Longitude")
    @Expose
    private Double longitude;

    /**
     * No args constructor for use in serialization
     *
     */
    public ParamsLocation() {
    }

    /**
     *
     * @param longitude
     * @param latitude
     */
    public ParamsLocation(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The Latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The Longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
