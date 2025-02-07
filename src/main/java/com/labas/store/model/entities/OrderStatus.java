package com.labas.store.model.entities;

import com.sun.xml.txw2.annotation.XmlValue;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Represents the status of an order.
 * Could be converted to an enum if statuses are predefined.
 */

@XmlRootElement
public class OrderStatus {

    @XmlAttribute
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
