package com.example.p462;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SeekBar seekBar;
        final TextView textView;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);

        // SeekBar의 움직임을 감지하기 위함
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textView.setText("SeekBar Value : "+i);
                setBright(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this,"start",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this,"stop",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setBright(int value){
        if(value < 10){
            value = 10;
        }
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.screenBrightness = (float)value/10;
        getWindow().setAttributes(params);
    }
}
