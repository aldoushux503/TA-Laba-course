package com.labas.store.mapper;

import com.labas.store.model.entities.ProductCategory;
import com.labas.store.util.CompositeKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IProductCategoryMapper extends IGenericMapper<ProductCategory, CompositeKey<Long, Long>> {

    @Override
    @Select("SELECT * FROM Product_category WHERE category_id = #{id.key1} AND product_id = #{id.key2}")
    ProductCategory findById(CompositeKey<Long, Long> id);

    @Override
    @Select("SELECT * FROM Product_category")
    List<ProductCategory> findAll();

    @Override
    @Insert("INSERT INTO Product_category (category_id, product_id) VALUES (#{category.categoryId}, #{product.productId})")
    boolean save(ProductCategory entity);

    @Override
    @Update("UPDATE Product_category SET category_id = #{category.categoryId}, product_id = #{product.productId} WHERE category_id = #{id.key1} AND product_id = #{id.key2}")
    boolean update(ProductCategory entity);

    @Override
    @Delete("DELETE FROM Product_category WHERE category_id = #{id.key1} AND product_id = #{id.key2}")
    boolean delete(CompositeKey<Long, Long> id);
}
