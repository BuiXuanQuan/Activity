package com.example.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class StopWatch extends AppCompatActivity {

    private int seconds = 0;
    private boolean running ;
    private boolean wasRunning ;


    Button btnStart,btnStop,btnReset;

    TextView txtTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop_watch);

        btnStart = findViewById(R.id.btnStart);
        btnStop = findViewById(R.id.btnStop);
        btnReset = findViewById(R.id.btnReset);
        txtTime = findViewById(R.id.txtTime);

        //        Restore the activity’s
        //        state by getting values
         //        from the Bundle.

        if (savedInstanceState != null){
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning=savedInstanceState.getBoolean("wasRunning");

        }

        start();
        stop();
        reset();
        runTimer();






    }

    private void runTimer() {

       final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                int hours = seconds /3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;

                String timeView = String.format(Locale.getDefault(),"%d:%02d:%02d", hours, minutes, secs);

                txtTime.setText(timeView);
                if(running == true){
                    seconds ++;
                }
                handler.postDelayed(this, 1000);
            }
        });
    }


    private void start() {
          btnStart.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  running = true;
              }
          });

    }



    private void stop() {
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
            }
        });

    }

    private void reset() {
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                running = false;
                seconds = 0;
            }
        });

    }

//    This method gets called before the
//    activity gets destroyed, which means you get an opportunity to save any values
//    you want to retain before they get lost.
    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

//        Save the state of the
//        variables in the activity’s
//        onSaveInstanceState() method.

        outState.putInt("seconds",seconds);
        outState.putBoolean("running",running);
        outState.putBoolean("wasRunning",wasRunning);

    }



    @Override
    protected void onStart() {
        super.onStart();

        if(wasRunning){
            running = true;
        }
        Log.d("Stop Watch "," ============== onStart =======");
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
        Log.d("Stop Watch "," ============== onResume =======");

    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
        Log.d("Stop Watch "," ============== onPause =======");

    }
    @Override
    protected void onStop() {
        super.onStop();

        wasRunning = running;
        running = false;
        Log.d("Stop Watch "," ============== onStop =======");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Stop Watch "," ============== onRestart =======");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Stop Watch "," ============== onDestroy =======");

    }
}