/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.filter;

import com.mycompany.model.AdminDTO;
import com.mycompany.model.DoctorDTO;
import com.mycompany.model.PatientDTO;
import java.io.IOException;
import java.util.logging.Logger;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Marcin Kaczorowski, Karol Nowicki
 */
@WebFilter(filterName = "AuthorizationFilter", urlPatterns = {"*.xhtml"})
public class AuthorizationFilter implements Filter {

    private static Logger log = Logger.getLogger(AuthorizationFilter.class.getName());
    
    public AuthorizationFilter() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        try {

            HttpServletRequest reqt = (HttpServletRequest) request;
            HttpServletResponse resp = (HttpServletResponse) response;
            HttpSession ses = reqt.getSession(false);

            String reqURI = reqt.getRequestURI();
            
            boolean adminLogin = ses != null && ses.getAttribute("username") != null && reqURI.indexOf("/admin/") >= 0 && ses.getAttribute("username") instanceof AdminDTO;
            boolean doctorLogin = ses != null && ses.getAttribute("username") != null && reqURI.indexOf("/doctor/") >= 0 && ses.getAttribute("username") instanceof DoctorDTO;
            boolean patientLogin = ses != null && ses.getAttribute("username") != null && reqURI.indexOf("/patient/") >= 0 && ses.getAttribute("username") instanceof PatientDTO;
            boolean t = reqt.getRequestURI().startsWith(reqt.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER);

            if (reqURI.indexOf("/login.xhtml") >= 0 || adminLogin || doctorLogin || patientLogin || t) {
                chain.doFilter(request, response);
            } else {
                log.warning("Zablokowano nieautoryzowany dostęp do zasobów");
                resp.sendRedirect(reqt.getContextPath() + "/login.xhtml");
            }
        } catch (Exception e) {
            log.warning(e.getMessage());
        }
    }

    @Override
    public void destroy() {

    }

}
