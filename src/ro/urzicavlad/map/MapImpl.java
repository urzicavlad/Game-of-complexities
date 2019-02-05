package ro.urzicavlad.map;

import java.util.Objects;
import java.util.Set;
import java.util.function.BiConsumer;

public class MapImpl<K, V> implements Map<K, V> {

    private Entry<K, V> buckets[];
    private static final int DEFAULT_CAPACITY = 10;
    private int size;

    public MapImpl() {
        this(DEFAULT_CAPACITY);
    }

    public MapImpl(int capacity) {
        this.buckets = new Entry[capacity];
    }

    @Override
    public void put(K key, V value) {
        Entry<K, V> entry = new Entry<>(key, value, null);
        int bucket = getHash(key) % buckets.length;

        Entry<K, V> existing = buckets[bucket];
        if (existing == null) {
            buckets[bucket] = entry;
            size++;
        } else {
            // compare the keys see if key already exists
            while (existing.next != null) {
                if (existing.key.equals(key)) {
                    existing.value = value;
                    return;
                }
                existing = existing.next;
            }

            if (existing.key.equals(key)) {
                existing.value = value;
            } else {
                existing.next = entry;
                size++;
            }
        }
    }

    @Override
    public void remove(K key) {

    }

    @Override
    public V get(K key) {
        Entry<K, V> bucket = buckets[getHash(key) % buckets.length];
        while (bucket != null) {
            if (key == bucket.key) {
                return bucket.value;
            }
            bucket = bucket.next;
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        return false;
    }

    @Override
    public Set<K> keySet() {
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return null;
    }

    @Override
    public int size() {
        return this.size;
    }

    public void forEach(BiConsumer<? super K, ? super V> action) {
        Objects.requireNonNull(action);
        for (Map.Entry<K, V> entry : entrySet()) {
            K k;
            V v;
            k = entry.getKey();
            v = entry.getValue();
            action.accept(k, v);
        }
    }

    private int getHash(K key) {
        return key == null ? 0 : Math.abs(key.hashCode());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Entry entry : buckets) {
            sb.append("[");
            while (entry != null) {
                sb.append(entry);
                if (entry.next != null) {
                    sb.append(", ");
                }
                entry = entry.next;
            }
            sb.append("]");
        }
        return "{" + sb.toString() + "}";
    }

    static class Entry<K, V> implements Map.Entry {
        private final K key;
        private V value;
        private Entry<K, V> next;

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (o instanceof Entry) {
                Entry entry = (Entry) o;
                return Objects.equals(key, entry.key) &&
                        Objects.equals(value, entry.value) &&
                        Objects.equals(next, entry.next);
            }
            return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value, next);
        }

        @Override
        public String toString() {
            return "{" + key + ", " + value + "}";
        }
    }
}
