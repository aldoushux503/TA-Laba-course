package com.labas.store.mapper;

import com.labas.store.model.entities.OrderStatus;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrderStatusMapper extends IGenericMapper<OrderStatus, Long> {

    @Override
    @Select("SELECT * FROM Order_status WHERE order_status_id = #{id}")
    OrderStatus findById(Long id);

    @Override
    @Select("SELECT * FROM Order_status")
    List<OrderStatus> findAll();

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "orderStatusId")
    @Insert("INSERT INTO Order_status (status_name) VALUES (#{statusName})")
    boolean save(OrderStatus entity);

    @Override
    @Update("UPDATE Order_status SET status_name = #{statusName} WHERE order_status_id = #{orderStatusId}")
    boolean update(OrderStatus entity);

    @Override
    @Delete("DELETE FROM Order_status WHERE order_status_id = #{id}")
    boolean delete(Long id);
}
