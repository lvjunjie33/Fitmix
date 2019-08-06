package com.business.bbs.base;

import java.util.HashMap;

/**
 * Created by fenxio on 2016/8/24.
 * 扩展 HashMap
 */
public class QxMap<K,V> extends HashMap<K,V> {
    public QxMap(){}
    public QxMap(K key,V value){
        this.put(key,value);
    }
    public QxMap<K,V> add(K key,V value){
        this.put(key,value);
        return this;
    }
    public QxMap<K,V> delete(K key){
        this.remove(key);
        return this;
    }
}
