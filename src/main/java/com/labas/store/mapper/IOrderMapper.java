package com.labas.store.mapper;

import com.labas.store.model.entities.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrderMapper extends IGenericMapper<Order, Long> {

    @Override
    @Select("SELECT * FROM `Order` WHERE order_id = #{id}")
    Order findById(Long id);

    @Override
    @Select("SELECT * FROM `Order`")
    List<Order> findAll();

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "orderId")
    @Insert("INSERT INTO `Order` (discount, total, created_at, updated_at, order_status_id, user_id) VALUES (#{discount}, #{total}, #{createdAt}, #{updatedAt}, #{orderStatus.orderStatusId}, #{user.userId})")
    boolean save(Order entity);

    @Override
    @Update("UPDATE `Order` SET discount = #{discount}, total = #{total}, created_at = #{createdAt}, updated_at = #{updatedAt}, order_status_id = #{orderStatus.orderStatusId}, user_id = #{user.userId} WHERE order_id = #{orderId}")
    boolean update(Order entity);

    @Override
    @Delete("DELETE FROM `Order` WHERE order_id = #{id}")
    boolean delete(Long id);
}
