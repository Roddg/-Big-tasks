package com.javarush.task.task33.task3310.strategy;

import java.util.HashMap;

public class HashMapStorageStrategy implements StorageStrategy{
    private HashMap<Long, String> data = new HashMap<>();

    @Override
    public boolean containsKey(Long key) {
        return this.data.containsKey(key);
    }

    @Override
    public boolean containsValue(String value) {
        return this.data.containsValue(value);
    }

    @Override
    public void put(Long key, String value) {
        this.data.put(key, value);
    }

    @Override
    public Long getKey(String value) {
        for (Long key : data.keySet())
            if (data.get(key).equals(value))
                return key;

        return null;
    }

    @Override
    public String getValue(Long key) {
        return this.data.get(key);
    }
}
