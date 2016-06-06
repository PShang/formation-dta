package fr.pizzeria.admin.web;

import fr.pizzeria.admin.metier.PizzaService;
import fr.pizzeria.dao.pizza.IPizzaDao;
import fr.pizzeria.dao.pizza.PizzaDaoMemoire;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public class PizzaWebServlet extends HttpServlet {

	@Inject private PizzaService pizzaService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            List<Pizza> pizzas = pizzaService.findAllPizzas();
            String page = afficherPageListePizzas(pizzas);
            resp.getWriter().write(page);
        } catch (DaoException e) {
            e.printStackTrace();
        }


    }

    private String afficherPageListePizzas(List<Pizza> pizzas) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("<!doctype html>")
                .append("<html>")
                    .append("<head><title>Liste des pizzas</title></head>")
                    .append("<body>")
                        .append("<table>")
                            .append("<tr>")
                                .append("<td>ID</td>")
                                .append("<td>CODE</td>")
                                .append("<td>NOM</td>")
                                .append("<td>IMAGE</td>")
                                .append("<td>PRIX</td>")
                            .append("</tr>");

        pizzas.forEach(p -> {
            sb.append("<tr>")
                    .append("<td>").append(p.getId()).append("</td>")
                    .append("<td>").append(p.getCode()).append("</td>")
                    .append("<td>").append(p.getNom()).append("</td>")
                    .append("<td><img src=\"").append(p.getUrlImage()).append("\"></td>")
                    .append("<td>").append(p.getPrix()).append("</td>")
                .append("<tr>");
        });

        sb.append("</table>");

        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }
}
