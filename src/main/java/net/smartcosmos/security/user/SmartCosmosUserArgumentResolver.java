package net.smartcosmos.security.user;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author voor
 */
@Component
public class SmartCosmosUserArgumentResolver implements HandlerMethodArgumentResolver {

	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().equals(SmartCosmosUser.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
			ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
			WebDataBinderFactory binderFactory) throws Exception {

		HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();

		if (null != SecurityContextHolder.getContext()) {
			Object principal = SecurityContextHolder.getContext().getAuthentication()
					.getPrincipal();

			if (principal instanceof OAuth2Authentication) {
				principal = ((OAuth2Authentication) principal).getPrincipal();
			}

			if (principal instanceof SmartCosmosUser) {
				return principal;
			}
		}

		throw new UserPrincipalNotFoundException(
				"Could not find Smart Cosmos User in current request.");
	}
}
