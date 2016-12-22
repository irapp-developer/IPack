
package com.psb.ipack.new_order.select_map.serializ;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Distance {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("value")
    @Expose
    private Integer value;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Distance() {
    }

    /**
     * 
     * @param text
     * @param value
     */
    public Distance(String text, Integer value) {
        super();
        this.text = text;
        this.value = value;
    }

    /**
     * 
     * @return
     *     The text
     */
    public String getText() {
        return text;
    }

    /**
     * 
     * @param text
     *     The text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 
     * @return
     *     The value
     */
    public Integer getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(Integer value) {
        this.value = value;
    }

}
