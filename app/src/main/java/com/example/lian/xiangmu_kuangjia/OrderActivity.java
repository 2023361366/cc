package com.example.lian.xiangmu_kuangjia;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.example.lian.xiangmu_kuangjia.fragment.Fragment_daizhifu;
import com.example.lian.xiangmu_kuangjia.fragment.Fragment_jinxing;
import com.example.lian.xiangmu_kuangjia.fragment.Fragment_tuikuan;
import com.example.lian.xiangmu_kuangjia.fragment.Fragment_wancheng;

public class OrderActivity extends AppCompatActivity {
    private RadioGroup radio_check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        intview();

        initdate();
    }
    private void intview() {
        radio_check = ((RadioGroup) findViewById(R.id.radio_check_collection));
    }
    private void initdate() {
        check_fragment(new Fragment_jinxing());
        radio_check.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment fragment=null;
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.jinxing:
                        fragment = new Fragment_jinxing();
                        break;
                    case R.id.wancheng:
                        fragment = new Fragment_wancheng();
                        break;
                    case R.id.daizhifu:
                        fragment = new Fragment_daizhifu();
                        break;
                    case R.id.tuikuan:
                        fragment = new Fragment_tuikuan();
                        break;
                }
                check_fragment(fragment);
            }
        });
    }
    public void check_fragment(Fragment fragment){
        android.app.FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();


        ft.replace(R.id.frameLayout,fragment);
        ft.commit();
    }
}
