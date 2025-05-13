package com.techvipul.tablechamp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private FloatingActionButton musicToggle;
    private boolean isMusicPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up Toolbar as ActionBar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Math Tables Master");
        }

        // Initialize CardViews
        MaterialCardView multiplicationCard = findViewById(R.id.card_multiplication);
        MaterialCardView additionCard = findViewById(R.id.card_addition);
        MaterialCardView subtractionCard = findViewById(R.id.card_subtraction);

        // Set click listeners for cards
        multiplicationCard.setOnClickListener(v -> startTableSelection("multiplication"));
        additionCard.setOnClickListener(v -> startTableSelection("addition"));
        subtractionCard.setOnClickListener(v -> startTableSelection("subtraction"));

        // Initialize music toggle FAB
        musicToggle = findViewById(R.id.fab_music);
        musicToggle.setOnClickListener(v -> {
            if (isMusicPlaying) {
                // Stop music (placeholder)
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                musicToggle.setImageResource(android.R.drawable.ic_media_play);
                isMusicPlaying = false;
            } else {
                // Start music (placeholder)
                mediaPlayer = new MediaPlayer();
                musicToggle.setImageResource(android.R.drawable.ic_media_pause);
                isMusicPlaying = true;
            }
        });
    }

    private void startTableSelection(String tableType) {
        Intent intent = new Intent(MainActivity.this, TableSelectionActivity.class);
        intent.putExtra("table_type", tableType);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}