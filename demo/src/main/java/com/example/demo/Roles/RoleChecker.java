package com.example.demo.Roles;



import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class RoleChecker {
    private static final Logger logger = LoggerFactory.getLogger(RoleChecker.class);

    public boolean isAdmin(List<String> roles) {
        if (roles == null || roles.isEmpty()) {
            logger.warn("Roles list is null or empty");
            return false;
        }

        try {
            Set<Role> roleSet = roles.stream()
                    .map(role -> {
                        try {
                            return new Role(ERole.valueOf(role));
                        } catch (IllegalArgumentException e) {
                            logger.error("Invalid role name: {}", role, e);
                            return null;
                        }
                    })
                    .filter(role -> role != null)
                    .collect(Collectors.toSet());

            boolean isAdmin = roleSet.stream()
                    .anyMatch(role -> role.getName().equals(ERole.ROLE_ADMIN));

            logger.info("isAdmin check result: {}", isAdmin);
            return isAdmin;
        } catch (Exception e) {
            logger.error("Error while checking admin role: {}", e.getMessage(), e);
            return false;
        }
    }
}


