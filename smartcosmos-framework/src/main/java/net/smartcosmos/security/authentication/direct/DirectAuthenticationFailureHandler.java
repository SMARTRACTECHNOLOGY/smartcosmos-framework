package net.smartcosmos.security.authentication.direct;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

/**
 * Returns a 401 error code (Unauthorized) to the client rather than displaying an error
 * page.
 * <p>
 * Code adapted from https://jhipster.github.io/
 *
 * @author voor
 * @see org.springframework.security.web.authentication.
 * SimpleUrlAuthenticationFailureHandler
 */
@Component
public class DirectAuthenticationFailureHandler
        extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException exception)
                    throws IOException, ServletException {

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Authentication failed");
    }
}
