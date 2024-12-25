package com.labas.travelagency;

@FunctionalInterface
interface Extractor<T> {
    int extract(T t);
}
