package com.example.a04location;


import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;


// Tutorial
// https://www.youtube.com/watch?v=qS1E-Vrk60E


public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Display Map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        // Get current location
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }

        String locationManagerString = "";

        if (locationManager.isProviderEnabled(locationManager.NETWORK_PROVIDER)) {
            locationManagerString = locationManager.NETWORK_PROVIDER;
        }
        else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            locationManagerString = locationManager.GPS_PROVIDER;
        }

        locationManager.requestLocationUpdates(locationManagerString, 0, 0, new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                setLocation(location);
            }
            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }
            @Override
            public void onProviderEnabled(String s) {
            }
            @Override
            public void onProviderDisabled(String s) {
            }
        });
    }

    public void setLocation(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        Geocoder geocoder = new Geocoder(getApplicationContext());
        try {
            List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
            String str = addressList.get(0).getLocality() + ", ";
            str += addressList.get(0).getCountryName();
            mMap.addMarker(new MarkerOptions().position(latLng).title(str));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10.2f));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Show fixed location
//        LatLng sydney = new LatLng(-34, 151);
//        mMap.addMarker(new MarkerOptions().position(sydney).title("Market n Sydney"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 10.2f));


    }

}
