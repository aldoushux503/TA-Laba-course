package com.labas.lambda;

class Animal {
    private String name;
    private String species;
    private int population;

    Animal(String name, String species, int population) {
        this.name = name;
        this.species = species;
        this.population = population;
    }

    @Override
    public String toString() {
        return String.format("%s (%s): %d", name, species, population);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }
}
