
package com.psb.ipack.new_order.select_map.serializ;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GeocodedWaypoint {

    @SerializedName("geocoder_status")
    @Expose
    private String geocoderStatus;
    @SerializedName("place_id")
    @Expose
    private String placeId;
    @SerializedName("types")
    @Expose
    private List<String> types = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public GeocodedWaypoint() {
    }

    /**
     * 
     * @param geocoderStatus
     * @param placeId
     * @param types
     */
    public GeocodedWaypoint(String geocoderStatus, String placeId, List<String> types) {
        super();
        this.geocoderStatus = geocoderStatus;
        this.placeId = placeId;
        this.types = types;
    }

    /**
     * 
     * @return
     *     The geocoderStatus
     */
    public String getGeocoderStatus() {
        return geocoderStatus;
    }

    /**
     * 
     * @param geocoderStatus
     *     The geocoder_status
     */
    public void setGeocoderStatus(String geocoderStatus) {
        this.geocoderStatus = geocoderStatus;
    }

    /**
     * 
     * @return
     *     The placeId
     */
    public String getPlaceId() {
        return placeId;
    }

    /**
     * 
     * @param placeId
     *     The place_id
     */
    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }

    /**
     * 
     * @return
     *     The types
     */
    public List<String> getTypes() {
        return types;
    }

    /**
     * 
     * @param types
     *     The types
     */
    public void setTypes(List<String> types) {
        this.types = types;
    }

}
