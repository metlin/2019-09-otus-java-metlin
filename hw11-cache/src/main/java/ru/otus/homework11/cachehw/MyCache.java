package ru.otus.homework11.cachehw;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class MyCache<K, V> implements HwCache<K, V> {

  private final Map<K, V> cache = new WeakHashMap<>();
  private final List<HwListener> listeners = new ArrayList<>();

  @Override
  public void put(K key, V value) {
      cache.put(key, value);
  }

  @Override
  public void remove(K key) {
      cache.remove(key);
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
}
