package com.labas.travelagency.model.tour;

import com.labas.travelagency.core.Tour;
import com.labas.travelagency.enums.general.TravelPurpose;

import java.util.List;

public class AdventureTour extends Tour {

    private String difficultyLevel;
    private List<String> requiredEquipment;
    private boolean physicalConditionRequirement;


    public AdventureTour(long id, String name, String description, TravelPurpose travelPurpose,
                         String difficultyLevel, List<String> requiredEquipment, boolean pcr) {
        super(id, name, description, travelPurpose);
        this.difficultyLevel = difficultyLevel;
        this.requiredEquipment = requiredEquipment;
        this.physicalConditionRequirement = pcr;
    }

    public String getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(String difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public List<String> getRequiredEquipment() {
        return requiredEquipment;
    }

    public void setRequiredEquipment(List<String> requiredEquipment) {
        this.requiredEquipment = requiredEquipment;
    }

    public boolean isPhysicalConditionRequirement() {
        return physicalConditionRequirement;
    }

    public void setPhysicalConditionRequirement(boolean physicalConditionRequirement) {
        this.physicalConditionRequirement = physicalConditionRequirement;
    }
}
