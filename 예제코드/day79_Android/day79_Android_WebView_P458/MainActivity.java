package com.example.p458;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    WebView webView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient());

        // 우리가 만든 JS라는 class를 등록
        webView.addJavascriptInterface(new JS(),"js");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // webView에서 Java 동작하도록

        textView = findViewById(R.id.textView);

        webView.loadUrl("http://m.naver.com"); // App이 network를 통해 왔다갔다 하려함 (보안은 취약)
    }

    final class JS{ // final class는 변경할 수 없는 클래스?
        JS(){}
        @android.webkit.JavascriptInterface // 이 함수는 webkit의 Javascript로 동작
        public void webclick(String str){
            textView.setText(str);
            Toast.makeText(MainActivity.this,""+str,Toast.LENGTH_SHORT).show();
        }
    }
    public void clickBt(View view){
        if(view.getId() == R.id.button){
            webView.loadUrl("http://www.daum.net");
        }else if(view.getId() == R.id.button2){
            webView.loadUrl("http://70.12.60.109/webview");
        }else if(view.getId() == R.id.button3){
            webView.loadUrl("javascript:s('cmd')"); // 함수호출
        }
    }
}
