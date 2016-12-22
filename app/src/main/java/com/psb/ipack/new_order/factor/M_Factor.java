package com.psb.ipack.new_order.factor;

import android.content.Context;

import com.psb.ipack.new_order.serializ.OrderSerialize;

/**
 * Created by mehdi on 12/10/16.
 */

public class M_Factor implements iM_Factor{
    private Context context;
    private iP_Factor ipFactor;
    private OrderSerialize orderSerialize;
    
    public M_Factor(iP_Factor ipFactor) {
        this.ipFactor=ipFactor;
        this.setContext(this.ipFactor.getContext());
        this.orderSerialize=ipFactor.getOrderSerialize();
                
        
    }
    
    @Override
    public void setContext(Context context) {
        this.context=context;
    }

    @Override
    public Context getContext() {
        return this.context;
    }

}
