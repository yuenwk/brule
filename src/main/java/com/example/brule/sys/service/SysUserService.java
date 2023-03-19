package com.example.brule.sys.service;

import com.example.brule.core.protocol.AppException;
import com.example.brule.core.protocol.CommonResultStatus;
import com.example.brule.sys.domain.SysUser;
import com.example.brule.sys.dto.UserRequest;
import com.example.brule.sys.dto.UserVO;
import com.example.brule.sys.repository.SysUserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class SysUserService {

    SysUserRepository userRepository;

    public List<UserVO> list() {
        List<SysUser> all = userRepository.findAll();

        return all.stream().map(UserVO::new).toList();
    }

    public SysUser findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new AppException(CommonResultStatus.RECORD_NOT_EXIST));
    }

    public SysUser create(UserRequest user) {
        SysUser entity = new SysUser();
        entity.setUsername(user.username())
            .setFullName(user.fullName())
            .setAvatar(user.avatar())
            .setGender(user.gender())
            .setState(SysUser.State.NORMAL)
            .setCreatedTime(LocalDateTime.now());

        return userRepository.save(entity);
    }

    public SysUser update(Long id, UserRequest user) {
        SysUser entity = findById(id);
        entity.setUsername(user.username())
            .setFullName(user.fullName())
            .setAvatar(user.avatar())
            .setGender(user.gender())
            .setState(SysUser.State.NORMAL)
            .setCreatedTime(LocalDateTime.now());

        return userRepository.save(entity);
    }

}
