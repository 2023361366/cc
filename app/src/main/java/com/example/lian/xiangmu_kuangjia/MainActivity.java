package com.example.lian.xiangmu_kuangjia;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioGroup;

import com.example.lian.xiangmu_kuangjia.fragment.Fragment_home;
import com.example.lian.xiangmu_kuangjia.fragment.Fragment_mine;
import com.example.lian.xiangmu_kuangjia.fragment.Fragment_tuijian;
import com.example.lian.xiangmu_kuangjia.fragment.Fragment_xiaoxi;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list_view;
    private RadioGroup radio_check;
    private DrawerLayout drawer;
//    private RadioButton requre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        initview();

        initdate();

    }
    public void my_judge_click(View view){
        Intent intent = new Intent(MainActivity.this,Order_say_Activity.class);
        startActivity(intent);
    }
    public void collection_click(View view){
        Intent intent = new Intent(MainActivity.this,CollectionActivity.class);
        startActivity(intent);
    }
    public void order_click(View view){
        Intent intent = new Intent(MainActivity.this,OrderActivity.class);
        startActivity(intent);
    }

    private void initview() {

        list_view = ((ListView) findViewById(R.id.list_view_drawer));
        radio_check = ((RadioGroup) findViewById(R.id.radio_chekc));
        drawer = ((DrawerLayout) findViewById(R.id.drawer));
//        requre = ((RadioButton) findViewById(R.id.requre));
    }

    private void initdate() {
        list_view.setAdapter(new ArrayAdapter(getApplicationContext(),R.layout.drawer_layout,
                R.id.text_view,new String[]{"item1","item2","item3"}
                ));

        final List<Fragment> list= new ArrayList<Fragment>();
        list.add(new Fragment_home() );
        fragment_(new Fragment_home());
        radio_check.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Fragment fragment =null;
                switch (checkedId){
                    case R.id.tuijian:
                        fragment = new Fragment_tuijian();
                        list.remove(0);
                        list.add(new Fragment_tuijian());
                        break;
                    case R.id.shouye:
                        fragment = new Fragment_home();
                        list.remove(0);
                        list.add(new Fragment_home());
                        break;
                    case R.id.mine:
                      fragment = new Fragment_mine();
                        list.remove(0);
                        list.add(new Fragment_mine());
                        break;
                    case R.id.xiaoxi:
                       fragment = new Fragment_xiaoxi();
                        list.remove(0);
                        list.add(new Fragment_xiaoxi());
                        break;
//                    case R.id.requre:
//                        fragment  = list.get(0);
//                        break;
                }
                fragment_(fragment);
            }
        });

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                drawer.closeDrawers();
            }
        });

//        requre.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.requre_popupwindow,null);
//                PopupWindow popupWindow = new PopupWindow(view, ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT,true);
//                popupWindow.showAtLocation(view,Gravity.BOTTOM,0,400);
//
//            }
//        });
    }

    private void fragment_(Fragment fragment) {
         android.app.FragmentManager ft = getFragmentManager();
         FragmentTransaction fg = ft.beginTransaction();

        Bundle bundle = new Bundle();
        bundle.putString("name","jack");
        fragment.setArguments(bundle);
        fg.replace(R.id.fram,fragment);
        fg.addToBackStack(null);
        fg.commit();
    }
}
