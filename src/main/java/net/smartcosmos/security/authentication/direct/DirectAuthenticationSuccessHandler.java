package net.smartcosmos.security.authentication.direct;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * Success handler that returns OK rather than redirects to another page.
 * <p>
 * Code adapted from https://jhipster.github.io/
 *
 * @author voor
 * @see org.springframework.security.web.authentication.
 * SimpleUrlAuthenticationSuccessHandler
 */
@Component
public class DirectAuthenticationSuccessHandler
		extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
					throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_OK);
	}
}
