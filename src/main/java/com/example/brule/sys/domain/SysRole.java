package com.example.brule.sys.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

import static jakarta.persistence.CascadeType.DETACH;

@Getter
@Setter
@Entity
public class SysRole {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(unique = true, nullable = false)
	private String name;

	private String description;

	private Boolean available = Boolean.FALSE;

	@ManyToMany(fetch = FetchType.LAZY, cascade = DETACH)
	@JoinTable(name = "sys_role_resource", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"))
	private Set<SysResource> resources = new LinkedHashSet<>();

	@ManyToMany(fetch = FetchType.LAZY, cascade = DETACH)
	@JoinTable(name = "sys_user_role", joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	private Set<SysUser> users = new LinkedHashSet<>();

}
