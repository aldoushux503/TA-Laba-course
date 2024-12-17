package com.labas.lambda;


@FunctionalInterface
interface Transformer<T, R> {
    R apply(T t);
}