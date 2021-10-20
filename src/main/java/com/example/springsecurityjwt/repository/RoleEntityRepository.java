package com.example.springsecurityjwt.repository;

import com.example.springsecurityjwt.entity.RoleEntity;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);
}
