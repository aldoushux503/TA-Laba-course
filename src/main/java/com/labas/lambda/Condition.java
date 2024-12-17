package com.labas.lambda;

@FunctionalInterface
interface Condition<T> {
    boolean test(T t);
}
