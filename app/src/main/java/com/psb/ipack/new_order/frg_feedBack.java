package com.psb.ipack.new_order;

/**
 * Created by mehdi on 12/7/16.
 */

public interface frg_feedBack {
    int defaultPrice();
    boolean hasEndPoint();
    void onLocationSelected();
    void onRenewLocation();

    void onSuccessCompletedDescr();
    void onFailedCompletedDescr();
    
}
