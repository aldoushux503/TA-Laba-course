package com.labas.travelagency.enums.general;

public enum Rating {
    EXCELLENT(5, "Excellent Service"),
    GOOD(4, "Good Service"),
    AVERAGE(3, "Average Service"),
    POOR(2, "Poor Service"),
    TERRIBLE(1, "Terrible Service");

    private final int score;
    private final String description;

    Rating(int score, String description) {
        this.score = score;
        this.description = description;
    }

    public int getScore() {
        return score;
    }

    public String getDescription() {
        return description;
    }
}

