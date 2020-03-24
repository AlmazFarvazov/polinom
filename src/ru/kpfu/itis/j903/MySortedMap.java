package ru.kpfu.itis.j903;

import java.util.*;

public class MySortedMap<K extends Comparable, V> extends AbstractMap<K, V>{

    private ArrayList<Node<K, V>> data;

    public MySortedMap() {
        data = new ArrayList<>();
    }

    public MySortedMap(Map<K, V> map) {
        this();
        putAll(map);
    }

    public void clear() {
        data.clear();
    }

    @Override
    public boolean containsKey(Object o) {
        for (Node el : data) {
            if (el.getKey().equals(o)) return true;
        }
        return false;
    }

    @Override
    public boolean containsValue(Object o) {
        for (Node el : data) {
            if (el.getValue().equals(o)) return true;
        }
        return false;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        TreeSet<Entry<K, V>> set = new TreeSet<>((o1, o2) -> o2.getKey().compareTo(o1.getKey()));
        for (int i = 0; i < data.size(); i++){
            set.add(data.get(i));
        }
        return set;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        MySortedMap<?, ?> myMap = (MySortedMap<?, ?>) o;
        return data.equals(myMap.data);
    }

    @Override
    public V get(Object o) {
        for(Node el : data) {
            if (el.getKey().equals(o)) return (V) el.getValue();
        }
        return null;
    }

    public K getFirstKey() {
        return data.get(0).getKey();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public Set<K> keySet() {
        TreeSet<K> keys = new TreeSet<>((o1, o2) -> o2.compareTo(o1));
        for (Node el : data) {
            keys.add((K)el.getKey());
        }
        return keys;
    }

    @Override
    public V put(K k, V v) {
        if (size() == 0) {
            data.add(new Node<>(k, v));
            return null;
        }
        if (containsKey(k)) {
            for (Node el : data) {
                if (el.getKey().equals(k)) {
                    V oldValue = (V) el.getValue();
                    el.setValue(v);
                    return oldValue;
                }
            }
        }
        if (k.compareTo(data.get(size() - 1).getKey()) < 0) {
            data.add(new Node<>(k, v));
        }
        else if (k.compareTo(data.get(0).getKey()) > 0) {
            data.add(0, new Node<>(k, v));
        }
        else {
            for (int i = 1; i < data.size(); i++) {
                if (k.compareTo(data.get(i).getKey()) > 0) {
                    data.add(i - 1, new Node<>(k, v));
                }
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (K key : map.keySet()) {
            put(key, map.get(key));
        }
    }

    @Override
    public V remove(Object key) {
        if (!containsKey(key)) return null;
        V value = get(key);
        data.remove(new Node<>(key, get(key)));
        return value;
    }

    @Override
    public String toString() {
        return "MyMap"+ data.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), data);
    }

    private class Node<K, V> implements Entry<K, V> {
        K key;
        V value;
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V v) {
            V oldValue = value;
            value = v;
            return oldValue;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node<?, ?> node = (Node<?, ?>) o;
            return key.equals(node.key) &&
                    value.equals(node.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key, value);
        }

        @Override
        public String toString() {
            return "(" + key + " : " + value + ")";
        }
    }

}
