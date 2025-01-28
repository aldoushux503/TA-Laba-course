package com.labas.store.model.entity;

/**
 * Represents the relationship between a product and a category.
 */
public class ProductCategory {
    private Product product;
    private Category category;

    public ProductCategory() {
    }

    public ProductCategory(Category category, Product product) {
        this.product = product;
        this.category = category;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
