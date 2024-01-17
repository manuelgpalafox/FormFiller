package com.magp.creditformfiller.dao;

import com.magp.creditformfiller.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
