package com.mycompany.jeel.user;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository  extends CrudRepository<User, Long> {

    Optional<User> findById(Integer id);
    public long countById(Integer id);
}
