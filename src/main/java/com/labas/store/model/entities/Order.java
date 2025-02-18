package com.labas.store.model.entities;

import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labas.store.util.JsonUtils;
import com.labas.store.util.LocalDateTimeAdapter;
import com.labas.store.util.LongIdAdapter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Represents an order placed by a user in the OnlineStore platform.
 * Tracks order details, status, and timestamps.
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "orderId",
        scope = Order.class
)
@JsonIgnoreProperties({"orderStatusId", "userId"})
public class Order {

    @XmlJavaTypeAdapter(LongIdAdapter.class)
    @XmlAttribute(name = "orderId")
    @XmlID
    private Long orderId;


    @XmlElement
    private Float discount;

    @XmlElement
    private Float total;

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlElement
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdAt;

    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    @XmlElement
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime updatedAt;

    @XmlAttribute(name = "orderStatusId")
    @XmlIDREF
    private OrderStatus orderStatus;

    @XmlAttribute(name = "userId")
    @XmlIDREF
    private User user;

    public Order() {
    }

    public Order(Long orderId, Float discount, Float total, LocalDateTime createdAt, LocalDateTime updatedAt, OrderStatus orderStatus, User user) {
        this.orderId = orderId;
        this.discount = discount;
        this.total = total;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.orderStatus = orderStatus;
        this.user = user;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Float getDiscount() {
        return discount;
    }

    public Float getTotal() {
        return total;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }


    public User getUser() {
        return user;
    }


    @Override
    public String toString() {
        try {
            return JsonUtils.getObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error to represent a Order class as string" + e);
        }
    }
}
