package fr.pizzeria.admin.web;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.dao.PizzaDaoImpl;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaServletWebApi extends HttpServlet {
	private static final Logger LOG = Logger.getLogger(PizzaServletWebApi.class.toString());

	private IPizzaDao pizzaDao = new PizzaDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			List<Pizza> listPizzas = pizzaDao.findAllPizzas();
			resp.getWriter().write(listPizzas.toString());

			/*
			 * resp.getWriter().write(listPizzas.stream().map(Pizza::toJson).
			 * collect(Collectors.joining()));
			 */
		} catch (DaoException e) {
			resp.sendError(500, "Sorry");

		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String code = req.getParameter("code");
		String nom = req.getParameter("nom");
		String prix = req.getParameter("prix");
		String cat = req.getParameter("categorie");
		String url = req.getParameter("UrlImage");
		
		if (StringUtils.isBlank(code) || StringUtils.isBlank(nom) || StringUtils.isBlank(prix)
				|| StringUtils.isBlank(cat)) {
			resp.sendError(400, "Non");
		}

		else {

		}
		Pizza newPizza = new Pizza(code, nom, new BigDecimal(prix), CategoriePizza.valueOf(cat));
		try {
			pizzaDao.savePizza(newPizza);
			LOG.info("J'ai bien reçu" + code);
		} catch (DaoException e) {
			resp.sendError(500, "Sorry ");
		} catch (NumberFormatException e) {
			resp.sendError(400, "Sorry We couldn't find the number ");
		}

	}
}