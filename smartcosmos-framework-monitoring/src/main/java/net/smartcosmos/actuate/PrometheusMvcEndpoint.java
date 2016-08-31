package net.smartcosmos.actuate;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.prometheus.client.exporter.MetricsServlet;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.actuate.endpoint.mvc.HypermediaDisabled;
import org.springframework.boot.actuate.endpoint.mvc.MvcEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.ServletWrappingController;
import org.springframework.web.util.UrlPathHelper;

/**
 * Wraps the prometheus client MetricsServlet.
 */
@ConfigurationProperties(prefix = "endpoints.prometheus", ignoreUnknownFields = false)
@HypermediaDisabled
public class PrometheusMvcEndpoint implements MvcEndpoint, InitializingBean,
                                              ApplicationContextAware, ServletContextAware,
                                              EnvironmentAware {

    private Environment environment;
    /**
     * Endpoint URL path.
     */
    @NotNull
    @Pattern(regexp = "/.*", message = "Path must start with /")
    private String path = "/prometheus";

    /**
     * Mark if the endpoint exposes sensitive information.
     */
    private Boolean sensitive = false;

    /**
     * Enable the endpoint.
     */
    private boolean enabled = true;

    private final ServletWrappingController controller = new ServletWrappingController();

    public PrometheusMvcEndpoint() {

        this.controller.setServletClass(MetricsServlet.class);
        this.controller.setServletName("prometheus");
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        this.controller.afterPropertiesSet();
    }

    @Override
    public void setServletContext(ServletContext servletContext) {

        this.controller.setServletContext(servletContext);
    }

    //    public void setInitParameters(Properties initParameters) {
    //
    //        this.controller.setInitParameters(initParameters);
    //    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.controller.setApplicationContext(applicationContext);
    }

    @Override
    public void setEnvironment(Environment environment) {

        this.environment = environment;
    }

    public boolean isEnabled() {

        return this.enabled;
    }

    public void setEnabled(boolean enabled) {

        this.enabled = enabled;
    }

    @Override
    public boolean isSensitive() {

        return this.sensitive;
    }

    public void setSensitive(boolean sensitive) {

        this.sensitive = sensitive;
    }

    @Override
    public String getPath() {

        return this.path;
    }

    public void setPath(String path) {

        this.path = path;
    }

    @Override
    @SuppressWarnings("rawtypes")
    public Class<? extends Endpoint> getEndpointType() {

        return null;
    }

    @RequestMapping("/**")
    public ModelAndView handle(HttpServletRequest request, HttpServletResponse response) throws Exception {

        return this.controller.handleRequest(new PathStripper(request, getPath()), response);
    }

    private static class PathStripper extends HttpServletRequestWrapper {

        private final String path;
        private final UrlPathHelper urlPathHelper;

        PathStripper(HttpServletRequest request, String path) {

            super(request);
            this.path = path;
            this.urlPathHelper = new UrlPathHelper();
        }

        @Override
        public String getPathInfo() {

            String value = this.urlPathHelper.decodeRequestString((HttpServletRequest) getRequest(), super.getRequestURI());
            if (value.contains(this.path)) {
                value = value.substring(value.indexOf(this.path) + this.path.length());
            }

            int index = value.indexOf("?");
            if (index > 0) {
                value = value.substring(0, index);
            }
            while (value.startsWith("/")) {
                value = value.substring(1);
            }
            return value;
        }
    }
}
