package com.labas.store.model.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labas.store.util.JsonUtils;
import com.labas.store.util.LongIdAdapter;


import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Represents a category in the OnlineStore platform.
 * Used to organize products into hierarchical groupings.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Category {

    @XmlJavaTypeAdapter(LongIdAdapter.class)
    @XmlAttribute
    @XmlID
    private Long categoryId;

    @XmlElement
    private String name;

    @XmlElement
    @XmlIDREF()
    private Category parentCategory; // References the parent category for nested hierarchies

    public Category() {
    }

    public Category(Long categoryId, String name, Category parentCategory) {
        this.categoryId = categoryId;
        this.name = name;
        this.parentCategory = parentCategory;
    }


    public Long getCategoryId() {
        return categoryId;
    }

    public String getName() {
        return name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        try {
            return JsonUtils.getObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error to represent a Category class as string" + e);
        }
    }
}
