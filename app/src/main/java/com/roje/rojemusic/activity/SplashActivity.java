package com.roje.rojemusic.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.roje.rojemusic.R;
import com.roje.rojemusic.utils.EncryptUtils;
import com.roje.rojemusic.utils.Md5Util;
import com.roje.rojemusic.utils.SharedPreferencesUtil;
import com.roje.rojemusic.utils.Utils;

import java.io.IOException;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class SplashActivity extends Activity {

    @BindView(R.id.splash)
    RelativeLayout splash;
    @BindView(R.id.skip)
    Button skip;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            overridePendingTransition(R.anim.activity_enter,R.anim.activity_exit);
            finish();
            return true;
        }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        splash.setBackgroundResource(R.drawable.start_i0);
        handler.sendEmptyMessageDelayed(0,5000);
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.removeMessages(0);
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                overridePendingTransition(R.anim.activity_enter,R.anim.activity_exit);
                finish();
            }
        });
        SharedPreferencesUtil.setUerId(this,76627798);
//        new Thread(){
//            @Override
//            public void run() {
//                OkHttpClient client = new OkHttpClient();
//                String data = "{\"phone\":\"18665115101\",\"password\":\""+ Md5Util.md5("jiejie1987")+"\",\"rememberLogin\": \"true\"}";
//                Map<String,String> form = EncryptUtils.encrypt(data);
//                RequestBody body = new FormBody.Builder()
//                        .add("params",form.get("params"))
//                        .add("encSecKey",form.get("encSecKey"))
//                        .build();
//                Request request = new Request.Builder()
//                        .url("http://music.163.com/weapi/login/cellphone?csrf_token=")
//                        .post(body)
//                        .build();
//                Call call = client.newCall(request);
//                try {
//                    Response response = call.execute();
//                    Log.i("Response",response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }.start();
    }
}
