<%@page import="java.math.BigDecimal"%>
<%@page import="fr.pizzeria.model.CategoriePizza"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fr.pizzeria.model.Pizza"%>
<%@page import="java.util.List"%>
<%@page import="fr.pizzeria.dao.pizza.PizzaDaoImpl"%>
<html>
<body>

<%
List<Pizza> list = new ArrayList<>();
list.add( new Pizza(1, "PEP", "Pépéroni", BigDecimal.valueOf(12.50), CategoriePizza.VIANDE, "http://placehold.it/150x150"));
list.add(new Pizza(2, "MAR", "Margherita", BigDecimal.valueOf(14.00),  CategoriePizza.SANS_VIANDE, "http://placehold.it/150x150"));
	
for (Pizza pizza:new PizzaDaoImpl().findAllPizzas()) {
	

%>
Code = <%=pizza.getCode() %>
Nom = <%=pizza.getNom() %>
<h2>Hello World!</h2>


<%
}
%>
</body>
</html>
