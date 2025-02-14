package com.labas.store.mapper;

import com.labas.store.model.entities.UserRole;
import com.labas.store.util.CompositeKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IUserRoleMapper extends IGenericMapper<UserRole, CompositeKey<Long, Long>> {

    @Override
    @Select("SELECT * FROM User_role WHERE user_id = #{id.key1} AND role_id = #{id.key2}")
    UserRole findById(CompositeKey<Long, Long> id);

    @Override
    @Select("SELECT * FROM User_role")
    List<UserRole> findAll();

    @Override
    @Insert("INSERT INTO User_role (user_id, role_id) VALUES (#{user.userId}, #{role.roleId})")
    boolean save(UserRole entity);

    @Override
    @Update("UPDATE User_role SET role_id = #{role.roleId} WHERE user_id = #{user.userId} AND role_id = #{id.key2}")
    boolean update(UserRole entity);

    @Override
    @Delete("DELETE FROM User_role WHERE user_id = #{id.key1} AND role_id = #{id.key2}")
    boolean delete(CompositeKey<Long, Long> id);
}
