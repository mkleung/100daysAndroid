package com.example.tapcounter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private int counter;
    private TextView counterLabel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Label
        counterLabel = (TextView)findViewById(R.id.counterLabel);
        initCounter();
        incrementCounter();


        // Toolbar
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Tap Counter");

        Button button = (Button) findViewById(R.id.toolbar_overflow_menu_button);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                initCounter();
            }
        });

    }

    private void initCounter() {
        counter = 0;
        counterLabel.setText(String.valueOf(counter));
    }

    private void incrementCounter() {
        // Button
        final Button button = (Button) findViewById(R.id.tapButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                counter++;
                counterLabel.setText(String.valueOf(counter));
            }
        });
    }
}
