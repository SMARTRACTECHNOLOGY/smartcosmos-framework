package net.smartcosmos.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * Often times Spring Security or other beans will override and rewrite the
 * defaultFailureUrl, which will cause a redirect to a non-existent login page. This class
 * merely overrides that one, automatically always sending a 401 response.
 *
 * @author voor
 * @see org.springframework.security.web.authentication.
 * SimpleUrlAuthenticationFailureHandler
 */
@Component
public class EndpointFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException exception)
                    throws IOException, ServletException {

        logger.debug("No failure URL set, sending 401 Unauthorized error");
        logger.trace(exception.getMessage(), exception);

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication Failed.");
    }
}
