package com.labas.store.mapper;

import com.labas.store.model.entities.Carrier;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ICarrierMapper extends IGenericMapper<Carrier, Long> {

    @Override
    @Select("SELECT * FROM Carrier WHERE carrier_id = #{id}")
    Carrier findById(Long id);

    @Override
    @Select("SELECT * FROM Carrier")
    List<Carrier> findAll();

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "carrierId")
    @Insert("INSERT INTO Carrier (name, contact_number, email, website) VALUES (#{name}, #{contactNumber}, #{email}, #{website})")
    boolean save(Carrier entity);

    @Override
    @Update("UPDATE Carrier SET name = #{name}, contact_number = #{contactNumber}, email = #{email}, website = #{website} WHERE carrier_id = #{carrierId}")
    boolean update(Carrier entity);

    @Override
    @Delete("DELETE FROM Carrier WHERE carrier_id = #{id}")
    boolean delete(Long id);
}
