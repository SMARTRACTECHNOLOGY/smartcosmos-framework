package net.smartcosmos.security.authentication.direct;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Returns a 401 error code (Unauthorized) to the client rather than displaying an error
 * page.
 * <p>
 * Code adapted from https://jhipster.github.io/
 *
 * @author voor
 * @see SimpleUrlAuthenticationFailureHandler
 *
 */
@Component
public class DirectAuthenticationFailureHandler
        extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
            HttpServletResponse response, AuthenticationException exception)
                    throws IOException, ServletException {

        if (response.getContentType() == null || response.getContentType().startsWith("text/")) {
            super.onAuthenticationFailure(request, response, exception);
        }
        else {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
                    "Authentication failed");
        }
    }
}
