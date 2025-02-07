package com.labas.store.model.entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.labas.store.util.LongIdAdapter;
import com.sun.xml.txw2.annotation.XmlValue;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * Represents the status of an order.
 * Could be converted to an enum if statuses are predefined.
 */

@XmlRootElement
@JsonRootName("orderStatus")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "orderStatusId",
        scope = OrderStatus.class
)
public class OrderStatus {

    @XmlJavaTypeAdapter(LongIdAdapter.class)
    @XmlAttribute
    @XmlID
    private Long orderStatusId;

    @XmlElement
    private String statusName;

    public OrderStatus() {
    }

    public OrderStatus(Long orderStatusId, String statusName) {
        this.orderStatusId = orderStatusId;
        this.statusName = statusName;
    }


    public Long getOrderStatusId() {
        return orderStatusId;
    }


    public String getStatusName() {
        return statusName;
    }



    @Override
    public String toString() {
        return "OrderStatus{" +
                "orderStatusId=" + orderStatusId +
                ", statusName='" + statusName + '\'' +
                '}';
    }
}
