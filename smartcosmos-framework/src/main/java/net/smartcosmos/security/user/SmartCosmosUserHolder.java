package net.smartcosmos.security.user;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author voor
 */
public class SmartCosmosUserHolder {

    public static SmartCosmosUser getCurrentUser() {
        if (SecurityContextHolder.getContext() != null
                && SecurityContextHolder.getContext().getAuthentication() != null) {
            Object principal = SecurityContextHolder.getContext().getAuthentication()
                    .getPrincipal();

            if (principal instanceof SmartCosmosUser) {
                return (SmartCosmosUser) principal;
            }
        }
        throw new RuntimeException("No current user, illegal access detected.");
    }
}
