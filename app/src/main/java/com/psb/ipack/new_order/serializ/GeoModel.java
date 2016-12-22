package com.psb.ipack.new_order.serializ;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by mehdi on 12/10/16.
 */

public class GeoModel implements Serializable {

    @SerializedName("Latitude")
    @Expose
    public Double latitude;
    @SerializedName("Longitude")
    @Expose
    public Double longitude;

    /**
     * No args constructor for use in serialization
     */
    public GeoModel() {
    }

    /**
     * @param longitude
     * @param latitude
     */
    public GeoModel(Double latitude, Double longitude) {
        super();
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public GeoModel(LatLng point) {
        super();
        this.latitude = point.latitude;
        this.longitude = point.longitude;
    }
}
