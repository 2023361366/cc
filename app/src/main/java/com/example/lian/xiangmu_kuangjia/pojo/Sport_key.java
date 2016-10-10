package com.example.lian.xiangmu_kuangjia.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lian on 2016/9/21.
 */
public class Sport_key implements Serializable{

    public Integer key;

    public List<Sport> sport;

    public static class Sport{
        public int id;
        public String uname;
        public String sportName;
        public String timeEnd;
        public String timeBegin;
        public String place;
        public String img;
        public String num;
    }
}
