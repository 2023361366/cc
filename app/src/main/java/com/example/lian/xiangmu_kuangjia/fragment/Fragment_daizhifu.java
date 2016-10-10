package com.example.lian.xiangmu_kuangjia.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lian.xiangmu_kuangjia.R;

/**
 * Created by lian on 2016/9/19.
 */
public class Fragment_daizhifu extends Fragment {
   private TextView home;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daizhifu,null);

        return view;
    }

}