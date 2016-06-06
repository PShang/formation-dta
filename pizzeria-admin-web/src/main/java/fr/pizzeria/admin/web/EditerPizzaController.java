package fr.pizzeria.admin.web;

import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.dao.pizza.IPizzaDao;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/pizzas/edit")
public class EditerPizzaController extends HttpServlet {

	private static final Logger LOG = Logger
			.getLogger(EditerPizzaController.class.getName());

	public static final String URL = "/pizzas/edit";
	private static final String VUE_EDITER_PIZZA = "/WEB-INF/views/pizzas/editerPizza.jsp";

	@Inject private PizzaService pizzaService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String code = req.getParameter("code");

		if (code == null || code.isEmpty()) {
			resp.setStatus(400); // Bad Request
			req.setAttribute("msgErreur",
					"Code obligatoire pour editer une pizza");
			this.getServletContext().getRequestDispatcher(VUE_EDITER_PIZZA)
					.forward(req, resp);
		} else {
			try {
				Pizza pizza = this.pizzaService.findOnePizza(code);
				if (pizza == null) {
					sendErrorPizzaInconnue(req, resp);
				} else {
					req.setAttribute("pizza", pizza);
					this.getServletContext()
							.getRequestDispatcher(VUE_EDITER_PIZZA)
							.forward(req, resp);
				}

			} catch (DaoException e) {
				LOG.log(Level.SEVERE, "Erreur de récupération de pizza", e);
				sendErrorPizzaInconnue(req, resp);
			}
		}

	}

	private void sendErrorPizzaInconnue(HttpServletRequest req,
			HttpServletResponse resp) throws ServletException, IOException {
		resp.setStatus(400); // Bad Request
		req.setAttribute("msgErreur", "Code pizza inconnu");
		this.getServletContext().getRequestDispatcher(VUE_EDITER_PIZZA)
				.forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String id = req.getParameter("id");
		String code = req.getParameter("code");
		String nom = req.getParameter("nom");
		String urlImage = req.getParameter("urlImage");
		String prix = req.getParameter("prix");
		String categorie = req.getParameter("categorie");

		try {
			if (isBlank(nom) || isBlank(urlImage) || isBlank(prix)) {
				req.setAttribute("pizza", this.pizzaService.findOnePizza(code));
				req.setAttribute("msgErreur",
						"Tous les paramètres sont obligatoires !");
				this.getServletContext().getRequestDispatcher(VUE_EDITER_PIZZA)
						.forward(req, resp);
			} else {
				Pizza pizzaAvecId = new Pizza(Integer.valueOf(id), code, nom,
						new BigDecimal(prix), CategoriePizza.VIANDE, urlImage);
				try {
					pizzaService.updatePizza(code, pizzaAvecId);
				} catch (DaoException e) {
					e.printStackTrace();
				}
				resp.sendRedirect(this.getServletContext().getContextPath()
						+ "/pizzas/list");
			}

		} catch (DaoException e) {
			LOG.log(Level.SEVERE, "Erreur de récupération de pizza", e);
		}

	}

	protected boolean isBlank(String param) {
		return param == null || param.isEmpty();
	}

}
