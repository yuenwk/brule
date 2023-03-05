package com.example.brule.sys.service;

import com.example.brule.sys.domain.SysUser;
import com.example.brule.sys.dto.PageVO;
import com.example.brule.sys.dto.RoleUserVO;
import com.example.brule.sys.repository.SysRoleRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SysRoleService {

	final SysRoleRepository roleRepository;

	public SysRoleService(SysRoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public PageVO<RoleUserVO> findRoleUsers(Long roleId, Pageable pageable) {
		Page<SysUser> page = roleRepository.findRoleUsers(roleId, pageable);

		return new PageVO<>(page, RoleUserVO::new);
	}

}
