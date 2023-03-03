package com.example.googlemaps;

import static com.example.googlemaps.BuildConfig.MAPS_API_KEY;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.googlemaps.databinding.ActivityMainBinding;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    static final double SOUTH=29.696354;
    static final double WEST=31.133331;
    static final double NORTH=30.186870;
    static final double EAST=31.454545;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        /* M Osama: Initalizing Google MAPs API */
        Places.initialize(getApplicationContext(),MAPS_API_KEY);
        PlacesClient placesClient = Places.createClient(this);

        /* M Osama: Initializing PlacesAutoCompleteView */
        AutocompleteSupportFragment autocompleteSupportFragment = (AutocompleteSupportFragment) getSupportFragmentManager().findFragmentById(R.id.autoCompleteFragment);
        autocompleteSupportFragment.setLocationBias(RectangularBounds.newInstance(new LatLng(SOUTH, WEST),new LatLng(NORTH, EAST))) /* M Osama: returns results in Cairo, Egypt only */
                                    .setCountries("EGY")                                                                            /* M Osama: returns results in Egypt only */
                                    .setPlaceFields(Arrays.asList(Place.Field.ID,Place.Field.NAME,Place.Field.LAT_LNG));            /* M Osama: returns onClickListener ID, Name, Latitude&Longitude */

        /* M Osama: PlacesAutoCompleteView onClickListener */
        autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onError(@NonNull Status status) {
                Toast.makeText(MainActivity.this, status.getStatusMessage(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onPlaceSelected(@NonNull Place place) {
                Toast.makeText(MainActivity.this, place.getName(), Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, place.getLatLng().longitude+"", Toast.LENGTH_SHORT).show();
                Toast.makeText(MainActivity.this, place.getLatLng().latitude+"", Toast.LENGTH_SHORT).show();
            }
        });

        /* M Osama: to check app is not stuck */
        binding.btn.setOnClickListener(v -> Toast.makeText(MainActivity.this, "Hellooooooooooo", Toast.LENGTH_SHORT).show());

    }
}