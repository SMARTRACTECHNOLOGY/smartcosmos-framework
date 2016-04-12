package net.smartcosmos.spring;

import java.lang.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Convenience annotation designed to facilitate migration patterns in the future.
 * Designates a REST endpoint as one that will provide access to objects remotely, and
 * possibly play part into a composite service down the lines.
 *
 * @author voor
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Controller
@ResponseBody
@Documented
public @interface SmartCosmosRdao {
}
