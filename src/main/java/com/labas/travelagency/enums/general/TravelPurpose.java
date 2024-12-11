package com.labas.travelagency.enums.general;

public enum TravelPurpose {
    LEISURE("Leisure", "Travel for relaxation or tourism"),
    BUSINESS("Business", "Travel for work or business purposes"),
    EDUCATION("Education", "Travel for studying or attending courses"),
    MEDICAL("Medical", "Travel for healthcare or treatments");

    private final String name;
    private final String description;

    TravelPurpose(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
