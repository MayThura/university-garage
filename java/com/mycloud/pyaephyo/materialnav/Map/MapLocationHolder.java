package com.mycloud.pyaephyo.materialnav.Map;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Lullaby on 28-Oct-16.
 */
public class MapLocationHolder {
    private static MapLocationHolder ourInstance = new MapLocationHolder();

    public static MapLocationHolder getInstance() {

        return ourInstance;
    }

    private MapLocationHolder() {
        // default location
        location = new LatLng(-34, 151);

    }

    private LatLng location;

    public void setLatLng(LatLng loc) {
        ourInstance.location = loc;
    }

    public void setLatLng( double lat,double lng) {
        ourInstance.location = new LatLng(lat, lng);
    }

    public LatLng getLatLng() {
        return ourInstance.location;
    }

    public double getLat() {
        return  ourInstance.location.latitude;
    }

    public double getLng() {
        return  ourInstance.location.longitude;
    }

}

