package com.example.brule.sys.service;

import com.example.brule.BruleApplicationTests;
import com.example.brule.sys.domain.SysResource;
import com.example.brule.sys.dto.ResourceRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class SysResourceServiceTest extends BruleApplicationTests {

    @Autowired
    SysResourceService sysResourceService;

    @Test
    void create() {
        ResourceRequest resource = new ResourceRequest("仪表盘", SysResource.Type.MENU, 0L, "dashboard", "", "/dashboard");
        sysResourceService.create(resource);
    }

}