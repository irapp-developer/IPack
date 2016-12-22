package com.psb.ipack.new_order.fill_descr;

import android.content.Context;

import com.psb.ipack.new_order.serializ.OrderSerialize;

/**
 * Created by mehdi on 12/9/16.
 */

public class P_fill_descr implements iP_fill_descr {

    private Context context;
    private iV_fill_descr iV_fill_descr;
    private M_fill_descr mFillDescr;
    private OrderSerialize orderSerialize;

    public P_fill_descr(iV_fill_descr iV_fill_descr) {
        this.context = iV_fill_descr.getContext();
        this.iV_fill_descr = iV_fill_descr;
        this.orderSerialize=iV_fill_descr.getOrderSerialize();
        mFillDescr = new M_fill_descr(this);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setName(String name) {
        mFillDescr.setName(name);
        checkValues();
    }

    public void setPhoneNumber(String phoneNumber) {
        mFillDescr.setPhoneNumber(phoneNumber);
        checkValues();
    }

    public void setAddress(String address) {
        mFillDescr.setAddress(address);
        checkValues();
    }

    public void setDescr(String descr) {
        mFillDescr.setDescr(descr);
        checkValues();
    }

    private void checkValues() {
        if (mFillDescr.getName().length() > 0 &
                mFillDescr.getAddress().length() > 0 &
                mFillDescr.getPhoneNumber().length() > 0
                ) {
            iV_fill_descr.fillValues(true);
        } else {
            iV_fill_descr.fillValues(false);
        }
    }

    @Override
    public OrderSerialize getOrderSerialize() {
        return orderSerialize;
    }
}
