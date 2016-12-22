package com.psb.ipack.new_order.select_map.serializ;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mehdi on 12/7/16.
 */

public class ParamsRoute {

    @SerializedName("origin")
    @Expose
    private String origin;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("sensor")
    @Expose
    private Boolean sensor;
    @SerializedName("units")
    @Expose
    private String units;
    @SerializedName("mode")
    @Expose
    private String mode;

    /**
     * No args constructor for use in serialization
     *
     */
    public ParamsRoute() {
    }

    /**
     *
     * @param sensor
     * @param origin
     * @param units
     * @param mode
     * @param destination
     */
    public ParamsRoute(String origin, String destination, Boolean sensor, String units, String mode) {
        super();
        this.origin = origin;
        this.destination = destination;
        this.sensor = sensor;
        this.units = units;
        this.mode = mode;
    }

    /**
     *
     * @return
     * The origin
     */
    public String getOrigin() {
        return origin;
    }

    /**
     *
     * @param origin
     * The Origin
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     *
     * @return
     * The destination
     */
    public String getDestination() {
        return destination;
    }

    /**
     *
     * @param destination
     * The destination
     */
    public void setDestination(String destination) {
        this.destination = destination;
    }

    /**
     *
     * @return
     * The sensor
     */
    public Boolean getSensor() {
        return sensor;
    }

    /**
     *
     * @param sensor
     * The sensor
     */
    public void setSensor(Boolean sensor) {
        this.sensor = sensor;
    }

    /**
     *
     * @return
     * The units
     */
    public String getUnits() {
        return units;
    }

    /**
     *
     * @param units
     * The units
     */
    public void setUnits(String units) {
        this.units = units;
    }

    /**
     *
     * @return
     * The mode
     */
    public String getMode() {
        return mode;
    }

    /**
     *
     * @param mode
     * The mode
     */
    public void setMode(String mode) {
        this.mode = mode;
    }

}
