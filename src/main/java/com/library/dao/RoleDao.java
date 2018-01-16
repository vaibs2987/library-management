package com.library.dao;

import com.library.model.Role;

public interface RoleDao {

	Long createRole(String name);

	Role getRoleById(Long roleId);
}
