package com.labas.lambda;

import com.labas.travelagency.model.agency.Customer;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<List<Integer>> nestedNumbers = List.of(
                List.of(1, 2, 3),
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

        // 1. Predicate: Filter animals with a population greater than 5
        Predicate<Animal> populationFilter = animal -> animal.getPopulation() > 5;
        List<Animal> filteredAnimals = zoo.stream()
                .filter(populationFilter)
                .toList();

        // 2. Function: Convert animal names to uppercase
        Function<Animal, Animal> nameToUppercase = animal ->
                new Animal(animal.getName().toUpperCase(), animal.getSpecies(), animal.getPopulation());
        List<Animal> uppercaseAnimals = filteredAnimals.stream()
                .map(nameToUppercase)
                .toList();

        // 3. ToIntFunction: Calculate total population
        ToIntFunction<Animal> populationExtractor = Animal::getPopulation;
        int totalPopulation = zoo.stream()
                .mapToInt(populationExtractor)
                .sum();

        // 4. Function and Collector: Group animals by species
        Function<Animal, String> speciesClassifier = Animal::getSpecies;
        Map<String, List<Animal>> animalsBySpecies = uppercaseAnimals.stream()
                .collect(Collectors.groupingBy(speciesClassifier));

        // 5. Consumer: Print formatted results for uppercaseAnimals
        Consumer<Animal> animalPrinter = animal ->
                System.out.println(animal.getName() + " belongs to "
                        + animal.getSpecies() + " category and has " + animal.getPopulation() + " population.");
        uppercaseAnimals.forEach(animalPrinter);


        System.out.println("\nAnimals grouped by species:");
        animalsBySpecies.forEach((species, animals) -> {
            System.out.println(species + ": " + animals);
        });

        System.out.println("\nTotal population: " + totalPopulation);

    }


    static <T, U> List<U> flattenAndTransform(List<List<T>> list, Function<T, U> function) {
        return list.stream()
                .flatMap(List::stream)
                .map(function)
                .collect(Collectors.toList());
    }

    static <T, U> Map<U, List<T>> groupBy(List<T> list, Function<T, U> function) {
        return list.stream()
                .collect(Collectors.groupingBy(function));
    }

    static <T, U> List<U> filterAndTransform(List<T> list, Predicate<T> filter, Function<T, U> function) {
        return list.stream()
                .filter(filter)
                .map(function)
                .collect(Collectors.toList());
    }
}
