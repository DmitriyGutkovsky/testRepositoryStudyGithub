package com.noirix.filter;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    // remade filter's logic :  if there is no X-Auth-Token, the request should be redirect to error page 500

//    if (StringUtils.isNotBlank(autHeader)) { // for future JWT Token Auth
//      System.out.println("Header was found with payload: " + autHeader);
//    } else {
//      System.out.println("Header was not found");
//      castedRequest.getRequestDispatcher("/WEB-INF/errors/500.jsp").forward(request, response);
//      castedRequest.getRequestDispatcher("/error500").forward(request, response);

//      ((HttpServletResponse)response).sendError(500);
//                    =
//      ((HttpServletResponse)response).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
//    }

    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {}
}
