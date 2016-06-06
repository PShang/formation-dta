package fr.pizzeria.admin.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class AuthentificationController extends HttpServlet {
	
	public static final String URL = "/login";

	private static final String VUE_LOGIN = "/WEB-INF/views/auth/login.jsp";

	public static final String AUTH_EMAIL = "auth_email";
	
	private static final String EMAIL = "admin@pizzeria.fr";
	private static final String MOTDEPASSE = "admin";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_LOGIN).forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String motDePasse = req.getParameter("motDePasse");

		if (EMAIL.equals(email) && MOTDEPASSE.equals(motDePasse)) {
			req.getSession(true).setAttribute(AUTH_EMAIL, email);
			resp.sendRedirect(this.getServletContext().getContextPath() + "/pizzas/list");
		} else {
			req.setAttribute("msgErreur", "Email ou Mot de passe incorret");
			this.getServletContext().getRequestDispatcher(VUE_LOGIN).forward(req, resp);
		}
	}

}
