package com.example.tapholder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private int counter;
    private TextView counterLabel;
    private Button tapButton;
    private Button toolbarButton;
    private Toolbar toolbar;

    CountDownTimer yourCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counter = 0;
        // Label
        counterLabel = (TextView)findViewById(R.id.counterLabel);
        handleToolbar();
        handleTapButton();
    }

    public void handleToolbar() {
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Tap Holder");

        tapButton = (Button) findViewById(R.id.tapButton);
        toolbarButton = (Button) findViewById(R.id.toolbar_overflow_menu_button);

        toolbarButton.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                counter = 0;
                counterLabel.setText(String.valueOf(counter));
            }
        });
    }

    public void handleTapButton() {
        tapButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    // start your timer

                    yourCountDownTimer = new CountDownTimer(10000, 100){
                        public void onTick(long millisUntilFinished){
                            counterLabel.setText(String.valueOf(counter));
                            counter++;
                        }
                        public  void onFinish(){
                            //counterLabel.setText("FINISH!!");
                        }
                    }.start();

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    // stop your timer.
                    yourCountDownTimer.cancel();
                }
                return false;
            }
        });
    }
}