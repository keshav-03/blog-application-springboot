package com.springboot.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.entity.Role;
import java.util.*;


public interface RoleRepository  extends JpaRepository<Role, Long>{
    
    Optional<Role> findByName(String name);

}
