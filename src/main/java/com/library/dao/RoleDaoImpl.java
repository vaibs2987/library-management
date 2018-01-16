package com.library.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.library.model.Role;
import com.library.util.HelperService;

@Repository
public class RoleDaoImpl implements RoleDao {
	Map<Long, Role> rolemap = new LinkedHashMap<>();

	@Autowired
	private HelperService helperService;

	@Override
	public Long createRole(String name) {
		Role role = new Role();
		Long id = helperService.getRandomNumuber();
		role.setId(id);
		role.setName(name);
		rolemap.put(id, role);
		return id;
	}

	@Override
	public Role getRoleById(Long roleId) {
		return rolemap.get(roleId);
	}

}
