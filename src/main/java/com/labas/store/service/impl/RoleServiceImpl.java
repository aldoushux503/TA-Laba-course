package com.labas.store.service.impl;

import com.labas.store.dao.RoleDAO;
import com.labas.store.model.entity.Role;
import com.labas.store.service.AbstractService;
import com.labas.store.service.RoleService;

public class RoleServiceImpl extends AbstractService<Role, Long> implements RoleService {
    public RoleServiceImpl(RoleDAO roleDAO) {
        super(roleDAO);
    }
}
