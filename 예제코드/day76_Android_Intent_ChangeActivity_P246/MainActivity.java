package com.example.p246;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickBt(View view){
        // 액티비티 간 화면 이동
        Intent intent = new Intent(MainActivity.this,Main2Activity.class);
        // MainActivity.this 대신 getApplicationContext() 써도 됨
        intent.putExtra("num",100);
        intent.putExtra("str","Hi, ANSOH");
        startActivity(intent);
        // setContentView(R.layout.activity_main2);
    }

}
