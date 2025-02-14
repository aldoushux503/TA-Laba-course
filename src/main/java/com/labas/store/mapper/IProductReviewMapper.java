package com.labas.store.mapper;

import com.labas.store.model.entities.ProductReview;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IProductReviewMapper extends IGenericMapper<ProductReview, Long> {

    @Override
    @Select("SELECT * FROM Product_review WHERE review_id = #{id}")
    ProductReview findById(Long id);

    @Override
    @Select("SELECT * FROM Product_review")
    List<ProductReview> findAll();

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "reviewId")
    @Insert("INSERT INTO Product_review (title, rating, created_at, product_id, user_id) VALUES (#{title}, #{rating}, #{createdAt}, #{product.productId}, #{user.userId})")
    boolean save(ProductReview entity);

    @Override
    @Update("UPDATE Product_review SET title = #{title}, rating = #{rating}, created_at = #{createdAt}, product_id = #{product.productId}, user_id = #{user.userId} WHERE review_id = #{reviewId}")
    boolean update(ProductReview entity);

    @Override
    @Delete("DELETE FROM Product_review WHERE review_id = #{id}")
    boolean delete(Long id);
}
