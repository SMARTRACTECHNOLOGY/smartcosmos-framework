package net.smartcosmos.security;

import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

/**
 * Re-implementing the {@see MethodSecurityExpressionRoot} since it is not public.
 */
public class SmartCosmosMethodSecurityExpressionRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations {

    private Object filterObject;
    private Object returnObject;
    private Object target;

    SmartCosmosMethodSecurityExpressionRoot(Authentication a) {

        super(a);
    }

    public void setFilterObject(Object filterObject) {

        this.filterObject = filterObject;
    }

    public Object getFilterObject() {

        return this.filterObject;
    }

    public void setReturnObject(Object returnObject) {

        this.returnObject = returnObject;
    }

    public Object getReturnObject() {

        return this.returnObject;
    }

    void setThis(Object target) {

        this.target = target;
    }

    public Object getThis() {

        return this.target;
    }
}
