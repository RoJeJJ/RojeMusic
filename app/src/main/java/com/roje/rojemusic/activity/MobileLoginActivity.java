package com.roje.rojemusic.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bilibili.magicasakura.widgets.TintToolbar;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.roje.rojemusic.R;
import com.roje.rojemusic.bean.login.LoginRootBean;
import com.roje.rojemusic.fragment.dialog.LoadingDialogFragment;
import com.roje.rojemusic.present.MyObserver;
import com.roje.rojemusic.present.Presenter;
import com.roje.rojemusic.present.impl.PresenterImpl;
import com.roje.rojemusic.utils.DisplayUtil;
import com.roje.rojemusic.utils.EncryptUtils;
import com.roje.rojemusic.utils.LogUtil;
import com.roje.rojemusic.utils.Md5Util;
import com.roje.rojemusic.utils.SharedPreferencesUtil;
import com.roje.rojemusic.utils.StatusBarUtil;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MobileLoginActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    TintToolbar toolbar;
    @BindView(R.id.mobile_login_et_name)
    EditText name;
    @BindView(R.id.mobile_login_et_psw)
    EditText psw;
    @BindView(R.id.login)
    Button login;
    @BindView(R.id.head)
    TextView head;
    private int paddingLeft = 0;
    private Presenter presenter;
    private DialogFragment dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cell_phone_login);
        ButterKnife.bind(this);
        initToolbar();
        initViews();
        dialog = new LoadingDialogFragment();
        presenter = new PresenterImpl();
    }

    @Override
    protected void onStart() {
        super.onStart();
        head.post(new Runnable() {
            @Override
            public void run() {
                if (paddingLeft == 0) {
                    paddingLeft = name.getPaddingLeft() + head.getMeasuredWidth() + DisplayUtil.dp2px(MobileLoginActivity.this, 2);
                    name.setPadding(paddingLeft,
                            name.getPaddingTop(), name.getPaddingRight(), name.getPaddingBottom());
                }else
                    name.setPadding(paddingLeft, name.getPaddingTop(), name.getPaddingRight(), name.getPaddingBottom());
            }
        });
    }

    private void initViews() {
        String number = SharedPreferencesUtil.getPhoneNumber(this);
        if (number != null)
            name.setText(number);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNum = name.getText().toString();
                String password = psw.getText().toString();
                if (TextUtils.isEmpty(phoneNum))
                    Toast.makeText(MobileLoginActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                else if (!TextUtils.isDigitsOnly(phoneNum))
                    Toast.makeText(MobileLoginActivity.this,"请输入手机号",Toast.LENGTH_SHORT).show();
                else if (phoneNum.length() != 11)
                    Toast.makeText(MobileLoginActivity.this,"请输入11位手机号",Toast.LENGTH_SHORT).show();
                else if (TextUtils.isEmpty(password))
                    Toast.makeText(MobileLoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                else {
                    dialog.show(getSupportFragmentManager(),"loading_dialog");
                    JsonObject object = new JsonObject();
                    object.addProperty("phone",phoneNum);
                    object.addProperty("password", Md5Util.md5(password));
                    object.addProperty("rememberLogin",true);
                    Map<String,String> form = EncryptUtils.encrypt(object.toString());
                    presenter.login(form, new MyObserver<LoginRootBean>(MobileLoginActivity.this) {
                        @Override
                        protected void next(LoginRootBean s) {
                            dialog.dismiss();
                            SharedPreferencesUtil.setUerId(MobileLoginActivity.this,s.getProfile().getUserId());
                            SharedPreferencesUtil.setPrePhoneNumber(MobileLoginActivity.this,name.getText().toString());
                            Intent i = new Intent(MobileLoginActivity.this,MainActivity.class);
                            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(i);
                        }

                        @Override
                        protected void error() {
                            dialog.dismiss();
                        }
                    });
                }

            }
        });
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (TextUtils.isEmpty(s))
                    head.setTextColor(name.getHintTextColors().getDefaultColor());
                else
                    head.setTextColor(name.getTextColors().getDefaultColor());
            }
        });
    }

    private void initToolbar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT)
            toolbar.setPadding(0, StatusBarUtil.getStatusBarHeight(this),0,0);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("tag","0000");
                onBackPressed();
            }
        });
    }
}
