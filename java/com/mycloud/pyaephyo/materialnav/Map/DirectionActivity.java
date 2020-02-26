package com.mycloud.pyaephyo.materialnav.Map;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mycloud.pyaephyo.materialnav.Model.UniversityInfo;
import com.mycloud.pyaephyo.materialnav.R;
import com.mycloud.pyaephyo.materialnav.database.DatabaseHelper;

public class DirectionActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    DatabaseHelper dh;
    UniversityInfo university;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.direction_layout);

        dh=new DatabaseHelper(this);

        String uni_id=getIntent().getStringExtra("id");
        Log.i("OnMapReady Activity", uni_id + " is the id for the university desired");
        university =dh.getUData(uni_id);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;



        MapLocationHolder mapLoc = MapLocationHolder.getInstance();

        double lat=Double.parseDouble(university.getUniLat());
        double lng=Double.parseDouble(university.getUniLng());

        mapLoc.setLatLng(lat,lng);

        LatLng position = new LatLng(mapLoc.getLat(), mapLoc.getLng());

        mMap.addMarker(new MarkerOptions().position(position).title("University"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position,15.0f));
    }
}
