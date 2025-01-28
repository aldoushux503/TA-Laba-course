package com.labas.store.service.impl;

import com.labas.store.service.AbstractService;
import com.labas.store.service.PaymentService;

public class PaymentServiceImpl extends AbstractService<Payment, Long> implements PaymentService {
    public PaymentServiceImpl(PaymentDAO paymentDAO) {
        super(paymentDAO);
    }
}
