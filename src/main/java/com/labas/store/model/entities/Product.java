package com.labas.store.model.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labas.store.util.JsonUtils;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.lang.reflect.Field;

/**
 * Represents a product in the OnlineStore platform.
 * Contains details such as name, price, and description.
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Product {

    @XmlAttribute
    private Long productId;

    @XmlElement
    private String name;

    @XmlElement
    private Double price;

    @XmlElement
    private String description;

    public Product() {
    }

    public Product(Long productId, String name, Double price, String description) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Long getProductId() {
        return productId;
    }


    public String getName() {
        return name;
    }


    public Double getPrice() {
        return price;
    }


    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        try {
            return JsonUtils.getObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error to represent a Product class as string" + e);
        }
    }

}
