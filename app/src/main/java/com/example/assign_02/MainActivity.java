package com.example.assign_02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager manager;
    RestaurantAdapter adapter;
    ArrayList<restaurant> restaurantList;
    EditText etSearch;
    Button btnAddNewRestaurant;
    ImageView imgSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.rvRestaurants);
        btnAddNewRestaurant = findViewById(R.id.btnAddNewRestaurant);
        etSearch = findViewById(R.id.etSearch);
        imgSearch=findViewById(R.id.imgSearch);// Assuming you have an ImageView with ID imgSearch
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        // Initialize the restaurant list and adapter
        restaurantList = new ArrayList<>();
        adapter = new RestaurantAdapter(this, restaurantList);
        recyclerView.setAdapter(adapter);

        // Load restaurant data from SharedPreferences
        loadRestaurantData();

        // Set up search filter
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String searchText = editable.toString().toLowerCase().trim();
                adapter.getFilter().filter(searchText);
            }
        });
        String searchText = etSearch.getText().toString().toLowerCase().trim();
              adapter.getFilter().filter(searchText);
        // Set onClickListener for imgSearch ImageView to trigger filtering
//        imgSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String searchText = etSearch.getText().toString().toLowerCase().trim();
//                adapter.getFilter().filter(searchText);
//            }
//        });

        btnAddNewRestaurant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadRestaurantData() {
        SharedPreferences sharedPreferences = getSharedPreferences("restaurant_prefs", Context.MODE_PRIVATE);
        Set<String> serializedSet = sharedPreferences.getStringSet("restaurant_data", new HashSet<>());

        restaurantList.clear();

        // Deserialize the set of serialized objects
        for (String serializedObject : serializedSet) {
            restaurant Restaurant = new Gson().fromJson(serializedObject, restaurant.class);
            restaurantList.add(Restaurant);
        }

        // Notify the adapter that the data set has changed
        adapter.notifyDataSetChanged();
    }
}
