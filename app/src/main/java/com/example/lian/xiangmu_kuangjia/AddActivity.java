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

public class AddActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText add_name;
    private EditText add_psw;
    private Button add_sure;
    private Button add_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        add_name = ((EditText) findViewById(R.id.add_name));
        add_psw = ((EditText) findViewById(R.id. add_psw));

        add_sure = ((Button) findViewById(R.id.add_sure));
        add_sure.setOnClickListener(this);
        add_back = ((Button) findViewById(R.id.add_back));
        add_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_sure:
                add_sure();
                break;
            case R.id.add_back:
                add_back();
                break;
        }
    }

    private void add_back() {
        Intent intent = new Intent(AddActivity.this,LoginActivity.class);
        startActivity(intent);
    }

    private void add_sure() {
        String name=null;
        String psw=null;
        try {
            name = URLEncoder.encode(add_name.getText().toString().trim(),"utf-8");
            psw = URLEncoder.encode(add_psw.getText().toString().trim(),"utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        RequestParams params = new RequestParams(Constent.URL+"ww/getInformation?name="+name+"&psw="+psw+"");

        final String finalName = name;
        final String finalPsw = psw;
        x.http().get(params, new Callback.CacheCallback<String>() {
            @Override
            public void onSuccess(String result) {

                if (result.length()>0) {
                    Toast.makeText(AddActivity.this, "用户名已存在", Toast.LENGTH_LONG).show();
                } else {

                    RequestParams p = new RequestParams(Constent.URL+"ww/addInformtion?name=" + finalName + "&psw=" + finalPsw + "");
                x.http().get(p, new Callback.CacheCallback<String>() {
                @Override
                public void onSuccess(String result) {

                  if(result.length()>0){
                      Sport_name.NAME=finalName;
                      Intent intent = new Intent(AddActivity.this,MainActivity.class);
                      startActivity(intent);
                  }

                }
                @Override
                public void onError(Throwable ex, boolean isOnCallback) {

                }

                @Override
                public void onCancelled(CancelledException cex) {

                }

                @Override
                public void onFinished() {

                }

                @Override
                public boolean onCache(String result) {
                    return false;
                }
            });
//
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
}
