package com.labas.travelagency.core.interfaces;

import com.labas.travelagency.exceptions.ReservationException;

/**
 * Interface representing bookable entities.
 */
public interface Manageable {
    boolean isAvailable();

    void reserve() throws ReservationException;
    void cancel() throws ReservationException;
}
