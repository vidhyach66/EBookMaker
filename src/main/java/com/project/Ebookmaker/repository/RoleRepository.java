package com.project.Ebookmaker.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.Ebookmaker.entity.ERole;
import com.project.Ebookmaker.entity.Role;




@Repository
public interface RoleRepository extends JpaRepository<Role,Integer> {
  Optional<Role> findByName(ERole name);
}
