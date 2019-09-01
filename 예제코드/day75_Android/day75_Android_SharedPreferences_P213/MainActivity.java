package com.example.p213;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickbt(View view){
        if(view.getId() == R.id.button){
            // save
            sp = getSharedPreferences("ma",MODE_PRIVATE);
            // PRIVATE으로 설정하면 저장된 데이터 나 밖에 못 봄
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("key01","id01");
            editor.commit();
        }else if(view.getId() == R.id.button2){
            // load
            sp = getSharedPreferences("ma",MODE_PRIVATE);
            String id = sp.getString("key01","default");
            Toast.makeText(this,id,Toast.LENGTH_SHORT).show();
        }
    }
}
