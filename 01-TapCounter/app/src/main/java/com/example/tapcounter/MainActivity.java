package com.example.tapcounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private int counter;
    private TextView counterLabel;

    long lastDown;
    long keyPressedDuration ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Label
        counterLabel = (TextView)findViewById(R.id.counterLabel);
//        initCounter();
//        incrementCounter();


        // Toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Tap Counter");

        Button button = (Button) findViewById(R.id.toolbar_overflow_menu_button);

//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Perform action on click
//                initCounter();
//            }
//        });


        button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // start your timer
                    lastDown = System.currentTimeMillis();

//                    counterLabel.setText(String.valueOf(lastDown));

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // stop your timer.
                    keyPressedDuration = System.currentTimeMillis() - lastDown;
                }
                return false;
            }
        });

    }
//
//    private void initCounter() {
//        counter = 0;
//        counterLabel.setText(String.valueOf(counter));
//    }
//
//    private void incrementCounter() {
//        // Button
//        final Button button = (Button) findViewById(R.id.tapButton);
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                counter++;
//                counterLabel.setText(String.valueOf(counter));
//            }
//        });
//    }
}
