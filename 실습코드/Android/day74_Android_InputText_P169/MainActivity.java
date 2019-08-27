package com.example.p169;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText editText; // input message
    TextView textView; // count

   @Override
    protected void onCreate(Bundle savedInstanceState) {
       super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_main);
       setUI();
   }

   private void setUI(){
        // ui and code connection
       editText = findViewById(R.id.editText);
       textView = findViewById(R.id.textView);
       editText.addTextChangedListener(watcher);
   }



    TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            // 글자수 제한하기
            InputFilter[] filter = new InputFilter[1]; // 배열의 크기
            filter[0] = new InputFilter.LengthFilter(80); // 여기에서 []은 index
            editText.setFilters(filter);

            // 입력 중인 텍스트 바이트 수 받아옴
            int currentBytes = charSequence.toString().getBytes().length;
            String txt = String.valueOf(currentBytes) + "/80 바이트";
            textView.setText(txt);
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

    public void onCick(View view) {
        // send 버튼과 close 버튼 나눔 - if문 사용
        if (view.getId() == R.id.sendbt) { // send 버튼 누르면 Toast로 표시
            String message = editText.getText().toString();
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    } else if(view.getId() == R.id.closebt){
            finish();
        }


    }
}
