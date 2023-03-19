package com.example.brule.sys.controller;

import com.example.brule.sys.dto.PageVO;
import com.example.brule.sys.dto.RoleUserVO;
import com.example.brule.sys.service.SysRoleService;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/role")
public class SysRoleController {

    final SysRoleService roleService;

    public SysRoleController(SysRoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/{roleId}/users")
    public PageVO<RoleUserVO> users(@PathVariable Long roleId, Pageable page) {
        return roleService.findRoleUsers(roleId, page);
    }

}
