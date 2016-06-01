<%@page import="fr.pizzeria.model.Pizza"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Lister Pizzas</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="<%=request.getContextPath() %>/bootstrap/css/bootstrap.css">
 
</head>
<body>

<h1>Page Liste pizzas</h1>

<table class="table table-striped">
	<thead>
		<tr>
			<td>ID</td>
			<td>CODE</td>
			<td>NOM</td>
			<td>IMAGE</td>
			<td>PRIX</td>
		</tr>
	</thead>
	<tbody>
<%
	List<Pizza> listPizzas = (List<Pizza>) request.getAttribute("listePizzas");
	
	for(Pizza p : listPizzas) {
%>
	<tr>
		<td><%=p.getId() %></td>
		<td><%=p.getCode() %></td>
		<td><%=p.getNom() %></td>
		<td><img src="<%= p.getUrlImage() %>"></td>
		<td><%=p.getPrix() %></td>
	</tr>

<%
	}
%>
</tbody>
</table>



</body>
</html>