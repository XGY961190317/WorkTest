package com.example.onetob.worktest;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;


public class ProgressBarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);
        final ProgressBar progressBar = findViewById(R.id.progress);

        new Thread(){
            @Override
            public void run() {
                for (int i=0;i<=100;i++){
                    int sum = (int) (Math.random()*10);
                    progressBar.setProgress(i+sum);
                    try {
                        Thread.sleep(sum*10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        android.widget.SeekBar seekBar = findViewById(R.id.seekBarOne);
        seekBar.setProgress(40);
        seekBar.setMax(100);
        seekBar.setOnSeekBarChangeListener(new android.widget.SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        CheckBox checkBox = findViewById(R.id.mCheckBox);
                checkBox.isChecked();
                checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    }
                });

    }
}
