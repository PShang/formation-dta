package fr.pizzeria.admin.web.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

@Path("/pizzas")
public class PizzaResource {
	
	@Inject private PizzaService pizzaService;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pizza> listerPizzas() throws DaoException {
		return pizzaService.findAllPizzas();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public void creerPizza(Pizza pizzaSansId) throws DaoException {
		pizzaService.savePizza(pizzaSansId);
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	public void mettreAJourPizza(Pizza pizza) throws DaoException {
		pizzaService.updatePizza(pizza.getCode(), pizza);
	}
	
	@DELETE
	@Path("{code}")
	public void supprimerPizza(@PathParam("code") String code) throws DaoException {
		pizzaService.deletePizza(code);
	}

}
