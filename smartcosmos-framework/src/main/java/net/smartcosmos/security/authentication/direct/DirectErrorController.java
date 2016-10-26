package net.smartcosmos.security.authentication.direct;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.DefaultErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Takes the {@link org.springframework.boot.autoconfigure.web.BasicErrorController} and
 * removes any instance of accidentally returning text in the response.
 *
 * @author voor
 * @see org.springframework.boot.autoconfigure.web.BasicErrorController
 */
@Controller
@Slf4j
@RequestMapping("${server.error.path:${error.path:/error}}")
public class DirectErrorController extends AbstractErrorController {

    private static final String PATH = "/error";
    private final ErrorProperties errorProperties;

    /**
     * Create a new {@link DirectErrorController} instance.
     */
    @Autowired
    public DirectErrorController(ServerProperties properties) {

        super(new DefaultErrorAttributes());
        Assert.notNull(properties.getError(), "ErrorProperties must not be null");
        this.errorProperties = properties.getError();
    }

    @RequestMapping(produces = "text/html")
    public ModelAndView errorHtml(HttpServletRequest request,
                                  HttpServletResponse response) {
        response.setStatus(getStatus(request).value());
        Map<String, Object> model = getErrorAttributes(request,
            isIncludeStackTrace(request, MediaType.TEXT_HTML));
        return new ModelAndView("error", model);
    }

    @RequestMapping
    @ResponseBody
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> body = getErrorAttributes(request,
                isIncludeStackTrace(request, MediaType.ALL));
        HttpStatus status = getStatus(request);

        return new ResponseEntity<>(body, status);
    }

    /**
     * Determine if the stacktrace attribute should be included.
     *
     * @param request the source request
     * @param produces the media type produced (or {@code MediaType.ALL})
     * @return if the stacktrace attribute should be included
     */
    protected boolean isIncludeStackTrace(HttpServletRequest request,
            MediaType produces) {
        ErrorProperties.IncludeStacktrace include = getErrorProperties()
                .getIncludeStacktrace();
        if (include == ErrorProperties.IncludeStacktrace.ALWAYS) {
            return true;
        }
        if (include == ErrorProperties.IncludeStacktrace.ON_TRACE_PARAM) {
            return getTraceParameter(request);
        }
        return false;
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }

    /**
     * Provide access to the error properties.
     *
     * @return the error properties
     */
    protected ErrorProperties getErrorProperties() {
        return this.errorProperties;
    }
}
