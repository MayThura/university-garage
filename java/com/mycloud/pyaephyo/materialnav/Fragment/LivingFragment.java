package com.mycloud.pyaephyo.materialnav.Fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mycloud.pyaephyo.materialnav.Model.Support;
import com.mycloud.pyaephyo.materialnav.Model.UniversityInfo;
import com.mycloud.pyaephyo.materialnav.R;
import com.mycloud.pyaephyo.materialnav.database.DatabaseHelper;

import static com.mycloud.pyaephyo.materialnav.InformationPager.id;

/**
 * Created by ucsm on 10/29/2016.
 */
public class LivingFragment extends Fragment {
    public TextView uniFerry,uniHostel;
    public DatabaseHelper dbHelper;
    public ImageView uniPhoto;
    public LivingFragment(){}
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.university_living_layout, container, false);
        dbHelper=new DatabaseHelper(getContext());
        Support support=dbHelper.getSupportData(id);
        UniversityInfo university=dbHelper.getUData(id);

        uniPhoto=(ImageView)v.findViewById(R.id.uniPhoto);
        uniFerry=(TextView) v.findViewById(R.id.uniFerry);
        uniHostel=(TextView) v.findViewById(R.id.uniHostel);

        uniFerry.setText(support.getFerrySupport());
        uniHostel.setText(support.getHostelSupport());
        Bitmap bm= BitmapFactory.decodeByteArray(university.getUniPhoto(),0,university.getUniPhoto().length);
        uniPhoto.setImageBitmap(bm);
        return v;
    }

}
