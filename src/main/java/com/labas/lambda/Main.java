package com.labas.lambda;

import com.labas.travelagency.model.agency.Customer;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Main {

    // Custom functional interfaces
    @FunctionalInterface
    interface Transformer<T, R> {
        R apply(T t);
    }

    @FunctionalInterface
    interface Condition<T> {
        boolean test(T t);
    }

    @FunctionalInterface
    interface Extractor<T> {
        int extract(T t);
    }

    public static void main(String[] args) {
        List<List<Integer>> nestedNumbers = List.of(
                List.of(1, 2, 3),
                List.of(4, 5, 6),
                List.of(4, 5, 6),
                List.of(4, 5, 6)
        );
        List<Integer> squared = flattenAndTransform(nestedNumbers, n -> n * n);

        List<String> words = List.of("apple", "banana", "cherry", "avocado");
        Map<Character, List<String>> groupedByFirstLetter = groupBy(words, word -> word.charAt(0));

        List<Integer> numbers = List.of(1, 2, 3, 4, 5, 6);
        List<Integer> evenCubes = filterAndTransform(numbers, n -> n % 2 == 0, n -> n * n * n);

        System.out.printf("Flattened and squared numbers: %s%n", squared);
        System.out.printf("Grouped words by first letter: %s%n", groupedByFirstLetter);
        System.out.printf("Even numbers cubed: %s%n%n", evenCubes);

        // Sample zoo data
        List<Animal> zoo = List.of(
                new Animal("Lion", "Mammal", 10),
                new Animal("Tiger", "Mammal", 5),
                new Animal("Elephant", "Mammal", 20),
                new Animal("Crocodile", "Reptile", 2),
                new Animal("Zebra", "Mammal", 12),
                new Animal("Python", "Reptile", 8)
        );

        // 1. Condition: Filter animals with a population greater than 5
        Condition<Animal> populationFilter = animal -> animal.getPopulation() > 5;
        List<Animal> filteredAnimals = zoo.stream()
                .filter(populationFilter::test)
                .toList();

        // 2. Transformer: Convert animal names to uppercase
        Transformer<Animal, Animal> nameToUppercase = animal ->
                new Animal(animal.getName().toUpperCase(), animal.getSpecies(), animal.getPopulation());
        List<Animal> uppercaseAnimals = filteredAnimals.stream()
                .map(nameToUppercase::apply)
                .toList();

        // 3. Extractor: Calculate total population
        Extractor<Animal> populationExtractor = Animal::getPopulation;
        int totalPopulation = zoo.stream()
                .mapToInt(populationExtractor::extract)
                .sum();

        // 4. Transformer and Collector: Group animals by species
        Transformer<Animal, String> speciesClassifier = Animal::getSpecies;
        Map<String, List<Animal>> animalsBySpecies = uppercaseAnimals.stream()
                .collect(Collectors.groupingBy(speciesClassifier::apply));

        // 5. Consumer: Print formatted results for uppercaseAnimals
        Consumer<Animal> animalPrinter = animal -> System.out.println(
                animal.getName() + " belongs to " + animal.getSpecies() + " category and has " + animal.getPopulation() + " population."
        );
        uppercaseAnimals.forEach(animalPrinter);

        System.out.println("\nAnimals grouped by species:");
        animalsBySpecies.forEach((species, animals) -> System.out.println(species + ": " + animals));

        System.out.println("\nTotal population: " + totalPopulation);
    }

    static <T, U> List<U> flattenAndTransform(List<List<T>> list, Transformer<T, U> transformer) {
        return list.stream()
                .flatMap(List::stream)
                .map(transformer::apply)
                .collect(Collectors.toList());
    }

    static <T, U> Map<U, List<T>> groupBy(List<T> list, Transformer<T, U> transformer) {
        return list.stream()
                .collect(Collectors.groupingBy(transformer::apply));
    }

    static <T, U> List<U> filterAndTransform(List<T> list, Condition<T> condition, Transformer<T, U> transformer) {
        return list.stream()
                .filter(condition::test)
                .map(transformer::apply)
                .collect(Collectors.toList());
    }

    // Animal class definition
    static class Animal {
        private final String name;
        private final String species;
        private final int population;

        public Animal(String name, String species, int population) {
            this.name = name;
            this.species = species;
            this.population = population;
        }

        public String getName() {
            return name;
        }

        public String getSpecies() {
            return species;
        }

        public int getPopulation() {
            return population;
        }

        @Override
        public String toString() {
            return String.format("%s(%s, %d)", name, species, population);
        }
    }
}
