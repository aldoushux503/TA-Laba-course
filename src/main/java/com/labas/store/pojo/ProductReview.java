package com.labas.store.pojo;

/**
 * Represents a product review made by a user for a product.
 */
public class ProductReview {
    private Long reviewId;
    private String title;
    private Double rating;
    private String createdAt; // ISO 8601 format preferred
    private Product product; // Associated product
    private User user; // Author of the review

    public ProductReview() {}

    public ProductReview(Long reviewId, String title, Double rating, String createdAt, Product product, User user) {
        this.reviewId = reviewId;
        this.title = title;
        this.rating = rating;
        this.createdAt = createdAt;
        this.product = product;
        this.user = user;
    }

    public Long getReviewId() {
        return reviewId;
    }

    public void setReviewId(Long reviewId) {
        this.reviewId = reviewId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}