package com.erayserter.server.authentication.repositories;

import com.erayserter.server.authentication.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {

}
