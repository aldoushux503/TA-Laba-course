package model.tour;

import core.Tour;

public class LuxuryTour extends Tour {
    private boolean privateChefIncluded;
    private boolean spaAccess;
    private String luxuryVehicle;


    public LuxuryTour(long id, String name, String description,
                      boolean privateChefIncluded, boolean spaAccess, String luxuryVehicle) {
        super(id, name, description);
        this.privateChefIncluded = privateChefIncluded;
        this.spaAccess = spaAccess;
        this.luxuryVehicle = luxuryVehicle;
    }

    @Override
    public String fullInformationPrint() {
        return String.format(
                "Luxury Tour: %s\nDescription: %s\nPrivate Chef Included: %s\nSPA Access: %s\nLuxury Vehicle: %s",
                name, description, privateChefIncluded ? "Yes" : "No", spaAccess ? "Yes" : "No", luxuryVehicle
        );
    }


    public boolean isPrivateChefIncluded() {
        return privateChefIncluded;
    }

    public void setPrivateChefIncluded(boolean privateChefIncluded) {
        this.privateChefIncluded = privateChefIncluded;
    }

    public boolean isSpaAccess() {
        return spaAccess;
    }

    public void setSpaAccess(boolean spaAccess) {
        this.spaAccess = spaAccess;
    }

    public String getLuxuryVehicle() {
        return luxuryVehicle;
    }

    public void setLuxuryVehicle(String luxuryVehicle) {
        this.luxuryVehicle = luxuryVehicle;
    }
}
