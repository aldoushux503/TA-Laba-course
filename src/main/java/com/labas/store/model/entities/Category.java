package com.labas.store.model.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labas.store.dao.mysql.MySQLCategoryDAO;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "categoryId")
public class Category {

    @XmlJavaTypeAdapter(LongIdAdapter.class)
    @XmlAttribute(name = "categoryId")
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

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    public void setParentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
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
