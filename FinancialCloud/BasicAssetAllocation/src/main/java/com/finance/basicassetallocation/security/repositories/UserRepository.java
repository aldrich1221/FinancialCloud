package com.finance.basicassetallocation.security.repositories;


import com.finance.basicassetallocation.security.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findFirstById(Long id);

    Boolean existsByEmail(String email);

    User findByUsernameOrEmail(String username, String email);

    boolean existsByUsername(String username);
}



//public interface UserRepository extends RefreshableCRUDRepository<User, Long> {
//    User findByUsername(String username);
//    User findFirstById(Long id);
//}
