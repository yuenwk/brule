package com.example.brule.sys.service;

import com.example.brule.core.AppException;
import com.example.brule.core.CommonResultStatus;
import com.example.brule.sys.domain.SysUser;
import com.example.brule.sys.repository.SysUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements UserDetailsService {

	final SysUserRepository userRepository;

	public LoginService(SysUserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		SysUser user = userRepository.findByUsername(username)
			.orElseThrow(() -> new AppException(CommonResultStatus.UNAUTHORIZED.getCode(), "用户名或密码错误！"));
		// Set<SysRole> roles = user.getRoles();

		return user;
	}

}
