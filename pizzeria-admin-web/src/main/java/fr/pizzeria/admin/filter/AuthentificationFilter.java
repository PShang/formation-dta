package fr.pizzeria.admin.filter;


import fr.pizzeria.admin.web.AuthentificationController;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/*")
public class AuthentificationFilter implements Filter {

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) req;
		HttpServletResponse httpResponse = (HttpServletResponse) resp;
		String emailAuthentifie = (String) httpRequest.getSession(true).getAttribute("email");
		
		if (!httpRequest.getRequestURI().contains("/api") && !httpRequest.getRequestURI().contains("/login") &&  emailAuthentifie == null) {
			httpResponse.sendRedirect(httpRequest.getServletContext().getContextPath() + AuthentificationController.URL);
		} else {
			chain.doFilter(req, resp);
		}
		
	}

	@Override
	public void init(FilterConfig config) throws ServletException {

	}

}
