package com.labas.store.model.entity;

/**
 * Represents a category in the OnlineStore platform.
 * Used to organize products into hierarchical groupings.
 */
public class Category {
    private Long categoryId;
    private String name;
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
}
