package com.ptb.justify;

import androidx.fragment.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.ptb.justify.databinding.ActivityLokasiBinding;

public class Lokasi extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
private ActivityLokasiBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     binding = ActivityLokasiBinding.inflate(getLayoutInflater());
     setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
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

        // Add a marker in Sydney and move the camera
        LatLng lok1 = new LatLng(-0.855219, 100.336839);
        mMap.addMarker(new MarkerOptions().position(lok1).title("KPK Kota Padang"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lok1));

        LatLng lok2 = new LatLng(-0.875484, 100.369642);
        mMap.addMarker(new MarkerOptions().position(lok2).title("KPK Prov.Sumbar"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lok2));

        LatLng lok3 = new LatLng(-0.876338, 100.369989);
        mMap.addMarker(new MarkerOptions().position(lok3).title("Lembaga KPK Sumbar"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lok3));
    }
}