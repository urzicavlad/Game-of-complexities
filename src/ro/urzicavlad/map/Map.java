package ro.urzicavlad.map;

import java.util.Set;
import java.util.function.BiConsumer;

public interface Map<K, V> {

    void put(K key, V value);

    boolean remove(K key);

    V get(K key);

    int size();

    boolean isEmpty();

    boolean containsKey(K key);

    boolean containsValue(V value);

    Set<K> keySet();

    Set<Map.Entry<K,V>> entrySet();

    Iterator<K,V> iterator();

    void forEach(BiConsumer<? super K, ? super V> action);

    interface Entry<K, V> {

        K getKey();

        V getValue();

    }
}
