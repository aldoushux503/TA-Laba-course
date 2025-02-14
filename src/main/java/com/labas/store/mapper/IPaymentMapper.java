package com.labas.store.mapper;

import com.labas.store.model.entities.Payment;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IPaymentMapper extends IGenericMapper<Payment, Long> {

    @Override
    @Select("SELECT * FROM Payment WHERE payment_id = #{id}")
    Payment findById(Long id);

    @Override
    @Select("SELECT * FROM Payment")
    List<Payment> findAll();

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "paymentId")
    @Insert("INSERT INTO Payment (created_at, updated_at, payment_method_id, user_id, order_id) VALUES (#{createdAt}, #{updatedAt}, #{paymentMethod.paymentMethodId}, #{user.userId}, #{order.orderId})")
    boolean save(Payment entity);

    @Override
    @Update("UPDATE Payment SET updated_at = #{updatedAt}, payment_method_id = #{paymentMethod.paymentMethodId}, user_id = #{user.userId}, order_id = #{order.orderId} WHERE payment_id = #{paymentId}")
    boolean update(Payment entity);

    @Override
    @Delete("DELETE FROM Payment WHERE payment_id = #{id}")
    boolean delete(Long id);
}
