package app.kulture.kucherenko.init.com.kulture.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import app.kulture.kucherenko.init.com.kulture.R;

public class GoogleMapContact extends Fragment implements OnMapReadyCallback {

    private MapView mMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_google_map, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mMap = view.findViewById(R.id.af_mapView);
        if (mMap != null) {
            mMap.onCreate(savedInstanceState);
            mMap.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng kulture = new LatLng(1.303180, 103.872860);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(kulture, 17);
        googleMap.moveCamera(cameraUpdate);
        googleMap.setBuildingsEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.addMarker(new MarkerOptions().position(kulture).title("33 Ann Siang Rd"));
    }

    @Override
    public void onResume() {
        mMap.onResume();
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMap.onPause();
    }

    @Override
    public void onDestroy() {
        mMap.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        mMap.onLowMemory();
        super.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mMap.onSaveInstanceState(outState);
    }
}

