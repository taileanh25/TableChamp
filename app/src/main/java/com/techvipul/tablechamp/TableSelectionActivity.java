package com.techvipul.tablechamp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class TableSelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_selection);

        // Get table type from Intent
        String tableType = getIntent().getStringExtra("table_type");

        // Set up Toolbar as ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            String title = "Select " + capitalize(tableType) + " Table";
            getSupportActionBar().setTitle(title);
        }

        // Initialize GridView
        GridView gridView = findViewById(R.id.grid_numbers);
        String[] numbers = new String[99];
        for (int i = 0; i < 99; i++) {
            numbers[i] = String.valueOf(i + 1);
        }

        // Custom ArrayAdapter for button-like styling
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.item_grid_number, R.id.text_number, numbers) {
            @NonNull
            @Override
            public View getView(int position, View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView textView = view.findViewById(R.id.text_number);
                textView.setTextColor(getResources().getColor(R.color.toolbar_background));
                textView.setTextSize(18);
                textView.setTypeface(null, android.graphics.Typeface.BOLD);
                textView.setClickable(false);
                textView.setFocusable(false);
                return view;
            }
        };
        gridView.setAdapter(adapter);

        // Set click listener for GridView
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(TableSelectionActivity.this, TableDisplayActivity.class);
            intent.putExtra("table_type", tableType);
            intent.putExtra("number", position + 1);
            startActivity(intent);
        });
    }

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}