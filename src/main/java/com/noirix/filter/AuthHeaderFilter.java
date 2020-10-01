package com.noirix.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class AuthHeaderFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    System.out.println("I'm AuthHeaderFilter");

    HttpServletRequest castedRequest = (HttpServletRequest) request;
    String autHeader = castedRequest.getHeader("X-Auth-Token");
    if (StringUtils.isNotBlank(autHeader)) { // for future JWT Token Auth
      System.out.println("Header was found with payload: " + autHeader);
    } else {
      System.out.println("Header was not found");
    }
    chain.doFilter(request, response);

  }

  @Override
  public void destroy() {}
}
