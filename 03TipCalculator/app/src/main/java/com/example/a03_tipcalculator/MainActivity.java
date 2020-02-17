package com.example.a03_tipcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText amountText;

    private TextView percentageLabel;
    private TextView totalLabel;

    double amount = 0;
    double tax = 0.15;
    double total = 0;
    Button toolbar_overflow_menu_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init
        totalLabel = (TextView) findViewById(R.id.totalLabel);
        percentageLabel = findViewById(R.id.topPercentageLabel);
        toolbar_overflow_menu_button = (Button)findViewById(R.id.toolbar_overflow_menu_button);
        toolbar_overflow_menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = 0;
                amountText.setText(String.valueOf(amount));
                totalLabel.setText("");
            }
        });

        // Toolbar
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        amountText = (EditText) findViewById(R.id.amountText);
        amountText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                amount = Double.parseDouble(s.toString());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        // Seekbar
        SeekBar simpleSeekBar=(SeekBar)findViewById(R.id.simpleSeekBar);
        simpleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                percentageLabel.setText(String.valueOf(progressChangedValue));
                calculateTotal(progressChangedValue);
            }
            public void onStartTrackingTouch(SeekBar seekBar) { }
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    public void calculateTotal(double progressChangedValue) {
        totalLabel.setText(String.valueOf(amount + (amount * (progressChangedValue/100))));
    }
}
