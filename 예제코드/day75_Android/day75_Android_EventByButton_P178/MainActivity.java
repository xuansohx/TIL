package com.example.p178;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity
implements View.OnClickListener{
    Button bt;
    RadioButton radioButton, radioButton2;
    CheckBox checkBox, checkBox2;
    Switch switch1;
    ToggleButton toggleButton;
    TextView textView2, editText, editText2, editText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Buttons
        bt = findViewById(R.id.button2);
        radioButton = findViewById(R.id.radioButton);
        radioButton2 = findViewById(R.id.radioButton2);
        checkBox = findViewById(R.id.checkBox);
        checkBox2 = findViewById(R.id.checkBox2);
        toggleButton = findViewById(R.id.toggleButton);
        switch1 = findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b == true) {
                    Toast.makeText(MainActivity.this,"Switch",Toast.LENGTH_SHORT).show();
                    // 이 경우 상위 클래스를 쓰고 써줘야 toast 띄우기 가능
                    bt.setBackgroundColor(Color.RED);
                } else {
                    bt.setBackgroundColor(Color.BLUE);
                }
            }
        });

        bt.setOnClickListener((this));
        /* button에 event가 들어오기를 기다림
           button에 event가 들어오면 'this'가 처리함
           여기에서 'this'는 MainActivity로 onClick 함수가 처리 */

        // EditText
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if(b == true){
                    editText.setHint("typing your name");
                }else{
                    editText.setHint("");
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
        Toast.makeText(this,"Click:"+checkBox.isChecked()
                +radioButton.isChecked(),Toast.LENGTH_SHORT).show();
    }
}
