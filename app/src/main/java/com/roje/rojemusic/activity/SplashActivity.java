package com.roje.rojemusic.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.roje.rojemusic.R;
import com.roje.rojemusic.utils.EncryptUtils;
import com.roje.rojemusic.utils.GzipUtils;
import com.roje.rojemusic.utils.Md5Util;
import com.roje.rojemusic.utils.SharedPreferencesUtil;
import com.roje.rojemusic.utils.Utils;

import org.apache.http.util.CharArrayBuffer;

import java.io.IOException;
import java.util.List;
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
//        Log.i("decode",Utils.decode("GwNLKU5dTRgSS0NJWQhtX1JbBVhFd0cfWkhDXTlGUkZQDFx0W0pbGgtEaV8eXBQFBywNTUNLRlorCxc="));
//        Log.i("decode",Utils.decode("Mw8PBxw="));
//        Log.i("decode",Utils.decode("IBYTGwsV"));
//        Log.i("decode",Utils.decode("IQEOExAe"));
//        Log.i("decode",Utils.decode("NQ8XGg=="));
//        Log.i("decode",Utils.decode("NgsABwsV"));
//        Log.i("decode",Utils.decode("NwsQHRUFACwBDQ=="));
//        Log.i("decode",Utils.decode("JgYCHBcVGA=="));
//        Log.i("decode",Utils.decode("MwsRARAfGiYBBxc="));
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
        new Thread(){
            @Override
            public void run() {
                Log.i("thread","start");
                OkHttpClient client = new OkHttpClient();
                String url = "http://music.163.com/eapi/batch";
                String data = "{\"e_r\":\"false\"}";
                Map<String,String> form = EncryptUtils.encrypt(data);
//                Log.i("params",form.get("params"));
//                Log.i("key",form.get("encSecKey"));
                RequestBody body = new FormBody.Builder()
                .add("Accept", "*/*")
//                .add("Accept-Encoding", "gzip,deflate,sdch")
                .add("Accept-Language", "zh-CN,zh;q=0.8,gl;q=0.6,zh-TW;q=0.4")
                .add("Connection", "keep-alive")
                .add("Host", "music.163.com")
                .add("Referer", "http://music.163.com/search/")
                .add("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
                .add("Cookie", "appver=5.0.0")
                        .addEncoded("params","C561B86F974CBA39FB9AB3222AC3E77103E75A6A409D4B3CC17C4A7671C9FAD0407133E9B83DFDF61604D687AD3EA56B99463D945D7C0ECFE3D5F2D6581DEA6C04B81515063822F8E0C5F828DA9E1035C98CB815C10DD162386A422B14AFE24459F340623E5BEC8CEE0D77E27C1DC443918481AA2002DD3D51C739ED08F536912CDB084B84A3804E7AFD294D9BB92D846E111D0BFCCF48848D45F75492AF575EC8F72DDFB6970A0880663A0C279E8BE881FD1F9228971E49689A66342981180E9AF8F899366D852396BAD1404C7607A0D6DC644B6E844D4F8319190CD2A9413CE1E967B9575A3ACBDA21C36BB1939875F75947D0F46FE58B478C82B047DC1FA52B2D53EA3993A31D7C067C53AD6E4CBF4817CDE4B4348365EDAA82E0CF38A65B6BECBA6CC8C23F6C24624D3C580CFB0951F2F5867563697728F3B9E206E6DC0095B1861C3D655260F66ABFFD28B5F46A9E8B06AA2976562809B8FFE33FF114A449DFC66A59D6CC421B4AC36DC7236B467EC7FA9D937C201F894374C0C30F775401E5AE53BFD4291206B4A5231570905FF9219728DBEB766E9A5CB1DE0008CD2299969B1AED831B9FC9D1D6390B372648F7AF8E36521410F8A06B51092A45BD0ECF65309DE83A7BFC8C473E54ED5D6C6E10290B1A3C6D41AC6C0F93CE1EB6C08228B56ACD49F6B8903BC240D567CF72A9493C53DC96B8E09B584573A32422066E79672DC26D962F519580B04CF58F3C60F3B4A7D0E2C1A8B567732BF206761288B3DB2DA72EBB1BDD82728369DA0E3046CD8E2ED41D3572BCF7F8C01660F3BF3713202E6BEBB793F14CF45C5EA793EDEDABD3CFDB7F9B97A03E70EA371668EA8CFBA0CA71AB58A352D369B27DA56C94733B7E8C7B15C6B1F0764A1CD298039BF6F8EB9A21305AEE331D43DD9B57379F5D962CF2C76B571A6874F024C3D12052693B298408BD8A2309EC87914DD8AA6EABFC7CE2355A162C43BB956AFF57E5A8333766EA206AE952BDD0640428D32D23FD6E6A4CD22972711C9B976BC729F7537376F649C23DDC1D598A505A52C15B19B8EE238FDDAC5A98BD847C291627EC46C650548EA06AB920A4D659800742B208AC7D71F0805DDA5CEA3EB679C4A10DF802FDBB9FAC2FE8DF78970E1222BE26A86F53735006FD471F3A7885B57C0F826CBBB28987EB372FB9EB6A487DB4C3D3014584D12DE261915E069DD4FCA4EE4C1BC3526F12B200BFCFC64DF90BFA51F0ABEA")
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .post(body)
                        .build();
                Call call = client.newCall(request);
                try {
                    Response response = call.execute();
                    Log.i("Response", response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
