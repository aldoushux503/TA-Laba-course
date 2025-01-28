package com.labas.store.util;

public class CompositeKey <K1, K2> {
    K1 k1;
    K2 k2;

    public CompositeKey(K1 k1, K2 k2) {
        this.k1 = k1;
        this.k2 = k2;
    }

    public K1 getK1() {
        return k1;
    }

    public K2 getK2() {
        return k2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CompositeKey<?, ?> that = (CompositeKey<?, ?>) o;

        if (!k1.equals(that.k1)) return false;
        return k2.equals(that.k2);
    }

}
