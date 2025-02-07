package com.labas.store.model.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.labas.store.util.JsonUtils;

import javax.xml.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = "onlineStore")
@XmlAccessorType(XmlAccessType.FIELD)
public class OnlineStore {

    @XmlElementWrapper(name = "categories")
    @XmlElement(name = "category")
    @JsonProperty("categories")
    private List<Category> categories;

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    @JsonProperty("products")
    private List <Product> products;

    @XmlElementWrapper(name = "orders")
    @XmlElement(name = "order")
    @JsonProperty("orders")
    private List<Order> orders;

    @XmlElementWrapper(name = "productCategories")
    @XmlElement(name = "productCategory")
    @JsonProperty("productCategories")
    private List<ProductCategory> productCategories;

    @XmlElementWrapper(name = "orderProducts")
    @XmlElement(name = "orderProduct")
    @JsonProperty("orderProducts")
    private List<OrderProduct> orderProducts;

    public List<Category> getCategories() {
        return categories;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public List<OrderProduct> getOrderProducts() {
        return orderProducts;
    }


    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setProductCategories(List<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }

    public void setOrderProducts(List<OrderProduct> orderProducts) {
        this.orderProducts = orderProducts;
    }

    @Override
    public String toString() {
        try {
            return JsonUtils.getObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error to represent a Store class as string" + e);
        }
    }
}
