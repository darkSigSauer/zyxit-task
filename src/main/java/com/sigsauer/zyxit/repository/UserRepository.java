package com.sigsauer.zyxit.repository;

import com.sigsauer.zyxit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
