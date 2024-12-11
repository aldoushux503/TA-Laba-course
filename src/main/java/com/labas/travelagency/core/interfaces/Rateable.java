package com.labas.travelagency.core.interfaces;


import com.labas.travelagency.enums.general.Rating;

/**
 * Interface representing rateable entities.
 */
public interface Rateable {
    Rating getRating();

    void setRating(Rating rating);
}