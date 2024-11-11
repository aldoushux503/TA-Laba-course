package model.tour;

import core.Tour;

public class AdventureTour extends Tour {

    private String difficultyLevel;

    public AdventureTour(long id, String name, String description, String difficultyLevel) {
        super(id, name, description);
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public void displayTourDetails() {
        System.out.println("Adventure Tour: " + getName() + ", Difficulty: " + difficultyLevel);
    }
}
