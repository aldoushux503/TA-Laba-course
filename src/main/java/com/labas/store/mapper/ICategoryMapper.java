package com.labas.store.mapper;


import com.labas.store.dao.*;
import com.labas.store.dao.mybatis.MyBatisAbstractDAO;
import com.labas.store.model.entities.*;
import com.labas.store.util.CompositeKey;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ICategoryMapper extends IGenericMapper<Category, Long> {

    @Override
    @Select("SELECT * FROM Category WHERE category_id = #{id}")
    Category findById(Long id);

    @Override
    @Select("SELECT * FROM Category")
    List<Category> findAll();

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "categoryId")
    @Insert("INSERT INTO Category (name, parent_category) VALUES (#{name}, #{parentCategory.categoryId})")
    boolean save(Category entity);

    @Override
    @Update("UPDATE Category SET name = #{name}, parent_category = #{parentCategory.categoryId} WHERE category_id = #{categoryId}")
    boolean update(Category entity);

    @Override
    @Delete("DELETE FROM Category WHERE category_id = #{id}")
    boolean delete(Long id);
}


