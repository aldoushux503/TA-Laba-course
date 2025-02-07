package com.labas.store.service.impl;

import com.labas.store.dao.IRoleDAO;
import com.labas.store.model.entities.Role;
import com.labas.store.service.IRoleService;

public class RoleServiceImpl extends AbstractService<Role, Long> implements IRoleService {
    public RoleServiceImpl(IRoleDAO IRoleDAO) {
        super(IRoleDAO);
    }
}
