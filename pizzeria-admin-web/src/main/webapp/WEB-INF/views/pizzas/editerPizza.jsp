<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<jsp:include page="../layout/entete.jsp">
	<jsp:param value="Page Editer Pizza" name="title" />
</jsp:include>
<body class="container">

	<c:choose>
		<c:when test="${pizza != null && pizza.getId() != null}">
			<h1>Editer la pizza</h1>
		</c:when>
		<c:otherwise>
			<h1>Créer une pizza</h1>
		</c:otherwise>
	</c:choose>
	
	<h1><c:out value="${ (pizza != null && pizza.id != null) ? 'Editer la pizza' : 'Créer une pizza' }"/></h1>

	<c:if test="${!empty msgErreur}">
		<div class="alert alert-danger" role="alert">${msgErreur}</div>
	</c:if>


	<c:if test="${pizza != null}">
		<form method="POST">

			<c:if test="${pizza.id != null}">
				<div class="form-group">
					<label for="id">Id</label> <input type="text" class="form-control"
						id="id" name="id" value="${pizza.id}" readonly>
				</div>
			</c:if>

			<div class="form-group">
				<label for="nom">Nom</label> <input type="text" class="form-control"
					id="nom" name="nom" value="${pizza.nom}">
			</div>
			<div class="form-group">
				<label for="urlImage">URL Image</label> <input type="text"
					class="form-control" name="urlImage" id="urlImage"
					value="${pizza.urlImage}">
			</div>
			<div class="form-group">
				<label for="prix">Prix</label> <input type="text"
					class="form-control" name="prix" id="prix" value="${pizza.prix}">
			</div>

			<button type="submit" class="btn btn-primary">Valider</button>
		</form>
	</c:if>

</body>
</html>