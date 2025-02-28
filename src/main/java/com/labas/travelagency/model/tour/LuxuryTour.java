package com.labas.travelagency.model.tour;

import com.labas.travelagency.core.Tour;
import com.labas.travelagency.enums.general.TravelPurpose;

public class LuxuryTour extends Tour {
    private boolean privateChefIncluded;
    private boolean spaAccess;
    private String luxuryVehicle;

    public LuxuryTour(long id, String name, String description, TravelPurpose travelPurpose,
                      boolean privateChefIncluded, boolean spaAccess, String luxuryVehicle) {
        super(id, name, description, travelPurpose);
        this.privateChefIncluded = privateChefIncluded;
        this.spaAccess = spaAccess;
        this.luxuryVehicle = luxuryVehicle;
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
