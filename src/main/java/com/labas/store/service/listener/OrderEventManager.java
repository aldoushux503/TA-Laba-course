package com.labas.store.service.listener;

import com.labas.store.model.entities.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderEventManager {
    private final List<IOrderEventListener> listeners = new ArrayList<>();

    public void subscribe(IOrderEventListener listener) {
        listeners.add(listener);
    }

    public void unsubscribe(IOrderEventListener listener) {
        listeners.remove(listener);
    }

    public void notifyOrderCreated(Order order) {
        for (IOrderEventListener listener : listeners) {
            listener.onOrderCreated(order);
        }
    }

    public void notifyOrderUpdated(Order order) {
        for (IOrderEventListener listener : listeners) {
            listener.onOrderUpdated(order);
        }
    }

    public void notifyOrderDeleted(Long orderId) {
        for (IOrderEventListener listener : listeners) {
            listener.onOrderDeleted(orderId);
        }
    }
}
