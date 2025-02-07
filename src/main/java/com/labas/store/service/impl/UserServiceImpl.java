package com.labas.store.service.impl;

import com.labas.store.dao.IUserDAO;
import com.labas.store.model.entities.User;
import com.labas.store.service.IUserService;

public class UserServiceImpl extends AbstractService<User, Long> implements IUserService {
    public UserServiceImpl(IUserDAO IUserDAO) {
        super(IUserDAO);
    }
}
