package com.labas.lambda;

@FunctionalInterface
interface Extractor<T> {
    int extract(T t);
}
