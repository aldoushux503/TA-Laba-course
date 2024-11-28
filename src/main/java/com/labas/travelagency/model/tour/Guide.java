package com.labas.travelagency.model.tour;

import com.labas.travelagency.core.Person;

import java.util.ArrayList;
import java.util.List;

public class Guide extends Person {

    private List<String> languages;

    public Guide(long id, String name, String email, List<String> languages) {
        super(id, name, email);
        this.languages = new ArrayList<>(languages);
    }

}
