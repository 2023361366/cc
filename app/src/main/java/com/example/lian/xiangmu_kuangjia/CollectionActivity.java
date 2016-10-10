package com.example.lian.xiangmu_kuangjia;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.RadioGroup;

import com.example.lian.xiangmu_kuangjia.fragment.Fragment_object;
import com.example.lian.xiangmu_kuangjia.fragment.Fragment_sport;
import com.example.lian.xiangmu_kuangjia.fragment.Fragment_story;

public class CollectionActivity extends AppCompatActivity {

    private RadioGroup radio_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        intview();

        initdate();
    }



    private void intview() {
        radio_check = ((RadioGroup) findViewById(R.id.radio_check_collection));
    }

    private void initdate() {
        check_fragment(new Fragment_sport());
        radio_check.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment fragment=null;
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.sport:
                        fragment = new Fragment_sport();
                        break;
                    case R.id.object:
                        fragment = new Fragment_object();
                        break;
                    case R.id.story:
                        fragment = new Fragment_story();
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
