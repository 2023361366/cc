package com.example.lian.xiangmu_kuangjia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lian.xiangmu_kuangjia.pojo.Constent;
import com.example.lian.xiangmu_kuangjia.pojo.Sport_name;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText user_name;
    private EditText use_psw;
    private Button user_login;
    private Button user_add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user_name = ((EditText) findViewById(R.id.user_name));
        use_psw = ((EditText) findViewById(R.id.user_psw));
        user_login = ((Button) findViewById(R.id.user_login));
        user_add = ((Button) findViewById(R.id.user_add));

        user_login.setOnClickListener(this);
        user_add.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_login:
                user_login();
                break;
            case R.id.user_add:
                user_add();
                break;
        }
    }

    private void user_login() {
        String name = null;
        try {
            name = URLEncoder.encode(user_name.getText().toString().trim(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String psw = null;
        try {
            psw = URLEncoder.encode(use_psw.getText().toString().trim(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println(Constent.URL+"ww/getInformation?name="+name+"&psw="+psw+"");

           RequestParams params = new RequestParams(Constent.URL+"ww/getInformation?name="+name+"&psw="+psw+"");

        final String finalName = name;
        x.http().get(params, new Callback.CacheCallback<String>() {
                @Override
                public void onSuccess(String result) {

                    if (result.length() ==0) {
                        Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_LONG).show();
                    } else {
                        Sport_name.NAME=finalName;
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    }
                }
                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(Callback.CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }

                @Override
                public boolean onCache(String result) {
                    return false;
                }
            });
    }
    private void user_add() {
        Intent inten = new Intent(LoginActivity.this,AddActivity.class);
        startActivity(inten);
    }
}
