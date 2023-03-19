package com.example.brule.sys.service;

import com.example.brule.core.protocol.AppException;
import com.example.brule.core.protocol.CommonResultStatus;
import com.example.brule.sys.domain.SysResource;
import com.example.brule.sys.dto.ResourceRequest;
import com.example.brule.sys.repository.SysResourceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class SysResourceService {

	SysResourceRepository resourceRepository;

	public SysResource findById(Long id) {
		return resourceRepository.findById(id).orElseThrow(() -> new AppException(CommonResultStatus.RECORD_NOT_EXIST));
	}

	public SysResource create(ResourceRequest resource) {
		SysResource entity = new SysResource();
		entity.setName(resource.name())
			.setType(resource.type())
			.setPermission(resource.permission())
			.setParent(findById(resource.parentId()))
			.setIcon(resource.icon())
			.setUrl(resource.url());

		return resourceRepository.save(entity);
	}


	public SysResource update(Long id, ResourceRequest resource) {
		SysResource entity = findById(id);

		entity.setName(resource.name())
			.setType(resource.type())
			.setPermission(resource.permission())
			.setParent(findById(resource.parentId()))
			.setIcon(resource.icon())
			.setUrl(resource.url());

		return resourceRepository.save(entity);
	}

}
