package ro.urzicavlad.map;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

@SuppressWarnings("unchecked")
public class MapImpl<K, V> implements Map<K, V> {

    private Entry<K, V>[] buckets;
    private static final int DEFAULT_CAPACITY = 16;
    private int size;


    public MapImpl(int initialCapacity) {
        buckets = new Entry[initialCapacity];
    }

    public MapImpl() {
        this(DEFAULT_CAPACITY);
    }


    @Override
    public void put(K key, V value) {
        ensureCapacity();
        for (int i = 0; i < buckets.length; i++) {
            int hash = getHash(key, i);
            if (buckets[i] != null && buckets[i].getKey().equals(key)) {
                buckets[i].setValue(value);
                return;
            }
            if (buckets[hash] == null) {
                buckets[hash] = new Entry<>(key, value, i);
                size++;
                break;
            }
        }
    }

    @Override
    public boolean remove(K key) {
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                if (buckets[i].getKey().equals(key)) {
                    buckets[i] = null;
                    size--;
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        for (Entry<K, V> entry : buckets) {
            if (entry != null) {
                if (entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        for (Entry<K, V> bucket : buckets) {
            if (bucket != null) {
                if (bucket.getKey().equals(key)) return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (Entry<K, V> bucket : buckets) {
            if (bucket != null) {
                if (bucket.getValue().equals(value)) return true;
            }
        }
        return false;
    }

    @Override
    public Set<K> keySet() {
        Set<K> kSet = new HashSet<>();
        for (Entry<K, V> bucket : buckets) {
            if (bucket != null) {
                kSet.add(bucket.getKey());
            }
        }
        return kSet;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> eSet = new HashSet<>();
        for (Entry<K, V> bucket : buckets) {
            if (bucket != null) {
                eSet.add(bucket);
            }
        }
        return eSet;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public Iterator<K, V> iterator() {
        return new Iterator<K, V>() {

            private int index = 0;

            @Override
            public Map.Entry<K, V> next() {
                return buckets[index++];
            }

            @Override
            public boolean hasNext() {
                if (buckets[index] == null) index++;
                return buckets[index] != null;
            }
        };
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        for (Map.Entry<K, V> entry : entrySet()) {
            K key = entry.getKey();
            V value = entry.getValue();
            action.accept(key, value);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (final Entry<K, V> bucket : buckets) {
            if (bucket != null) sb.append(bucket).append(",");
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        return "{" + sb + "}";
    }

    private int getHash(K key, int i) {
        return Math.abs((key.hashCode() + i) % buckets.length);
    }

    private void ensureCapacity() {
        final int NEW_SIZE = buckets.length * 2;
        Entry<K, V>[] entries = new Entry[NEW_SIZE];
        if (this.size == this.buckets.length) {
            System.arraycopy(buckets, 0, entries, 0, buckets.length);
            this.buckets = entries;
        }
    }

    static class Entry<K, V> implements Map.Entry<K, V> {

        private K key;
        private V value;
        private int position;

        Entry(K key, V value, int position) {
            this.key = key;
            this.value = value;
            this.position = position;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public int getPosition() {
            return position;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public void setPosition(int position) {
            this.position = position;
        }

        @Override
        public String toString() {
            return "[K=" + key + ", V=" + value + "]";
        }
    }
}
