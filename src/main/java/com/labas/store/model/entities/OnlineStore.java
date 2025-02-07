package com.labas.store.model.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.labas.store.util.JsonUtils;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@XmlRootElement(name = "onlineStore")
public class OnlineStore {

    @XmlElementWrapper(name = "categories")
    @XmlElement(name = "category")
    private List<Category> categories;

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    private List <Product> products;

    @XmlElementWrapper(name = "orders")
    @XmlElement(name = "order")
    private List<Order> orders;

    @XmlElementWrapper(name = "productCategories")
    @XmlElement(name = "productCategory")
    private List<ProductCategory> productCategories;

    @XmlElementWrapper(name = "orderProducts")
    @XmlElement(name = "orderProduct")
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

    @Override
    public String toString() {
        try {
            return JsonUtils.getObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error to represent a Store class as string" + e);
        }
    }
}
