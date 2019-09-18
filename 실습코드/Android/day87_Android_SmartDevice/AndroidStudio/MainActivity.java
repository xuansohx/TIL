package com.example.ws;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText idbox, pwdbox;
    String id, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        idbox = findViewById(R.id.idbox);
        pwdbox = findViewById(R.id.pwdbox);
    }

    public void clickBt(View view){
        // 버튼을 누르면 입력한 아이디와 비밀번호 값을 저장하여
        id = idbox.getText().toString();
        pwd = pwdbox.getText().toString();
        // 서버와 연동하여 확인
        check();
    }

    private void check(){
        // 로그인 정보가 맞는지 서버를 통하여 확인함
        String url = "http://70.12.60.109/webview/login.jsp?id="+id+"&pwd="+pwd;
        HttpTask httpTask = new HttpTask(url);
        httpTask.execute();
    }

    class HttpTask extends AsyncTask<String, Void, String> {
        String url; // url 받음
        public HttpTask(String url) {
            this.url = url;
        } // 여기까지

        // Thread 동작 전
        @Override
        protected void onPreExecute() {

        }

        // Thread 동작
        @Override
        protected String doInBackground(String... strings) {
            String str = HttpHandler.getString(url);
            return str; // 0이나 1을 받아옴 → onPostExecute 여기로 감
        }

        // Thread 동작 후
        @Override
        protected void onPostExecute(String str) {
            Log.d("test",str);
            if(str.equals("0")){
                // 로그인 성공 → 메인 화면으로 이동(Main2Activity)
                Intent intent = new Intent(getApplicationContext(), Main2Activity.class);
                intent.putExtra("id",id); // 화면 이동할 때 아이디 정보 함꼐 전송
                startActivity(intent);
            }else{
                // 로그인 실패
                Toast.makeText(MainActivity.this,"아이디 또는 비밀번호를 확인해주세요.",Toast.LENGTH_SHORT).show();
            }

        }
    }

}
