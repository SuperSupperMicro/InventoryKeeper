package com.heftyb.inventorykeeper.repositories;

import com.heftyb.inventorykeeper.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByGoogleId(String GID);
    User findByEmail(String email);
}
