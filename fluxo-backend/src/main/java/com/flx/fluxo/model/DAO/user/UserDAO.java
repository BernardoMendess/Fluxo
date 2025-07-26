package com.flx.fluxo.model.DAO.user;

import com.flx.fluxo.model.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserDAO extends CrudRepository<User, Long> {

    Optional<User> findByEmail(String email);
}

