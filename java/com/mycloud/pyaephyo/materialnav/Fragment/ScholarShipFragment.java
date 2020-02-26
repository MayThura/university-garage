package com.mycloud.pyaephyo.materialnav.Fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mycloud.pyaephyo.materialnav.R;

/**
 * Created by ucsm on 10/27/2016.
 */

public class ScholarShipFragment extends Fragment {
    public ScholarShipFragment() {
    
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.scholar_layout, container, false);
        return v;
    }
}
