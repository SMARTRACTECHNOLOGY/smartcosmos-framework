package net.smartcosmos.metrics;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import io.prometheus.client.exporter.MetricsServlet;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.Endpoint;
import org.springframework.boot.actuate.endpoint.mvc.HypermediaDisabled;
import org.springframework.boot.actuate.endpoint.mvc.MvcEndpoint;
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
@HypermediaDisabled
public class PrometheusEndpoint implements MvcEndpoint, InitializingBean,
                                           ApplicationContextAware, ServletContextAware,
                                           EnvironmentAware {

    private Environment environment;

    private String path;
    private Boolean sensitive;

    private final ServletWrappingController controller = new ServletWrappingController();

    @Autowired
    public PrometheusEndpoint(PrometheusProperties metricsProperties) {

        this.path = metricsProperties.getPath();
        this.sensitive = metricsProperties.getSensitive();

        this.controller.setServletClass(MetricsServlet.class);
        this.controller.setServletName(metricsProperties.getServletName());
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        this.controller.afterPropertiesSet();
    }

    @Override
    public void setServletContext(ServletContext servletContext) {

        this.controller.setServletContext(servletContext);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        this.controller.setApplicationContext(applicationContext);
    }

    @Override
    public void setEnvironment(Environment environment) {

        this.environment = environment;
    }

    @Override
    public boolean isSensitive() {

        return this.sensitive;
    }

    @Override
    public String getPath() {

        return this.path;
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
