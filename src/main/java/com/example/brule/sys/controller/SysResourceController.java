package com.example.brule.sys.controller;

import com.example.brule.sys.dto.ResourceRequest;
import com.example.brule.sys.service.SysResourceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("sys/resource")
public class SysResourceController {

    SysResourceService resourceService;

    @PostMapping
    public void create(@RequestBody @Valid ResourceRequest resource) {
        resourceService.create(resource);
    }

    @PutMapping("{id}")
    public void update(@RequestBody @Valid ResourceRequest resource, @PathVariable Long id) {
        resourceService.update(id, resource);
    }

}
