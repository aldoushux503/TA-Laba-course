package com.labas.store.mapper;

import com.labas.store.model.entities.Shipping;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IShippingMapper extends IGenericMapper<Shipping, Long> {

    @Override
    @Select("SELECT * FROM Shipping WHERE shipping_id = #{id}")
    Shipping findById(Long id);

    @Override
    @Select("SELECT * FROM Shipping")
    List<Shipping> findAll();

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "shippingId")
    @Insert("INSERT INTO Shipping (shipping_carrier, tracking_number, shipped_at, estimated_delivery, shipping_status_id, order_id, adress_id, carrier_id) VALUES (#{shippingCarrier}, #{trackingNumber}, #{shippedAt}, #{estimatedDelivery}, #{shippingStatus.shippingStatusId}, #{order.orderId}, #{address.addressId}, #{carrier.carrierId})")
    boolean save(Shipping entity);

    @Override
    @Update("UPDATE Shipping SET shipping_carrier = #{shippingCarrier}, tracking_number = #{trackingNumber}, shipped_at = #{shippedAt}, estimated_delivery = #{estimatedDelivery}, shipping_status_id = #{shippingStatus.shippingStatusId}, order_id = #{order.orderId}, adress_id = #{address.addressId}, carrier_id = #{carrier.carrierId} WHERE shipping_id = #{shippingId}")
    boolean update(Shipping entity);

    @Override
    @Delete("DELETE FROM Shipping WHERE shipping_id = #{id}")
    boolean delete(Long id);
}
