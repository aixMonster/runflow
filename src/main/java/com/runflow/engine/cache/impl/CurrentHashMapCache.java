package com.runflow.engine.cache.impl;

import com.runflow.engine.ExecutionEntity;
import com.runflow.engine.cache.Session;
import com.runflow.engine.utils.CollectionUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public  class  CurrentHashMapCache<V extends ExecutionEntity> extends ConcurrentHashMap<String, List<V>> {


    public synchronized List<V> putSingle(V v) {
        return this.putSingle(v.getSerialNumber(), v);
    }


    @Override
    public List<V> put(String key, List<V> value) {
        return super.put(key, value);
    }

    public synchronized List<V> putSingle(String key, V value) {
        List<V> vs = this.get(key);
        if (CollectionUtil.isEmpty(vs)) {
            List<V> objects = new ArrayList<>();
            objects.add(value);
            return super.put(key, objects);
        }
        V byId = this.getById(key, value.getId());
        if (byId != null) {
            return null;
        } else {
            vs.add(value);
        }
        return super.put(key, vs);
    }

    public V findInCache(String key, String id) {
        return this.getById(key, id);
    }


    public List<V> findInCache(String key) {
        return this.get(key);
    }

    protected synchronized V getById(String uuid, String id) {
        return this.getById(this.get(uuid), id);

    }


    protected synchronized V getById(List<V> vs, String id) {
        for (V v : vs) {
            if (v.getId().equals(id)) {
                return v;
            }
        }
        return null;
    }


}