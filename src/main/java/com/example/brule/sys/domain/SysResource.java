package com.example.brule.sys.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
public class SysResource extends EntityBase {

	@Column(length = 20)
	private String name;

	private Type type;

	private String permission;

	@ManyToOne(fetch = FetchType.LAZY)
	private SysResource parent;

	@OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
	private Set<SysResource> children = new LinkedHashSet<>();

	/**
	 * 父编号列表
	 */
	private String parentIds;

	@Column(length = 50)
	private String icon;

	@Column(length = 1024)
	private String url;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
	@JoinTable(name = "sys_role_resource", joinColumns = @JoinColumn(name = "resource_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<SysRole> roles = new LinkedHashSet<>();

	public enum Type {

		MENU, BUTTON, API

	}

}
