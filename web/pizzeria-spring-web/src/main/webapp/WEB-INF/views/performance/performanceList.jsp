<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
    <title>Performance</title>
    <link rel="stylesheet"
          href="<%=pageContext.getServletContext().getContextPath()%>/bootstrap/css/bootstrap.css">
    <link rel="stylesheet"
          href="<%=pageContext.getServletContext().getContextPath()%>/bootstrap/css/bootstrap-theme.css">
</head>
<body>

<h1>Liste Performance</h1>

${param.msg}
<c:url var="deleteall_url"  value="/mvc/performance/deleteall" />
<c:url var="delete_url"  value="/mvc/performance/delete" />
<form action="${deleteall_url}" method="post">
    <button type="submit">Tout supprimer</button>
</form>

<table class="table">
    <thead>
    <tr>
        <td>Id</td>
        <td>Service</td>
        <td>Temps d'exécution</td>
        <td>Date</td>
        <td></td>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${performanceList}">
        <tr>
            <td>${p.id}</td>
            <td>${p.service}</td>
            <td>${p.tempsExecution}</td>
            <td>${p.date}</td>
            <td>
                <form:form action="${delete_url}" method="post">
                    <input type="hidden" name="id" value="${p.id}">
                    <button type="submit">Supprimer</button>
                </form:form>
            </td>
        </tr>
    </c:forEach>
    </tbody>

</table>


</body>
</html>