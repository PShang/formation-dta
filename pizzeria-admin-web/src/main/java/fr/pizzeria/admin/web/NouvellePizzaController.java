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


@WebServlet("/pizzas/new")
public class NouvellePizzaController extends HttpServlet {

	public static final String URL = "/pizzas/new";
	private static final String VUE_EDITER_PIZZA = "/WEB-INF/views/pizzas/editerPizza.jsp";
	@Inject private PizzaService pizzaService;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("pizza", new Pizza());
		this.getServletContext().getRequestDispatcher(VUE_EDITER_PIZZA).forward(req, resp);
	}

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String urlImage = req.getParameter("urlImage");
        String prix = req.getParameter("prix");
        String code = req.getParameter("code");
        String categorie = req.getParameter("categorie");

        if (isBlank(nom) || isBlank(urlImage) || isBlank(prix)) {
            req.setAttribute("msgErreur", "Tous les paramètres sont obligatoires !");
            this.getServletContext().getRequestDispatcher(VUE_EDITER_PIZZA).forward(req, resp);
        } else {
            Pizza pizzaSansId = new Pizza(code, nom, new BigDecimal(prix), CategoriePizza.VIANDE);
            pizzaSansId.setUrlImage(urlImage);
            try {
                pizzaService.savePizza(pizzaSansId);
            } catch (DaoException e) {
                e.printStackTrace();
                resp.sendRedirect(this.getServletContext().getContextPath() + "/pizzas/list");
            }

        }
    }

    protected boolean isBlank(String param) {
		return param == null || param.isEmpty();

    }

}
