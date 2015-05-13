package com.swiftcorp.portal.common.web;


import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GZIPFilter
  implements Filter
{
  public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
    throws IOException, ServletException
  {
    if ((req instanceof HttpServletRequest)) {
      HttpServletRequest request = (HttpServletRequest)req;
      HttpServletResponse response = (HttpServletResponse)res;
      String ae = request.getHeader("accept-encoding");
      if ((ae != null) && (ae.indexOf("gzip") != -1)) {
        GZIPResponseWrapper wrappedResponse = new GZIPResponseWrapper(response);
        try {
          chain.doFilter(req, wrappedResponse);
        } catch (ServletException e) {
          throw e;
        } catch (Exception e) {
          throw new ServletException(e);
        } finally {
          wrappedResponse.finishResponse();
          //ret;
        }
      }
      //chain.doFilter(req, res);
    }
  }

  public void init(FilterConfig filterConfig)
  {
  }

  public void destroy()
  {
  }
}