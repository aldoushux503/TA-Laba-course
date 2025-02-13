package com.labas.store.mapper;

import com.labas.store.model.entities.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IUserMapper extends IGenericMapper<User, Long> {

    @Override
    @Select("SELECT * FROM User WHERE user_id = #{id}")
    User findById(Long id);

    @Override
    @Select("SELECT * FROM User")
    List<User> findAll();

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "userId")
    @Insert("INSERT INTO User (first_name, last_name, email, phone_number, password_hash) " +
            "VALUES (#{firstName}, #{lastName}, #{email}, #{phoneNumber}, #{passwordHash})")
    boolean save(User entity);

    @Override
    @Update("UPDATE User SET first_name = #{firstName}, last_name = #{lastName}, email = #{email}, " +
            "phone_number = #{phoneNumber}, password_hash = #{passwordHash} WHERE user_id = #{userId}")
    boolean update(User entity);

    @Override
    @Select("DELETE FROM User WHERE user_id = #{id}")
    boolean delete(Long id);
}
