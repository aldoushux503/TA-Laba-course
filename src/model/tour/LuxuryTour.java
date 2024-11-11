package model.tour;

import core.Tour;

public class LuxuryTour extends Tour {
    private boolean includesSpa;

    public LuxuryTour(long id, String name, String description, boolean includesSpa) {
        super(id, name, description );
        this.includesSpa = includesSpa;
    }

}
