package com.ptb.justify;

import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

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
    private AutoCompleteTextView searchBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLokasiBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        searchBox = binding.searchBox;
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setUpAutoComplete();
    }

    private void setUpAutoComplete() {
        String[] locations = {"KPK Kota Padang", "KPK Prov.Sumbar", "Lembaga KPK Sumbar"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, locations);
        searchBox.setAdapter(adapter);
        searchBox.setOnItemClickListener((parent, view, position, id) -> {
            String selectedLocation = (String) parent.getItemAtPosition(position);
            onSearchLocation(selectedLocation);
        });
    }

    private void onSearchLocation(String location) {
        LatLng selectedLatLng = null;

        switch (location) {
            case "KPK Kota Padang":
                selectedLatLng = new LatLng(-0.855219, 100.336839);
                break;
            case "KPK Prov.Sumbar":
                selectedLatLng = new LatLng(-0.875484, 100.369642);
                break;
            case "Lembaga KPK Sumbar":
                selectedLatLng = new LatLng(-0.876338, 100.369989);
                break;
        }

        if (selectedLatLng != null) {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedLatLng, 15f));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng lok1 = new LatLng(-0.855219, 100.336839);
        mMap.addMarker(new MarkerOptions().position(lok1).title("KPK Kota Padang"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(lok1));

        LatLng lok2 = new LatLng(-0.875484, 100.369642);
        mMap.addMarker(new MarkerOptions().position(lok2).title("KPK Prov.Sumbar"));

        LatLng lok3 = new LatLng(-0.876338, 100.369989);
        mMap.addMarker(new MarkerOptions().position(lok3).title("Lembaga KPK Sumbar"));
    }
}
