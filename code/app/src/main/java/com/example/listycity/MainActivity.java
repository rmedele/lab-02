package com.example.listycity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> cityList;
    private ArrayAdapter<String> adapter;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView cityListView = findViewById(R.id.cityListView);
        Button addCityButton = findViewById(R.id.addCityButton);
        Button deleteCityButton = findViewById(R.id.deleteCityButton);

        cityList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_activated_1,
                cityList);

        cityListView.setAdapter(adapter);
        cityListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        cityListView.setOnItemClickListener((parent, view, position, id) ->
                selectedPosition = position
        );

        addCityButton.setOnClickListener(v -> showAddCityDialog());

        deleteCityButton.setOnClickListener(v -> {
            if (selectedPosition != -1) {
                cityList.remove(selectedPosition);
                adapter.notifyDataSetChanged();
                selectedPosition = -1;
            }
        });
    }

    private void showAddCityDialog() {
        EditText input = new EditText(this);

        new AlertDialog.Builder(this)
                .setTitle("Add City")
                .setMessage("Enter city name:")
                .setView(input)
                .setPositiveButton("CONFIRM", (dialog, which) -> {
                    String cityName = input.getText().toString().trim();
                    if (!cityName.isEmpty()) {
                        cityList.add(cityName);
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("CANCEL", null)
                .show();
    }
}
