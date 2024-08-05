package com.finance.authenticator.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;

//public interface UserRepository extends JpaRepository<User, Long> {
//    User findByUsername(String username);
//}

@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long> {
    public UserInfo findByUsername(String username);
}