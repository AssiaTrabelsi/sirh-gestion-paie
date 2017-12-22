<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
</head>
<body>
	<div class="container" style="margin-left: auto; margin-right: auto;">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="well well-sm">
					<a type="button" class="btn btn-link"
						href="<c:url value='lister'/>">employes</a>
					<button type="button" class="btn btn-link">Bulletins</button>
				</div>
				<center>
					<h1>Créer Bulletin de Salaire</h1>
				</center>
				<form method="POST">

					<div class="form-group">
						<label for="periode">Période</label> <select name="periode_id"
							class="form-control">
							<c:forEach var="periode" items="${periode}">
								<option value="${periode.id}">${periode.dateDebut}${periode.dateFin}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="remunerationemploye">Matricule</label> <select name="periode_id"
							class="form-control">
							<c:forEach var="employe" items="${employes}">
								<option value="${employe.id}">${employe.matricule}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<div class="form-group">
							<label for="primeExceptionnelle">Prime Exceptionnelle</label> <input
								type="text" class="form-control" id="primeExceptionnelle"
								name="primeExceptionnelle">
						</div>
					</div>

					<center>
						<button type="submit" class="btn btn-primary">Créer</button>
					</center>
				</form>
			</div>
		</div>
	</div>
</body>
</html>