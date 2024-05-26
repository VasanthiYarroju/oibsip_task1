package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView timerText;
    private Button startButton, stopButton, holdButton;
    private Handler handler;
    private long startTime, elapsedTime;
    private boolean isRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerText = findViewById(R.id.timer);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
        holdButton = findViewById(R.id.holdButton);

        handler = new Handler();

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });

        holdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holdTimer();
            }
        });
    }

    private void startTimer() {
        if (!isRunning) {
            startTime = System.currentTimeMillis() - elapsedTime;
            handler.postDelayed(updateTimerThread, 0);
            isRunning = true;
        }
    }

    private void stopTimer() {
        if (isRunning) {
            handler.removeCallbacks(updateTimerThread);
            elapsedTime = 0;
            timerText.setText("00:00:00");
            isRunning = false;
        }
    }

    private void holdTimer() {
        if (isRunning) {
            handler.removeCallbacks(updateTimerThread);
            elapsedTime = System.currentTimeMillis() - startTime;
            isRunning = false;
        }
    }

    private Runnable updateTimerThread = new Runnable() {
        @Override
        public void run() {
            elapsedTime = System.currentTimeMillis() - startTime;
            int seconds = (int) (elapsedTime / 1000);
            int minutes = seconds / 60;
            int hours = minutes / 60;
            seconds = seconds % 60;
            minutes = minutes % 60;
            timerText.setText(String.format("%02d:%02d:%02d", hours, minutes, seconds));
            handler.postDelayed(this, 1000);
        }
    };
}
