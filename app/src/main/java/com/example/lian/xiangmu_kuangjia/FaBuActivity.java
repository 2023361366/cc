package com.example.lian.xiangmu_kuangjia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class FaBuActivity extends AppCompatActivity {

    private Button shangchuan;
    private Button quxiao;
    private TextView sportName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fa_bu);

        initView();
    }

    private void initView() {
        shangchuan = ((Button) findViewById(R.id.shangchuan));
        quxiao = ((Button) findViewById(R.id.quxiao));
        sportName = ((TextView) findViewById(R.id.sportName));
    }
}
