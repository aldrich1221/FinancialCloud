package com.finance.basicassetallocation.security.repositories;



import com.finance.basicassetallocation.security.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Map;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByRolename(String Rolename);
}