package com.psb.ipack.new_order.fill_descr;

import com.psb.ipack.new_order.serializ.OrderSerialize;

/**
 * Created by mehdi on 12/9/16.
 */

public class M_fill_descr {
    private iP_fill_descr ip_fill_descr;
    private OrderSerialize orderSerialize;

    public M_fill_descr(iP_fill_descr ip_fill_descr) {
        this.ip_fill_descr = ip_fill_descr;
        this.orderSerialize=ip_fill_descr.getOrderSerialize();
    }

    public String getName() {
        return orderSerialize.getFirstName();
    }

    public void setName(String name) {
        orderSerialize.setFirstName(name);
    }

    public String getPhoneNumber() {
        return orderSerialize.getPhoneNumber();
    }

    public void setPhoneNumber(String phoneNumber) {
        orderSerialize.setPhoneNumber(phoneNumber);
    }

    public String getAddress() {
       return orderSerialize.getAddress();
    }

    public void setAddress(String address) {
        orderSerialize.setAddress(address);
    }

    public void setDescr(String descr) {
        orderSerialize.setDescr(descr);
    }
}
