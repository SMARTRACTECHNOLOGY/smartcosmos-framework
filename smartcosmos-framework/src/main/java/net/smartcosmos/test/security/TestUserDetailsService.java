package net.smartcosmos.test.security;

import java.util.Collections;
import java.util.Map;

import net.smartcosmos.security.user.SmartCosmosUser;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author voor
 */
public class TestUserDetailsService implements UserDetailsService {

    final Map<String, SmartCosmosTestProperties.TestUser> users;

    public TestUserDetailsService(Map<String, SmartCosmosTestProperties.TestUser> users) {
        this.users = users;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        if (!users.containsKey(username)) {
            throw new UsernameNotFoundException(
                    "Could not find test user account " + username);
        }
        return new SmartCosmosUser(users.get(username).getAccountUrn(),
                users.get(username).getUserUrn(), username, "password",
                Collections.emptyList());
    }
}
