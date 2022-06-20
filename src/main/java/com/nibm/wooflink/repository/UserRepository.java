package com.nibm.wooflink.repository;

import com.nibm.wooflink.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
