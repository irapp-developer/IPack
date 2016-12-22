package com.psb.ipack.new_order.factor;

import android.content.Context;

import com.psb.ipack.new_order.serializ.OrderSerialize;

/**
 * Created by mehdi on 12/10/16.
 */

public class P_Factor implements iP_Factor {
    private Context context;
    private M_Factor mFactor;
    private iV_Factor ivFactor;
    private OrderSerialize orderSerialize;
    
    public P_Factor(iV_Factor ivFactor) {
        this.ivFactor=ivFactor;
        setContext(this.ivFactor.getContext());
        mFactor=new M_Factor(this);
    }
    
    @Override
    public void setContext(Context context) {
        
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public OrderSerialize getOrderSerialize() {
        return this.orderSerialize;
    }

}
