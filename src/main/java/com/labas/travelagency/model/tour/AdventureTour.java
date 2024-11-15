package main.java.com.labas.travelagency.model.tour;

import main.java.com.labas.travelagency.core.Tour;

import java.util.List;

public class AdventureTour extends Tour {

    private String difficultyLevel;
    private List<String> requiredEquipment;
    private boolean physicalConditionRequirement;

    public AdventureTour(long id, String name, String description, String difficultyLevel,
                         List<String> requiredEquipment, boolean physicalConditionRequirement) {
        super(id, name, description);
        this.difficultyLevel = difficultyLevel;
        this.requiredEquipment = requiredEquipment;
        this.physicalConditionRequirement = physicalConditionRequirement;
    }

    @Override
    public String fullInformationPrint() {
        return String.format(
                "Adventure Tour: %s\nDescription: %s\nDifficulty Level: %s\nRequired Equipment: %s\nPhysical Condition Requirement: %s",
                name, description, difficultyLevel, requiredEquipment, physicalConditionRequirement ? "Yes" : "No"
        );
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
