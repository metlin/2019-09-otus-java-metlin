package ru.otus.java.homework13.cachehw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V> {

    private final Map<K, V> cache = new WeakHashMap<>();
    private final List<HwListener> listeners = new ArrayList<>();
    private final int MAX_CACHE_SIZE = 500;

    @Override
    public void put(K key, V value) {
        if (cache.size() > MAX_CACHE_SIZE) {
            cache.clear();
        }

        cache.put(key, value);
        checkListener(key, value, "put");
    }

    @Override
    public void remove(K key) {
        V value = cache.remove(key);
        checkListener(key, value, "remove");
    }

    @Override
    public V get(K key) {
        return cache.get(key);
    }

    @Override
    public void addListener(HwListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListener(HwListener listener) {
        listeners.remove(listener);
    }

    private void checkListener(K key, V value, String action) {
        if(!listeners.isEmpty()) {
            listeners.get(0).notify(key, value, action);
        }
    }
}
