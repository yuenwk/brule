package com.example.brule.sys.domain;

import static jakarta.persistence.FetchType.LAZY;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
@Accessors(chain = true)
public class SysUser extends EntityBase implements UserDetails {

    @Column(nullable = false, unique = true, length = 30)
    private String username;

    @Column
    @JsonIgnore
    private String password;

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

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return this.state != State.LOCKED;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return this.state != State.LOCKED;
    }

    public enum Gender {

        MALE, FEMALE

    }

    public enum State {

        NORMAL, LOCKED

    }

}
