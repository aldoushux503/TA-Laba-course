package com.labas.travelagency;

@FunctionalInterface
interface Condition<T> {
    boolean test(T t);
}
