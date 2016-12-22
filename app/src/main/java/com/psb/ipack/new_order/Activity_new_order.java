package com.psb.ipack.new_order;

import android.animation.Animator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.TextView;

import com.psb.ipack.R;
import com.psb.ipack.logic.G;
import com.psb.ipack.new_order.factor.Frg_Factor;
import com.psb.ipack.new_order.fill_descr.Frg_fill_descr;
import com.psb.ipack.new_order.select_map.Frg_select_map;
import com.psb.ipack.new_order.serializ.OrderSerialize;

import java.util.ArrayList;
import java.util.List;

import static com.psb.ipack.new_order.M_New_Order.NUM_VIEWPAGER_COUNT;

public class Activity_new_order extends AppCompatActivity implements iV_New_Order, frg_feedBack {

    public static final String INSERTED_ORDER_ID_KEY="savedOrderId";
    
    private ViewPager viewPagerNewOrder;
    private P_New_Order p_new_order;
    private TextView textNext;
    private TextView textPrev;
    private OrderSerialize orderSerialize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_order);
        textNext=(TextView)findViewById(R.id.act_new_order_text_next);
        textPrev=(TextView)findViewById(R.id.act_new_order_text_prev);
       

        if(getIntent().getParcelableExtra("parcelabel")==null){
            orderSerialize=new OrderSerialize();    
        }else{
            orderSerialize=getIntent().getParcelableExtra("parcelabel");
        }

       
        p_new_order = new P_New_Order(this, getIntent().getIntExtra("orderId", -1));
        p_new_order.grantPermission();
        
    }

    @Override
    protected void onDestroy() {
        p_new_order=null;
        super.onDestroy();
    }

    ////////// Start interface fragment feedback ///////////////////////////////////////////////////////

    @Override
    public int defaultPrice() {
        return p_new_order.getDefaultPrice();
    }
    
    @Override
    public boolean hasEndPoint() {
        return p_new_order.hasEndPoint();
    }
    
    @Override
    public void onLocationSelected() {
        showBottom("بازگشت","ادامه",true);
    }

    @Override
    public void onRenewLocation() {
        showBottom("بازگشت","ادامه",false);
    }

    @Override
    public void onSuccessCompletedDescr() {
        if(textNext.getVisibility()==View.GONE){
            showBottom("مرحله قبل","مشاهده و ارسال فاکتور",true);   
        }
    }

    @Override
    public void onFailedCompletedDescr() {
        if(textNext.getVisibility()==View.VISIBLE){
            showBottom("مرحله قبل","مشاهده و ارسال فاکتور",false);
        }
    }

////////// End interface fragment feedback /////////////////////////////////////////////////////////    
////////// start interface new order ///////////////////////////////////////////////////////////////
    @Override
    public void onPermissionGranted() {
        viewPagerNewOrder = (ViewPager) findViewById(R.id.act_new_order_pager);
        viewPagerNewOrder.setOffscreenPageLimit(1);
        viewPagerNewOrder.setAdapter(new PagerAdapterNewOrder(getSupportFragmentManager()));
        viewPagerNewOrder.addOnPageChangeListener(p_new_order.getPageListner());
        
        textNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p_new_order.goNextPage();
            }
        });
        
        textPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               p_new_order.goPrevPage();
            }
        });
        
    }

    @Override
    public void onFailedLoadOrder() {

    }

    @Override
    public void setContext(Context context) {

    }

    @Override
    public Context getContext() {
        return this;
    }
    
    @Override  
    public void showBottom(String titlePrev ,String titleNext,boolean isShow){
        textNext.setText(titleNext);
        textPrev.setText(titlePrev);
        if (isShow) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                int cx = textNext.getWidth() / 2;
                int cy = textNext.getHeight() / 2;
                float finalRadius = (float) Math.hypot(cx, cy);
                Animator anim = ViewAnimationUtils.createCircularReveal(textNext, cx, cy, 0, finalRadius);
                textNext.setVisibility(View.VISIBLE);
                anim.start();
            } else {
                textNext.setVisibility(View.VISIBLE);
            }
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                int cx = textNext.getWidth() / 2;
                int cy = textNext.getHeight() / 2;
                float finalRadius = (float) Math.hypot(cx, cy);
                Animator anim = ViewAnimationUtils.createCircularReveal(textNext, cx, cy, finalRadius, 0);
                anim.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        textNext.setVisibility(View.GONE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                });
                anim.start();
            } else {
                textNext.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public void setPagerPosition(int index) {
        if(index==-1){
            finish();
            return;
        }
        viewPagerNewOrder.setCurrentItem(index);
    }

    @Override
    public void updateFactor() {
        viewPagerNewOrder.getAdapter().notifyDataSetChanged();
    }

    @Override
    public OrderSerialize getOrderSerialize() {
        return orderSerialize;
    }

    @Override
    public void successSubmitRequest(long savedOrderId) {
        Log.d(G.LOG_TAG,"save success id is :"+savedOrderId);
        Intent resultIntent=new Intent();
        resultIntent.putExtra(INSERTED_ORDER_ID_KEY,savedOrderId);
        setResult(Activity.RESULT_OK,resultIntent);
        finish();
                
    }

    @Override
    public void failedSubmitRequest(int errorCode, String errorMessage) {
        Log.d(G.LOG_TAG,"save failed id is :"+errorMessage);
        setResult(Activity.RESULT_CANCELED);
        finish();
    }
////////// End interface new order /////////////////////////////////////////////////////////////////    
////////// Start functions /////////////////////////////////////////////////////////////////////////
////////// End functions ///////////////////////////////////////////////////////////////////////////
    
    public class PagerAdapterNewOrder extends FragmentPagerAdapter {
        
        private List<Fragment> arrFragments = new ArrayList<>();

        public PagerAdapterNewOrder(FragmentManager fragmentManager) {
            super(fragmentManager);
            for (int i = 0; i < NUM_VIEWPAGER_COUNT; i++) {
                arrFragments.add(null);
            }
        }

        @Override
        public int getCount() {
            return NUM_VIEWPAGER_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            if (arrFragments.get(position) == null) {
                switch (position) {
                    case 0: // Fragment # 0 - This will show FirstFragment
                        arrFragments.add(position, Frg_select_map.newInstance(orderSerialize));
                        break;
                    case 1: // Fragment # 0 - This will show FirstFragment
                        arrFragments.add(position, Frg_fill_descr.newInstance(orderSerialize));
                        break;
                    case 2: // Fragment # 0 - This will show FirstFragment
                        arrFragments.add(position, Frg_Factor.newInstance(orderSerialize));
                        break;
                }
            }
            return arrFragments.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            if(object instanceof Frg_Factor){
                ((Frg_Factor)arrFragments.get(2)).updateViews();
            }
            return super.getItemPosition(object);
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }
    }
}
