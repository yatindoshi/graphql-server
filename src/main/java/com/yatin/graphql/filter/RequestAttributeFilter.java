package com.yatin.graphql.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.catalina.connector.RequestFacade;
import org.springframework.stereotype.Component;

/**
 * Servlet Filter that adds a Servlet request attribute.
 */
@Component
public class RequestAttributeFilter implements Filter {

    public static final String NAME_ATTRIBUTE = RequestAttributeFilter.class.getName() + ".name";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        //((RequestFacade) request).getHeaderNames();
        request.setAttribute(NAME_ATTRIBUTE, "007");
        chain.doFilter(request, response);
    }

}
