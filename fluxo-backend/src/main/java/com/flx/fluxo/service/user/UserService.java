package com.flx.fluxo.service.user;

import com.flx.fluxo.model.DAO.user.UserDAO;
import com.flx.fluxo.model.user.User;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public Optional<User> findByEmail(String email){
        return userDAO.findByEmail(email);
    }

    public void save(User user){
        userDAO.save(user);
    }

    public Long findIdUsuarioAtual(){
        val authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException();
        }
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return userDAO.findByEmail(userDetails.getUsername()).orElseThrow().getId();
    }
}
