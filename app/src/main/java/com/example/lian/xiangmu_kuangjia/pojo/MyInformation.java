package com.example.lian.xiangmu_kuangjia.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lian on 2016/9/24.
 */
public class MyInformation implements Serializable{
    public  String key;

    public List<Myself> myself;

    public static class Myself{
        public int id;
        public Integer judgeNum;
        public Integer fansNum;
        public Integer followNum;
        public String backgroundId;
        public String headId;
        public Integer likeNum;
        public String psw;
    }
}
