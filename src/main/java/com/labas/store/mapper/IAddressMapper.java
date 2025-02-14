package com.labas.store.mapper;

import com.labas.store.model.entities.Address;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IAddressMapper extends IGenericMapper<Address, Long> {

    @Override
    @Select("SELECT * FROM Address WHERE address_id = #{id}")
    Address findById(Long id);

    @Override
    @Select("SELECT * FROM Address")
    List<Address> findAll();

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "addressId")
    @Insert("INSERT INTO Address (city, street, zip_code, user_id) VALUES (#{city}, #{street}, #{zipCode}, #{user.userId})")
    boolean save(Address entity);

    @Override
    @Update("UPDATE Address SET city = #{city}, street = #{street}, zip_code = #{zipCode}, user_id = #{user.userId} WHERE address_id = #{addressId}")
    boolean update(Address entity);

    @Override
    @Delete("DELETE FROM Address WHERE address_id = #{id}")
    boolean delete(Long id);
}
