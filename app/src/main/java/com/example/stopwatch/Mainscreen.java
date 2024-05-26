package com.example.stopwatch;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class Mainscreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainscreen);

        // Delay for 3 seconds (3000 milliseconds) before navigating to MainActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Mainscreen.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close WelcomeActivity
            }
        }, 3000);
    }
}
