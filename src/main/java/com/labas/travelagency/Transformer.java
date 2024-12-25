package com.labas.travelagency;


@FunctionalInterface
interface Transformer<T, R> {
    R apply(T t);
}