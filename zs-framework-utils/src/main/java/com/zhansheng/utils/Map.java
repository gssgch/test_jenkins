package com.zhansheng.utils;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author
 * @description
 * @date 2019/6/25
 */

public class Map {

    //自定义map集合   不会有重复的键
    private class myMap<K,V> {
        private HashMap<K,ArrayList<V>> data =new HashMap<>();
        public boolean add(K k, V v) {
            if (data.containsKey(k)) {
                data.get(k).add(v);
            } else {
                ArrayList<V> list = new ArrayList<>();
                list.add(v);
                data.put(k,list);
            }
            return true;
        }
        public HashMap<K,ArrayList<V>> getData() {
            return data;
        }
    }


}
