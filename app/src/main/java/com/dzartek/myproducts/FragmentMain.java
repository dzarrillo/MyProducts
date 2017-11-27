package com.dzartek.myproducts;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemSelected;

/**
 * Created by dzarrillo on 11/27/2017.
 */

public class FragmentMain extends Fragment implements View.OnClickListener{
    @BindView(R.id.buttonCreateProduct) Button buttonCreateProduct;
    @BindView(R.id.buttonShowProduct) Button buttonShowProduct;

    public interface OnSelectedProductButton{
        void onButtonSelected(int id);
    }
    private OnSelectedProductButton mButtonCallback;

    public FragmentMain(){}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, v);

        buttonCreateProduct.setOnClickListener(this);
        buttonShowProduct.setOnClickListener(this);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        try{
            mButtonCallback = (OnSelectedProductButton) getActivity();
        } catch (Exception e){
            throw new ClassCastException(getActivity().toString() + " must implement OnSelectedProductButton!");
        }

    }

    @Override
    public void onClick(View view) {
        mButtonCallback.onButtonSelected(view.getId());
    }
}
