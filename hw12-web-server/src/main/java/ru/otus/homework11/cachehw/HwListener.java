package ru.otus.homework11.cachehw;

public interface HwListener<K, V> {
  void notify(K key, V value, String action);
}
