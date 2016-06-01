package fr.pizzeria.admin.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.pizzeria.dao.pizza.IPizzaDao;
import fr.pizzeria.dao.pizza.PizzaDaoImpl;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

public class ListerPizzaController extends HttpServlet {
	
	private IPizzaDao pizzaDao = new PizzaDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			List<Pizza> listPizzas = pizzaDao.findAllPizzas();
			req.setAttribute("listePizzas", listPizzas);
			
			RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/pizzas/listerPizzas.jsp");
			requestDispatcher.forward(req, resp);
			
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	

}
