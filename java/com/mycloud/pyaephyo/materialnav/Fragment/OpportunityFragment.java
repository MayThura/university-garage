package com.mycloud.pyaephyo.materialnav.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mycloud.pyaephyo.materialnav.Model.UniversityInfo;
import com.mycloud.pyaephyo.materialnav.R;
import com.mycloud.pyaephyo.materialnav.database.DatabaseHelper;

import static com.mycloud.pyaephyo.materialnav.InformationPager.id;

/**
 * Created by ucsm on 10/27/2016.
 */

public class OpportunityFragment extends Fragment {
    public ImageView uniPhoto;
    public DatabaseHelper dbHelper;
    public OpportunityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.university_opportunity_layout, container, false);
        dbHelper=new DatabaseHelper(getContext());
        UniversityInfo university=dbHelper.getUData(id);
        uniPhoto=(ImageView)v.findViewById(R.id.uniPhoto);

        Bitmap bm= BitmapFactory.decodeByteArray(university.getUniPhoto(),0,university.getUniPhoto().length);
        uniPhoto.setImageBitmap(bm);
        return v;
    }
}
