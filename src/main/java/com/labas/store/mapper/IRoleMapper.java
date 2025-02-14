package com.labas.store.mapper;

import com.labas.store.model.entities.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleMapper extends IGenericMapper<Role, Long> {

    @Override
    @Select("SELECT * FROM Role WHERE role_id = #{id}")
    Role findById(Long id);

    @Override
    @Select("SELECT * FROM Role")
    List<Role> findAll();

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "roleId")
    @Insert("INSERT INTO Role (name) VALUES (#{name})")
    boolean save(Role entity);

    @Override
    @Update("UPDATE Role SET name = #{name} WHERE role_id = #{roleId}")
    boolean update(Role entity);

    @Override
    @Delete("DELETE FROM Role WHERE role_id = #{id}")
    boolean delete(Long id);
}
