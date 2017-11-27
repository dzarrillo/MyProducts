package com.dzartek.myproducts.showproduct;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dzartek.myproducts.R;

/**
 * Created by dzarrillo on 11/27/2017.
 */

public class FragmentShowProduct extends Fragment {

    public FragmentShowProduct() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_showproduct, container, false);

        return v;
    }
}
