package com.noirix.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LanguageFilter implements Filter {
  @Override
  public void init(FilterConfig filterConfig) throws ServletException {}

  @Override
  public void doFilter(
      ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
      throws IOException, ServletException {
    System.out.println("I'm in LanguageFilter");
    HttpServletRequest castedRequest = (HttpServletRequest) servletRequest;
    String langHeader = castedRequest.getHeader("Locale-Language");

    if (StringUtils.isNotBlank(langHeader)) {
      System.out.println("Custom Language header was founded: " + langHeader);
    } else {
      System.out.println("Custom Language header was not founded");
    }

    filterChain.doFilter(servletRequest, servletResponse);
  }

  @Override
  public void destroy() {}
}
