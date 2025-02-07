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


    public void buildRelations() {
        Map<Long, Category> categoryMap = new HashMap<>();
        categories.forEach(category -> categoryMap.put(category.getCategoryId(), category));

        Map<Long, Product> productMap = new HashMap<>();
        products.forEach(product -> productMap.put(product.getProductId(), product));

        Map<Long, Order> orderMap = new HashMap<>();
        orders.forEach(order -> orderMap.put(order.getOrderId(), order));

        for (ProductCategory productCategory : productCategories) {
            Long productId = productCategory.getProduct() != null ? productCategory.getProduct().getProductId() : null;
            Long categoryId = productCategory.getCategory() != null ? productCategory.getCategory().getCategoryId() : null;

            if (productId != null && productMap.containsKey(productId)) {
                productCategory.setProduct(productMap.get(productId));
            }

            if (categoryId != null && categoryMap.containsKey(categoryId)) {
                productCategory.setCategory(categoryMap.get(categoryId));
            }
        }

        for (OrderProduct orderProduct : orderProducts) {
            Long orderId = orderProduct.getOrder() != null ? orderProduct.getOrder().getOrderId() : null;
            Long productId = orderProduct.getProduct() != null ? orderProduct.getProduct().getProductId() : null;

            if (orderId != null && orderMap.containsKey(orderId)) {
                orderProduct.setOrder(orderMap.get(orderId));
            }

            if (productId != null && productMap.containsKey(productId)) {
                orderProduct.setProduct(productMap.get(productId));
            }
        }
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
