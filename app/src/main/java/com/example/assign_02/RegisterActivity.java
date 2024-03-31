package com.example.assign_02;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class RegisterActivity extends AppCompatActivity {
    EditText ETName , ETAddress, ETPhone, ETDesc, ETRating;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        SharedPreferences sharedPreferences = getSharedPreferences("restaurant_prefs", Context.MODE_PRIVATE);
        Set<String> restaurantSet = sharedPreferences.getStringSet("restaurant_data", new HashSet<>());

        List<restaurant> restaurantList = new ArrayList<>();

        btnAdd=findViewById(R.id.btnSubmit);
        ETName=findViewById(R.id.ETName);
        ETAddress=findViewById(R.id.ETAddress);
        ETPhone=findViewById(R.id.ETPhone);
        ETDesc=findViewById(R.id.ETDesc);
        ETRating=findViewById(R.id.ETRating);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a new Restaurant object
                restaurant newRestaurant = new restaurant();
                newRestaurant.setRestaurantName(ETName.getText().toString().trim());
                newRestaurant.setLocation(ETAddress.getText().toString().trim());
                newRestaurant.setPhone(ETPhone.getText().toString().trim());
                newRestaurant.setDescription(ETDesc.getText().toString().trim());
                newRestaurant.setRatings(ETRating.getText().toString().trim());

// Add the new restaurant to the list
                restaurantList.add(newRestaurant);
                Log.d("RegisterActivity", "Data before saving:");
                Log.d("RegisterActivity", "ResName: " + restaurantSet);
// Serialize the list of Restaurant objects
                Set<String> serializedSet = new HashSet<>();
                for (String serializedRestaurant : restaurantSet) {
                    restaurant Restaurant = new Gson().fromJson(serializedRestaurant, restaurant.class);
                    restaurantList.add(Restaurant);
                }
// Serialize the updated restaurantList

                for (restaurant Restaurant : restaurantList) {
                    serializedSet.add(new Gson().toJson(Restaurant));
                }

// Save the serialized set to SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putStringSet("restaurant_data", serializedSet);
                editor.apply();
                Log.d("RegisterActivity", "Data after saving:");
                Log.d("RegisterActivity", "ResName: " + serializedSet);
                // Start the MainActivity
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optional: finish the RegisterActivity
        }
    });



    }
}