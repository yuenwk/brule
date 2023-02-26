package com.example.brule.sys.service;

import com.example.brule.sys.domain.SysUser;
import com.example.brule.sys.dto.UserVO;
import com.example.brule.sys.repository.SysUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysUserService {

	private final SysUserRepository userRepository;

	public SysUserService(SysUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public List<UserVO> list() {
		List<SysUser> all = userRepository.findAll();

		return all.stream()
			.map(u -> new UserVO(u.getId(), u.getUsername(), u.getFullName(), u.getAvatar(), u.getGender(),
					u.getState(), u.getCreatedTime()))
			.toList();
	}

}
