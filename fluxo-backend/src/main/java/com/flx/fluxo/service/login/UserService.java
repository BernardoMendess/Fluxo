package com.flx.fluxo.service.login;

import com.flx.fluxo.model.DAO.login.UserDAO;
import com.flx.fluxo.model.login.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UserService {

    @Autowired
    private UserDAO userDAO;

    public Optional<User> findByEmail(String email){
        return userDAO.findByEmail(email);
    }

    public void save(User user){
        userDAO.save(user);
    }
}
