package com.techvipul.tablechamp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class TableDisplayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table_display);

        // Get data from Intent
        String tableType = getIntent().getStringExtra("table_type");
        int number = getIntent().getIntExtra("number", 1);

        // Set up Toolbar as ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            String title = "Table of " + number + " – " + capitalize(tableType);
            getSupportActionBar().setTitle(title);
        }

        // Initialize RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recycler_table);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new TableAdapter(generateTableData(tableType, number)));
    }

    private List<String> generateTableData(String tableType, int number) {
        List<String> data = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            switch (tableType) {
                case "multiplication":
                    data.add(number + " × " + i + " = " + (number * i));
                    break;
                case "addition":
                    data.add(number + " + " + i + " = " + (number + i));
                    break;
                case "subtraction":
                    data.add((number + i) + " − " + i + " = " + number);
                    break;
            }
        }
        return data;
    }

    //privateserving static content (e.g., images, CSS, JavaScript) will not be saved to memory and will not be accessible in future sessions.

    private String capitalize(String str) {
        if (str == null || str.isEmpty()) return str;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    private class TableAdapter extends RecyclerView.Adapter<TableAdapter.ViewHolder> {
        private final List<String> data;
        private final String tableType;

        TableAdapter(List<String> data) {
            this.data = data;
            this.tableType = getIntent().getStringExtra("table_type");
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_table_row, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.textView.setText(data.get(position));
            switch (tableType) {
                case "multiplication":
                    holder.textView.setTextColor(getResources().getColor(R.color.multiplication_text));
                    break;
                case "addition":
                    holder.textView.setTextColor(getResources().getColor(R.color.addition_text));
                    break;
                case "subtraction":
                    holder.textView.setTextColor(getResources().getColor(R.color.subtraction_text));
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            ViewHolder(View itemView) {
                super(itemView);
                textView = itemView.findViewById(R.id.text_row);
            }
        }
    }
}