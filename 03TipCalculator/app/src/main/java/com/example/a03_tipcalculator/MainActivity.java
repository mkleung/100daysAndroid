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

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText amountText;

    private TextView percentageLabel;
    private TextView totalLabel;

    double amount = 0;
    double taxPercent = 15;
    double total = 0;
    Button toolbar_overflow_menu_button;

    SeekBar simpleSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // init
        totalLabel = (TextView) findViewById(R.id.totalLabel);
        percentageLabel = findViewById(R.id.topPercentageLabel);

        // Reset
        toolbar_overflow_menu_button = (Button)findViewById(R.id.toolbar_overflow_menu_button);
        toolbar_overflow_menu_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                amount = 0;
                amountText.setText(String.valueOf(amount));
                totalLabel.setText("");

                calculateTotal(0);

                simpleSeekBar.setProgress(0);
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
                calculateTotal(taxPercent);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

        // Seekbar
        simpleSeekBar=(SeekBar)findViewById(R.id.simpleSeekBar);
        simpleSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = (int) Math.round(taxPercent);
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
                calculateTotal(progressChangedValue);
            }
            public void onStartTrackingTouch(SeekBar seekBar) { }
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    public void calculateTotal(double taxValue) {
        taxPercent = taxValue;
        total = amount + (amount * (taxPercent/100));
        percentageLabel.setText(String.valueOf(taxPercent));
        //totalLabel.setText(String.valueOf(total));
        totalLabel.setText(String.format(Locale.CANADA, "%.2f", total));
    }
}
