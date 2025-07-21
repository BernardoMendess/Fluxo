package com.flx.fluxo.model.DAO.login;

import com.flx.fluxo.model.login.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserDAO extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
}

