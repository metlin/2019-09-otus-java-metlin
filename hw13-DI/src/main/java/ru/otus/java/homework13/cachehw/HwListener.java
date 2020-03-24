package ru.otus.java.homework13.cachehw;

public interface HwListener<K, V> {
  void notify(K key, V value, String action);
}
