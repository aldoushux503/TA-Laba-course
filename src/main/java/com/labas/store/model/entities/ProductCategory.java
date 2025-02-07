package com.labas.store.model.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labas.store.util.JsonUtils;

import javax.xml.bind.annotation.*;

/**
 * Represents the relationship between a product and a category.
 */
@XmlRootElement(name = "productCategory")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductCategory {

    @XmlElement(name = "product")
    private Product product;

    @XmlElement(name = "category")
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

    @XmlAttribute(name = "productId")
    public void setProductId(long productId) {
        if (this.product == null) {
            this.product = new Product();
        }
        this.product.setProductId(productId);
    }

    @XmlAttribute(name = "categoryId")
    public void setCategoryId(long categoryId) {
        if (this.category == null) {
            this.category = new Category();
        }
        this.category.setCategoryId(categoryId);
    }

    @Override
    public String toString() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error to represent a ProductCategory class as string", e);
        }
    }
}
