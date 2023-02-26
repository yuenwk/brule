package com.example.brule.sys.repository;

import com.example.brule.sys.domain.SysRole;
import com.example.brule.sys.domain.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleRepository extends JpaRepository<SysRole, Long> {

    @Query("select distinct u FROM SysUser u join u.roles r where r.id=:roleId")
    Page<SysUser> findRoleUsers(Long roleId, Pageable pageable);

}