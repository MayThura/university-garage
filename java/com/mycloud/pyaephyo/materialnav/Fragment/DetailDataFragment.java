package com.mycloud.pyaephyo.materialnav.Fragment;

/**
 * Created by Windows on 10/26/2016.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mycloud.pyaephyo.materialnav.FilterActivity;
import com.mycloud.pyaephyo.materialnav.Map.DirectionActivity;
import com.mycloud.pyaephyo.materialnav.Map.RouteActivity;
import com.mycloud.pyaephyo.materialnav.Model.Admission;
import com.mycloud.pyaephyo.materialnav.Model.MajorDetail;
import com.mycloud.pyaephyo.materialnav.Model.UniversityInfo;
import com.mycloud.pyaephyo.materialnav.R;
import com.mycloud.pyaephyo.materialnav.database.DatabaseHelper;
import com.mycloud.pyaephyo.materialnav.database.MyDatabase;

import static com.mycloud.pyaephyo.materialnav.InformationPager.id;


public class DetailDataFragment extends Fragment{
    public DatabaseHelper dbHelper;
    public TextView uniTitle,uniLocation,uniFee,uniUniform,uniPhone,uniEmail,uniWebsite,uniMark,uniMajor,uniDegree,uniDuration,uniDescription,uniAdmission,uniTest;
    public ImageView uniPhoto;
    public ImageButton btnFavorite,btnCall,btnWebsite,btnDirection,btnRoute;
    public DetailDataFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.university_data_show_layout, container, false);
        dbHelper=new DatabaseHelper(getContext());

        Log.i("ID",id);
        final UniversityInfo university=dbHelper.getUData(id);
        MajorDetail major=dbHelper.getMajorData(id);
        Admission admission=dbHelper.getAdmissionData(id);
        uniPhoto=(ImageView)v.findViewById(R.id.uniPhoto);
       uniTitle=(TextView) v.findViewById(R.id.uniTitle);
        uniLocation=(TextView)v.findViewById(R.id.uniLocation);
        uniFee=(TextView)v.findViewById(R.id.uniFee);
        uniUniform=(TextView)v.findViewById(R.id.uniUniform);
        uniPhone=(TextView)v.findViewById(R.id.uniPhone);
        uniEmail=(TextView)v.findViewById(R.id.uniEmail);
        uniWebsite=(TextView)v.findViewById(R.id.uniWebsite);
        uniMark=(TextView)v.findViewById(R.id.uniMark);
        uniMajor=(TextView)v.findViewById(R.id.uniMajor);
        uniDegree=(TextView)v.findViewById(R.id.uniDegree);
        uniDuration=(TextView)v.findViewById(R.id.uniDuration);
        uniDescription=(TextView)v.findViewById(R.id.uniDescription);
        uniAdmission=(TextView)v.findViewById(R.id.uniAdmission);
        uniTest=(TextView)v.findViewById(R.id.uniTest);
        btnFavorite=(ImageButton)v.findViewById(R.id.btnFavorite);
        btnCall=(ImageButton)v.findViewById(R.id.btnCall);
        btnWebsite=(ImageButton)v.findViewById(R.id.btnWebsite);
        btnDirection=(ImageButton)v.findViewById(R.id.btnDirection);
        btnRoute=(ImageButton)v.findViewById(R.id.btnRoute);

        final Bitmap bm= BitmapFactory.decodeByteArray(university.getUniPhoto(),0,university.getUniPhoto().length);
        uniPhoto.setImageBitmap(bm);
        uniTitle.setText(university.getUniName());
        uniLocation.setText(university.getUniLocation());
        uniFee.setText(university.getUniCost());
        uniUniform.setText(university.getUniUniform());
        uniPhone.setText(university.getUniPhone());
        uniEmail.setText(university.getUniEmail());
        uniWebsite.setText(university.getUniWebsite());
        uniMark.setText(university.getUniMark());
        uniMajor.setText(major.getMajor());
        uniDegree.setText(major.getDegree());
        uniDuration.setText(major.getDuration());
        uniDescription.setText(major.getDescription());
        uniAdmission.setText(admission.getAdmissionType());
        uniTest.setText(admission.getTestDescription());
        final MyDatabase mdb;
        DatabaseHelper db=new DatabaseHelper(getContext());
        mdb=new MyDatabase(getContext());
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mdb.insertData(id,university.getUniName().toString(),bm);
                Toast.makeText(getContext(),"Inserted",Toast.LENGTH_LONG).show();
            }
        });
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent=new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+university.getUniPhone().toString()));
                startActivity(callIntent);
            }
        });
        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent =
                        new Intent(Intent.ACTION_VIEW, Uri.parse("https://"+university.getUniWebsite().toString()));
                startActivity(browserIntent);
            }
        });
        btnDirection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), DirectionActivity.class);
                i.putExtra("id",university.getUniId());
                startActivity(i);

            }
        });

        btnRoute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), RouteActivity.class);
                i.putExtra("id",university.getUniId());
                startActivity(i);
            }
        });

        return v;

    }



}

