package com.finance.basicassetallocation.repositories;
import com.finance.basicassetallocation.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UserRepository extends JpaRepository<User, Long> {
}
