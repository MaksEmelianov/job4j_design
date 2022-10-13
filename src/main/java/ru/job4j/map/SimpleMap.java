package ru.job4j.map;

import java.util.*;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        boolean rsl = false;
        if ((float) count / capacity >= LOAD_FACTOR) {
            expand();
        }
        int tmpIndex = getIndex(key);
        if (table[tmpIndex] == null) {
            table[tmpIndex] = new MapEntry<>(key, value);
            rsl = true;
            count++;
            modCount++;
        }
        return rsl;
    }

    private int getIndex(K key) {
        return Objects.hashCode(key) == 0
                ? 0 : indexForHash(hash(key.hashCode()));
    }

    private int hash(int hashCode) {
        return hashCode == 0 ? 0 : hashCode ^ (hashCode >>> 16);
    }

    private int indexForHash(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity = capacity << 1;
        MapEntry<K, V>[] newTable = new MapEntry[capacity];
        MapEntry<K, V>[] oldTable = table;
        table = newTable;
        for (var node : oldTable) {
            if (node != null) {
                table[getIndex(node.key)] = node;
            }
        }
    }

    private boolean getCompareKeys(K key, MapEntry<K, V> actual) {
        return hash(Objects.hashCode(actual.key)) == hash(Objects.hashCode(key))
                && (actual.key == key || Objects.equals(actual.key, key));
    }

    @Override
    public V get(K key) {
        return checkCompareNoNull(key) ? getNode(key).value : getNullKeyValue(key);
    }

    private V getNullKeyValue(K key) {
        return key == null && table[0].key == null
                ? table[0].value : null;
    }

    private boolean checkCompareNoNull(K key) {
        return key != null && getNode(key) != null && getCompareKeys(key, getNode(key));
    }

    private boolean isSeatTaken(K key) {
        return table != null
                && table.length > 0
                && table[getIndex(key)] != null;
    }

    private MapEntry<K, V> getNode(K key) {
        return key != null && isSeatTaken(key)
                ? table[getIndex(key)] : table[0];
    }

    @Override
    public boolean remove(K key) {
        boolean rsl = false;
        if (isSeatTaken(key) && checkCompareNoNull(key)
        || (key == null && table[0].key == null)) {
            table[getIndex(key)] = null;
            count--;
            modCount++;
            rsl = true;
        }
        return rsl;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {

            private final int expected = modCount;
            private int index;

            @Override
            public boolean hasNext() {
                if (expected != modCount) {
                    throw new ConcurrentModificationException("Concurrent Modification");
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException("No Such Element");
                }
                return table[index++].key;
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SimpleMap<?, ?> simpleMap = (SimpleMap<?, ?>) o;
        return capacity == simpleMap.capacity
                && count == simpleMap.count
                && modCount == simpleMap.modCount
                && Arrays.equals(table, simpleMap.table);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(capacity, count, modCount);
        result = 31 * result + Arrays.hashCode(table);
        return result;
    }

    private static class MapEntry<K, V> {

        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

    }
}
