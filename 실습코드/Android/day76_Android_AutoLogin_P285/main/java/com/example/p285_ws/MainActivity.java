package com.example.p285_ws;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText, editText2;
    CheckBox checkBox;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        checkBox = findViewById(R.id.checkBox);

        // App이 실행되자마자, 자동로그인 체크 여부 검사를 하여
        // 데이터가 저장되어 있으면 로그인 과정이 필요 없으므로 바로 다음 페이지
        SharedPreferences sp = getSharedPreferences("userdata", MODE_PRIVATE);
        if (sp.contains("id") && sp.contains("pwd")) {
            intent = new Intent(getApplicationContext(), Main2Activity.class);
            startActivity(intent);
        }
    }

    // 로그인 버튼을 누르면
    public void clickBt(View view) {
        // 화면 이동
        // 이 때 로그인 정보가 일치해야 다음 화면
        // 그렇지 않으면 경고 메시지 띄우기
        if (editText.getText().toString().equals("ansoh") && editText2.getText().toString().equals("pwd123")) {

            // 자동로그인
            if (checkBox.isChecked() == true) {
                SharedPreferences sp = getSharedPreferences("userdata", MODE_PRIVATE); // 데이터 저장 공간
                SharedPreferences.Editor editor = sp.edit(); // 데이터를 넣거나 수정하는 역할

                editor.putString("id", editText.getText().toString()); // 데이터를 저장
                editor.putString("pwd", editText2.getText().toString());
                editor.commit(); // 데이터를 저장하면 반드시 'commit'을 해줘야 됨
            } else {
                SharedPreferences sp = getSharedPreferences("userdata", MODE_PRIVATE); // 데이터 저장 공간
                SharedPreferences.Editor editor = sp.edit();
                editor.clear(); // 만약 자동로그인 체크를 하지 않으면 저장소를 비워줌 → clear
                editor.commit();
            }

            intent = new Intent(getApplicationContext(), Main2Activity.class);
            // intent는 다음 화면으로 데이터를 전송할 때만 이용
            // 따라서 전송할 데이터가 없으면 저장하지 않아도 됨
            // intent.putExtra("id",R.id.editText);
            // intent.putExtra("pwd",R.id.editText2);
            startActivity(intent);
            //startActivityForResult(intent,100); → 이것도 데이터를 전송할 때만! 없으면 Result 없이 사용
        } else {
            Toast.makeText(this, "아이디 또는 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
        }

    }
}




