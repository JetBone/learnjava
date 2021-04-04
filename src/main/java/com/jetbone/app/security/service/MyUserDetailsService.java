package com.jetbone.app.security.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.UserDetailsManager;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Chris
 * @date 2021-03-20
 */
public class MyUserDetailsService implements UserDetailsManager {

    private static final Map<String, UserDetails> USERS = new HashMap<>();

    @Override
    public void createUser(UserDetails user) {
        USERS.put(user.getUsername(), user);
    }

    @Override
    public void updateUser(UserDetails user) {
        if (USERS.containsKey(user.getUsername())) {
            USERS.put(user.getUsername(), user);
        } else {
            throw new IllegalArgumentException("user " + user.getUsername() + " does not exists.");
        }
    }

    @Override
    public void deleteUser(String username) {
        if (USERS.containsKey(username)) {
            USERS.remove(username);
        } else {
            throw new IllegalArgumentException("user " + username + " does not exists.");
        }
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext()
                .getAuthentication();

        if (currentUser == null) {
            // This would indicate bad coding somewhere
            throw new AccessDeniedException(
                    "Can't change password as no Authentication object found in context "
                            + "for current user.");
        }

        String username = currentUser.getName();

        if (USERS.containsKey(username)) {
            // TODO
        } else {
            throw new IllegalArgumentException("user " + username + " does not exists.");
        }
    }

    @Override
    public boolean userExists(String username) {
        return USERS.containsKey(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (USERS.containsKey(username)) {
            return USERS.get(username);
        } else {
            throw new UsernameNotFoundException("user " + username + " does not exists.");
        }
    }
}
