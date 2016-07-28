package net.smartcosmos.test.security;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.annotation.SecurityTestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import net.smartcosmos.security.user.SmartCosmosUser;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SecurityTestExecutionListeners
public class WithMockSmartCosmosUserSecurityContextFactoryTest {

    private SecurityContext securityContext;
    private Authentication authentication;
    private SmartCosmosUser user;

    @Before
    public void setUp() {
        securityContext = SecurityContextHolder.getContext();
        authentication = securityContext.getAuthentication();
        user = (SmartCosmosUser) authentication.getPrincipal();
    }

    @Test
    @WithMockSmartCosmosUser
    public void thatPrincipalIsSmartCosmosUser() {
        Object principal = authentication.getPrincipal();

        assertNotNull(principal);
        assertTrue(principal instanceof SmartCosmosUser);
    }

    @Test
    @WithMockSmartCosmosUser
    public void thatDefaultValuesAreSet() {

        assertTrue(user.getAuthorities().isEmpty());
        assertEquals("user", user.getUsername());
        assertEquals("password", user.getPassword());
        assertEquals("urn:user:uuid:e2174814-eb6d-4176-978c-58390105375b", user.getUserUrn());
        assertEquals("urn:tenant:uuid:b5b52af3-9909-4ea4-97bf-07639d683c95", user.getAccountUrn());
    }

    @Test
    @WithMockSmartCosmosUser(username = "myNewUser")
    public void thatUserNameCanBeSet() {

        assertEquals("myNewUser", user.getUsername());
    }

    @Test
    @WithMockSmartCosmosUser(password = "xyz123")
    public void thatPasswordCanBeSet() {

        assertEquals("xyz123", user.getPassword());
    }

    @Test
    @WithMockSmartCosmosUser(usernUrn = "urn:user:new")
    public void thatUserUrnCanBeSet() {

        assertEquals("urn:user:new", user.getUserUrn());
    }

    @Test
    @WithMockSmartCosmosUser(tenantUrn = "urn:tenant:new")
    public void thatTenantUrnCanBeSet() {

        assertEquals("urn:tenant:new", user.getAccountUrn());
    }

    @Test
    @WithMockSmartCosmosUser(authorities = { "authority1", "authority2"} )
    public void thatAuthoritiesCanBeSet() {

        final GrantedAuthority authority1 = new SimpleGrantedAuthority("authority1");
        final GrantedAuthority authority2 = new SimpleGrantedAuthority("authority2");

        assertFalse(user.getAuthorities().isEmpty());
        assertEquals(2, user.getAuthorities().size());
        assertTrue(user.getAuthorities().contains(authority1));
        assertTrue(user.getAuthorities().contains(authority2));
    }
}
