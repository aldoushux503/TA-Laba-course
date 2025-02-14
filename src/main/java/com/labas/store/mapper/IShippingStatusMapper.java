package com.labas.store.mapper;

import com.labas.store.model.entities.ShippingStatus;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IShippingStatusMapper extends IGenericMapper<ShippingStatus, Long> {

    @Override
    @Select("SELECT * FROM Shipping_status WHERE shipping_status_id = #{id}")
    ShippingStatus findById(Long id);

    @Override
    @Select("SELECT * FROM Shipping_status")
    List<ShippingStatus> findAll();

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "shippingStatusId")
    @Insert("INSERT INTO Shipping_status (status_name) VALUES (#{statusName})")
    boolean save(ShippingStatus entity);

    @Override
    @Update("UPDATE Shipping_status SET status_name = #{statusName} WHERE shipping_status_id = #{shippingStatusId}")
    boolean update(ShippingStatus entity);

    @Override
    @Delete("DELETE FROM Shipping_status WHERE shipping_status_id = #{id}")
    boolean delete(Long id);
}
