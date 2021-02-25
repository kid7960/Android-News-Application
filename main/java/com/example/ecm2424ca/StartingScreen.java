package com.example.ecm2424ca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ecm2424ca.R;

import static java.lang.Thread.sleep;

public class StartingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);

        /*
        Creating thread for starting screen
         */
        final Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    sleep(1000);
                    Intent intent = new Intent
                            (StartingScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();     //start thread
    }
}