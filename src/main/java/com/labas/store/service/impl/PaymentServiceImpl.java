package com.labas.store.service.impl;

import com.labas.store.dao.IPaymentDAO;
import com.labas.store.model.entities.Payment;
import com.labas.store.service.IPaymentService;

public class PaymentServiceImpl extends AbstractService<Payment, Long> implements IPaymentService {
    public PaymentServiceImpl(IPaymentDAO IPaymentDAO) {
        super(IPaymentDAO);
    }
}
