package com.example.martin.homework;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.jetbrains.annotations.Nullable;
import org.w3c.dom.Text;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Activity activity;

    TextView Texter;
    SeekBar Sizer;
    Button Setter;
    EditText Entry;

    @Override
    protected void onCreate(@Nullable Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_main);
        activity = this;

        Texter = findViewById(R.id.Texter);
        Sizer = findViewById(R.id.Sizer);
        Setter = findViewById(R.id.Setter);
        Entry = findViewById(R.id.Entry);

        Texter.setText("Hello there");

        Setter.setOnClickListener(this);

        Sizer.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                setText();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.Setter:
                Texter.setText(Entry.getText());
                break;
        }
    }

    public boolean setText() {
        int a = Sizer.getProgress();
        Texter.setTextSize(a);
        return true;
    }
}
