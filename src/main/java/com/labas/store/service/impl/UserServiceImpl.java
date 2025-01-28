package com.labas.store.service.impl;

import com.labas.store.dao.UserDAO;
import com.labas.store.model.entity.User;
import com.labas.store.service.AbstractService;
import com.labas.store.service.UserService;

public class UserServiceImpl extends AbstractService<User, Long> implements UserService {
    public UserServiceImpl(UserDAO userDAO) {
        super(userDAO);
    }
}
