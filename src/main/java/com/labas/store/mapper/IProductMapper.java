package com.labas.store.mapper;

import com.labas.store.model.entities.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IProductMapper extends IGenericMapper<Product, Long> {

    @Override
    @Select("SELECT * FROM Product WHERE product_id = #{id}")
    Product findById(Long id);

    @Override
    @Select("SELECT * FROM Product")
    List<Product> findAll();

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "productId")
    @Insert("INSERT INTO Product (name, price, description) VALUES (#{name}, #{price}, #{description})")
    boolean save(Product entity);

    @Override
    @Update("UPDATE Product SET name = #{name}, price = #{price}, description = #{description} WHERE product_id = #{productId}")
    boolean update(Product entity);

    @Override
    @Delete("DELETE FROM Product WHERE product_id = #{id}")
    boolean delete(Long id);
}
