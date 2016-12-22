package com.psb.ipack.new_order.fill_descr;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.psb.ipack.R;
import com.psb.ipack.new_order.Activity_new_order;
import com.psb.ipack.new_order.serializ.OrderSerialize;
import com.psb.ipack.new_order.frg_feedBack;

public class Frg_fill_descr extends Fragment implements iV_fill_descr {
    private static final String ORDER_VALUES = "defaultValues";
    private frg_feedBack frg_feedBack;
    private P_fill_descr pFillDescr;
    private View pView;
    private EditText textName;
    private EditText textPhoneNumber;
    private EditText textAddress;
    private EditText textDescr;

    private OrderSerialize orderSerialize;

    public Frg_fill_descr() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            orderSerialize = getArguments().getParcelable(ORDER_VALUES);
        }
    }

    public static Frg_fill_descr newInstance(OrderSerialize orderSerialize) {
        Frg_fill_descr fragment = new Frg_fill_descr();
        Bundle args = new Bundle();
        args.putParcelable(ORDER_VALUES,orderSerialize);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        pFillDescr = new P_fill_descr(this);
        pView=inflater.inflate(R.layout.fragment_fill_descr, container, false);

        textName=(AppCompatEditText)pView.findViewById(R.id.frg_fill_desc_name);
        textPhoneNumber=(AppCompatEditText)pView.findViewById(R.id.frg_fill_desc_number);
        textAddress=(AppCompatEditText)pView.findViewById(R.id.frg_fill_desc_address);
        textDescr=(AppCompatEditText)pView.findViewById(R.id.frg_fill_desc_descr);

        textName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                    pFillDescr.setName(editable.toString());
            }
        });
        textPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                pFillDescr.setPhoneNumber(editable.toString());
            }
        });
        textAddress.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                pFillDescr.setAddress(editable.toString());
            }
        });
        textDescr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                pFillDescr.setDescr(editable.toString());
            }
        });
        
        if(orderSerialize.getFirstName()!=null){
            textName.setText(orderSerialize.getFirstName());
        }
        if(orderSerialize.getPhoneNumber()!=null){
            textPhoneNumber.setText(orderSerialize.getPhoneNumber());
        }
        if(orderSerialize.getAddress()!=null){
            textAddress.setText(orderSerialize.getAddress());
        }
        if(orderSerialize.getDescr()!=null){
            textDescr.setText(orderSerialize.getDescr());
        }
       



        return pView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity_new_order) {
            frg_feedBack = (frg_feedBack) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        frg_feedBack=null;
    }

    @Override
    public void fillValues(boolean isCompleted) {
        if (isCompleted) {
            frg_feedBack.onSuccessCompletedDescr();
        } else {
            frg_feedBack.onFailedCompletedDescr();
        }
    }

    @Override
    public OrderSerialize getOrderSerialize() {
        return orderSerialize;
    }
    
}
