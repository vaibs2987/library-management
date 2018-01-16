package com.library.model;

import java.util.Set;

public class User {

	private Long id;
	private String name;
	private String email;
	private Long currentSubscriptionDetailId;
	private Set<Role> userRoles;

	public User(String name, String email, Set<Role> userRoles) {
		this.setName(name);
		this.setEmail(email);
		this.setUserRoles(userRoles);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getCurrentSubscriptionDetailId() {
		return currentSubscriptionDetailId;
	}

	public void setCurrentSubscriptionDetailId(Long currentSubscriptionDetailId) {
		this.currentSubscriptionDetailId = currentSubscriptionDetailId;
	}

	public Set<Role> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<Role> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
