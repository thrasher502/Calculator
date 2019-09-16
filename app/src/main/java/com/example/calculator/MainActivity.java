package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import java.text.NumberFormat;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
    private static final NumberFormat percentFormat = NumberFormat.getPercentInstance();

    private double billAmount = 0.0;
    private double customPercent = 0.18;
    private TextView amountDisplayTextView;
    private TextView percentCustomTextView;
    private TextView customTextView;
    private TextView tip15TextView;
    private TextView tipCustomTextView;
    private TextView total15TextView;
    private TextView totalCustomTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amountDisplayTextView=(TextView) findViewById(R.id.amountDisplayTextView);
        percentCustomTextView = (TextView) findViewById(R.id.percentCustomTextView);
        customTextView = (TextView)findViewById(R.id.customPercentTextView);
        tip15TextView=(TextView)findViewById(R.id.tip15TextView);
        tipCustomTextView=(TextView)findViewById(R.id.tipCustomTextView);
        total15TextView=(TextView)findViewById(R.id.total15TextView);
        totalCustomTextView=(TextView) findViewById(R.id.totalCustomTextView);
        amountDisplayTextView.setText(currencyFormat.format(billAmount));

        updateStandard();
        updateCustom();

        EditText amountEditText = (EditText)findViewById(R.id.amountEditText);
        amountEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try{
                    billAmount = Double.parseDouble(s.toString());
                    amountDisplayTextView.setText(currencyFormat.format(billAmount));
                    updateCustom();
                    updateStandard();
                }

                catch (NumberFormatException e) {
                    billAmount = 0.0;
                }
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });


        SeekBar customTipSeekBar = (SeekBar) findViewById(R.id.customTipSeekBar);
        customTipSeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                customPercent = progress/100.0;
                percentCustomTextView.setText(percentFormat.format(customPercent));
                updateCustom();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void updateStandard(){

        double fifteenPercentTip = billAmount*0.15;
        double fifteenPercentTotal = billAmount + fifteenPercentTip;

        tip15TextView.setText(currencyFormat.format(fifteenPercentTip));
        total15TextView.setText(currencyFormat.format(fifteenPercentTotal));
    }

    private void updateCustom(){
        double customPercentTip = billAmount * customPercent;
        double customPercentTotal = billAmount+customPercentTip;


        tipCustomTextView.setText(currencyFormat.format(customPercentTip));
        totalCustomTextView.setText(currencyFormat.format(customPercentTotal));
    }



}
