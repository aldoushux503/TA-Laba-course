package com.labas.store.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @XmlAttribute(name = "productId")
    @XmlIDREF
    @JsonIdentityReference(alwaysAsId = true)
    private Product product;

    @XmlAttribute(name = "categoryId")
    @XmlIDREF
    @JsonIdentityReference(alwaysAsId = true)
    private Category category;

    public ProductCategory() {
    }

    public ProductCategory(Category category, Product product) {
        this.product = product;
        this.category = category;
    }

    @JsonProperty("productId")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    @JsonProperty("categoryId")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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
