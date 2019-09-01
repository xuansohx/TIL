package com.example.p212;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String appID;
    EditText editText;
    Button button,button2;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        // Bundle : 앱이 살아있는 동안 잠시 정보를 어딘가(?) 저장
        // → 화면이 움직이거나, 홈버튼 눌러서 잠시 나갔다왔을 때 임시저장됨
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = editText.getText().toString();
                savedInstanceState.putString("save",data);
            }
        });

        button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String data = null;
                if(savedInstanceState != null){
                    data = savedInstanceState.getString("ID");
                }else{
                    data = "Empty";
                }
                Toast.makeText(MainActivity.this,data, Toast.LENGTH_SHORT).show();

            }
        });
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(MainActivity.this,"onSaveInstanceState", Toast.LENGTH_SHORT).show();
     //   outState.putString("ID",appID);
          outState.putString("ID","ID01");
    }
}
