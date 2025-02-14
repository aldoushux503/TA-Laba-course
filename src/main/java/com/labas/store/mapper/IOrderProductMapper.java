package com.labas.store.mapper;

import com.labas.store.model.entities.OrderProduct;
import com.labas.store.util.CompositeKey;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface IOrderProductMapper extends IGenericMapper<OrderProduct, CompositeKey<Long, Long>> {

    @Override
    @Select("SELECT * FROM Order_product WHERE order_id = #{id.key1} AND product_id = #{id.key2}")
    OrderProduct findById(CompositeKey<Long, Long> id);

    @Override
    @Select("SELECT * FROM Order_product")
    List<OrderProduct> findAll();

    @Override
    @Insert("INSERT INTO Order_product (order_id, product_id, price_at_order, quantity) VALUES (#{order.orderId}, #{product.productId}, #{priceAtOrder}, #{quantity})")
    boolean save(OrderProduct entity);

    @Override
    @Update("UPDATE Order_product SET price_at_order = #{priceAtOrder}, quantity = #{quantity} WHERE order_id = #{order.orderId} AND product_id = #{product.productId}")
    boolean update(OrderProduct entity);

    @Override
    @Delete("DELETE FROM Order_product WHERE order_id = #{id.key1} AND product_id = #{id.key2}")
    boolean delete(CompositeKey<Long, Long> id);
}
