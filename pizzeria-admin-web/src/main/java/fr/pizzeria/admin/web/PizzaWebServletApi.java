package fr.pizzeria.admin.web;

import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.dao.pizza.IPizzaDao;
import fr.pizzeria.dao.pizza.PizzaDaoMemoire;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.Scanner;

public class PizzaWebServletApi extends HttpServlet {


	@Inject private PizzaService pizzaService;

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pizza pizzaAvecId = getPizzaDepuisRequete(req);
        try {
        	pizzaService.deletePizza(pizzaAvecId.getCode());
        } catch (DaoException e) {
            resp.setStatus(400);
            resp.getWriter().write(e.getMessage());
        }
    }

    private Pizza getPizzaDepuisRequete(HttpServletRequest req) throws IOException {
        try (Scanner sc = new Scanner(req.getInputStream())) {
            String data = sc.nextLine();
            Pizza pizzaAvecId = new Pizza();
            Arrays.asList(data.split("&")).stream().forEach(elt -> {
                String[] keyValue = elt.split("=");
                try {
                    switch (keyValue[0]) {
                        case "nom":
                            pizzaAvecId.setNom(URLDecoder.decode(keyValue[1], "utf-8"));
                            break;
                        case "prix":
                            pizzaAvecId.setPrix(new BigDecimal(keyValue[1]));
                            break;
                        case "urlImage":
                            pizzaAvecId.setUrlImage(URLDecoder.decode(keyValue[1], "utf-8"));
                            break;
                        case "id":
                            pizzaAvecId.setId(Integer.valueOf(keyValue[1]));
                            break;
                    }

                } catch (UnsupportedEncodingException e) {
                    throw new RuntimeException(e);
                }
            });
            return pizzaAvecId;
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            resp.getWriter().write(pizzaService.findAllPizzas().toString());
        } catch (DaoException e) {
            resp.setStatus(400);
            resp.getWriter().write(e.getMessage());
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("code");
        String nom = req.getParameter("nom");
        String categorie = req.getParameter("categorie");
        String prixString = req.getParameter("prix");
        Pizza pizzaSansId = new Pizza(code, nom, new BigDecimal(prixString), CategoriePizza.valueOf(categorie));
        try {
        	pizzaService.savePizza(pizzaSansId);
            resp.setStatus(201);
        } catch (DaoException e) {
            resp.setStatus(400);
            resp.getWriter().write(e.getMessage());
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Pizza pizzaAvecId = getPizzaDepuisRequete(req);
        try {
        	pizzaService.updatePizza(pizzaAvecId.getCode(), pizzaAvecId);
        } catch (DaoException e) {
            resp.setStatus(400);
            resp.getWriter().write(e.getMessage());
        }
    }


}
