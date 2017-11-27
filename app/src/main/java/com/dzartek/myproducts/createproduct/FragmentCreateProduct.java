package com.dzartek.myproducts.createproduct;

import android.os.Bundle;
import com.dzartek.myproducts.R;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dzarrillo on 11/27/2017.
 */

public class FragmentCreateProduct extends Fragment {

    public FragmentCreateProduct() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_createproduct, container, false);

        return v;
    }
}
