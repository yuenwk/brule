package com.example.brule.sys.repository;

import com.example.brule.sys.domain.SysResource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysResourceRepository extends JpaRepository<SysResource, Long> {

}