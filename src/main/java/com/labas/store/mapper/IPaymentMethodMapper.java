package com.labas.store.mapper;

import com.labas.store.model.entities.PaymentMethod;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IPaymentMethodMapper extends IGenericMapper<PaymentMethod, Long> {

    @Override
    @Select("SELECT * FROM Payment_method WHERE payment_method_id = #{id}")
    PaymentMethod findById(Long id);

    @Override
    @Select("SELECT * FROM Payment_method")
    List<PaymentMethod> findAll();

    @Override
    @Options(useGeneratedKeys = true, keyProperty = "paymentMethodId")
    @Insert("INSERT INTO Payment_method (name) VALUES (#{name})")
    boolean save(PaymentMethod entity);

    @Override
    @Update("UPDATE Payment_method SET name = #{name} WHERE payment_method_id = #{paymentMethodId}")
    boolean update(PaymentMethod entity);

    @Override
    @Delete("DELETE FROM Payment_method WHERE payment_method_id = #{id}")
    boolean delete(Long id);
}
