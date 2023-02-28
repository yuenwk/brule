package com.example.brule.sys.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Setter
public class SysUser extends EntityBase {

	@Column(nullable = false, unique = true, length = 30)
	private String username;

	@Column(nullable = false, length = 50)
	private String fullName;

	@Column(length = 1024)
	private String avatar;

	@Column(nullable = false)
	private Gender gender;

	@Column(nullable = false)
	private State state;

	@Column
	private LocalDateTime updatedTime;

	@Column
	private LocalDateTime createdTime;

	@ManyToMany(fetch = LAZY, cascade = CascadeType.DETACH)
	@JoinTable(name = "sys_user_role", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Set<SysRole> roles = new LinkedHashSet<>();

	public enum Gender {

		MALE, FEMALE

	}

	public enum State {

		NORMAL, LOCKED

	}

	public boolean isLocked() {
		return this.state == State.LOCKED;
	}

}
