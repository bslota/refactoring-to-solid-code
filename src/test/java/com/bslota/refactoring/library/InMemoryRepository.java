package com.bslota.refactoring.library;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author bslota on 20/11/2019
 */
abstract class InMemoryRepository<K, V> {

    private Function<V, K> keyExtractor;
    private Map<K, V> store = new HashMap<>();

    InMemoryRepository(Function<V, K> keyExtractor) {
        this.keyExtractor = keyExtractor;
    }

    public V findBy(K key) {
        return store.get(key);
    }

    public void save(V value) {
        store.put(keyExtractor.apply(value), value);
    }
}
