package com.mycloud.pyaephyo.materialnav.Map;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.mycloud.pyaephyo.materialnav.Model.UniversityInfo;
import com.mycloud.pyaephyo.materialnav.R;
import com.mycloud.pyaephyo.materialnav.database.DatabaseHelper;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RouteActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {

    private GoogleMap mMap;

    private static final String TAG = RouteActivity.class.getSimpleName();

    DatabaseHelper dh;

    UniversityInfo university;

    SupportMapFragment fm;

    LatLng source;
    LatLng dest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.route_layout);

        dh=new DatabaseHelper(this);

        String uni_id=getIntent().getStringExtra("id");
        Log.i("OnMapReady Activity", uni_id + " is the id for the university desired");
        university =dh.getUData(uni_id);


        try{

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);

            fm = (SupportMapFragment)
                    getSupportFragmentManager().findFragmentById(R.id.map);

            mMap = fm.getMap();

            mMap.setMyLocationEnabled(true);

            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            Criteria criteria = new Criteria();

            String provider = locationManager.getBestProvider(criteria, true);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            Location location = locationManager.getLastKnownLocation(provider);

            if (location != null) {

                double latitude = location.getLatitude();

                double longitude = location.getLongitude();

                LatLng latLng = new LatLng(latitude, longitude);

                source = new LatLng(latitude, longitude);

                Log.i(TAG,"Source : "+ source);
                mMap.addMarker(new MarkerOptions().position(source).title("Me").icon(BitmapDescriptorFactory.fromResource(R.drawable.man_standing_up)));
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(source,10.0f));

            }

            MapLocationHolder mapLoc = MapLocationHolder.getInstance();

            double lat=Double.parseDouble(university.getUniLat());
            double lng=Double.parseDouble(university.getUniLng());

            mapLoc.setLatLng(lat,lng);

            dest=new LatLng(mapLoc.getLat(),mapLoc.getLng());

            mMap.addMarker(new MarkerOptions().position(dest).title("University").icon(BitmapDescriptorFactory.fromResource(R.drawable.university2)));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(dest,10.0f));

            Log.i(TAG,"Destination : "+ dest);
            String url = getDirectionsUrl(source, dest);

            DownloadTask downloadTask = new DownloadTask();

            downloadTask.execute(url);


        }
        catch(NullPointerException event) {
            Toast.makeText(this, "Fetching Location Failed.", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e) {
            Toast.makeText(this, e.toString() , Toast.LENGTH_SHORT).show();
        }



    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mMap==null){
            fm = (SupportMapFragment)
                    getSupportFragmentManager().findFragmentById(R.id.map);

            mMap = fm.getMap();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
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
    }



    @Override
    public void onLocationChanged(Location location) {

    }



    private String getDirectionsUrl(LatLng origin, LatLng dest) {

        String str_origin = "origin=" + origin.latitude + "," + origin.longitude;

        String str_dest = "destination=" + dest.latitude + "," + dest.longitude;

        String sensor = "sensor=false";

        String parameters = str_origin + "&" + str_dest + "&" + sensor;

        String output = "json";

        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters;

        return url;
    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.connect();

            iStream = urlConnection.getInputStream();

            BufferedReader br = new BufferedReader(new InputStreamReader(iStream));

            StringBuffer sb = new StringBuffer();

            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            data = sb.toString();

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }

    private class DownloadTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {

            String data = "";

            try {
                data = downloadUrl(url[0]);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            ParserTask parserTask = new ParserTask();

            parserTask.execute(result);
        }
    }

    private class ParserTask extends AsyncTask<String, Integer, List<List<HashMap<String, String>>>> {

        @Override
        protected List<List<HashMap<String, String>>> doInBackground(String... jsonData) {

            JSONObject jObject;
            List<List<HashMap<String, String>>> routes = null;

            try {
                jObject = new JSONObject(jsonData[0]);
                DirectionsJSONParser parser = new DirectionsJSONParser();
                routes = parser.parse(jObject);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return routes;
        }

        @Override
        protected void onPostExecute(List<List<HashMap<String, String>>> result) {
            ArrayList<LatLng> points = null;
            PolylineOptions lineOptions = null;
            MarkerOptions markerOptions = new MarkerOptions();
            String distance = "";
            String duration = "";

            if (result.size() < 1) {
                Toast.makeText(getBaseContext(), "No Points", Toast.LENGTH_SHORT).show();
                return;
            }

            for (int i = 0; i < result.size(); i++) {
                points = new ArrayList<LatLng>();
                lineOptions = new PolylineOptions();

                List<HashMap<String, String>> path = result.get(i);

                for (int j = 0; j < path.size(); j++) {
                    HashMap<String, String> point = path.get(j);
                    double lat = Double.parseDouble(point.get("lat"));
                    double lng = Double.parseDouble(point.get("lng"));
                    LatLng position = new LatLng(lat, lng);

                    points.add(position);
                }

                lineOptions.addAll(points);
                lineOptions.width(5);
                lineOptions.color(Color.BLUE);
            }

            Log.i("Distance : " + distance, "Duration : " + duration);

            mMap.addPolyline(lineOptions);
        }
    }
}
