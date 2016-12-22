
package com.psb.ipack.new_order.select_map.serializ;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OverviewPolyline {

    @SerializedName("points")
    @Expose
    private String points;

    /**
     * No args constructor for use in serialization
     * 
     */
    public OverviewPolyline() {
    }

    /**
     * 
     * @param points
     */
    public OverviewPolyline(String points) {
        super();
        this.points = points;
    }

    /**
     * 
     * @return
     *     The points
     */
    public String getPoints() {
        return points;
    }

    /**
     * 
     * @param points
     *     The points
     */
    public void setPoints(String points) {
        this.points = points;
    }

}
