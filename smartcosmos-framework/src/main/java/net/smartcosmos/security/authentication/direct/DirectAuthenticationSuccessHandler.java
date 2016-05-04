package net.smartcosmos.security.authentication.direct;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Success handler that returns OK for anything that is not text rather than redirect to another page.
 * <p>
 * Code adapted from https://jhipster.github.io/
 *
 * @author voor
 * @see SimpleUrlAuthenticationSuccessHandler
 */
@Component
public class DirectAuthenticationSuccessHandler
        extends SimpleUrlAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
            HttpServletResponse response, Authentication authentication)
                    throws IOException, ServletException {

        if (StringUtils.startsWithIgnoreCase(response.getContentType(),"text/")) {
            handle(request,response,authentication);
        } else {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        clearAuthenticationAttributes(request);
    }
}
