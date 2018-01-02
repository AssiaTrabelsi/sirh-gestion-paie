<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
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
				<h1>Ajouter un Employe</h1>
				<form method="POST">
					<div class="form-group">
						<label for="matricule">Matricule</label> <input type="text"
							class="form-control" id="matricule" name="matricule"
							placeholder="Entrez votre matricule">
					</div>
					<div class="form-group">
						<label for="entreprise">Entreprise</label> <select
							name="entreprise_id" class="form-control">
							<c:forEach var="entrep" items="${entreprise}">
								<option value="${entrep.id}">${entrep.denomination}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label for="profil">Profil</label> <select class="form-control"
							id="profil" name="profil_id">
							<c:forEach var="profil" items="${ProfilRemuniration}">
								<option value="${profil.id}">${profil.code}</option>
							</c:forEach>

						</select>
					</div>
					<div class="form-group">
						<label for="grade">Grade</label> <select class="form-control"
							id="grade" name="grade_id">
							<c:forEach var="grade" items="${grade}">
								<option value="${grade.id}">${grade.code}</option>
							</c:forEach>
						</select>
					</div>
					<sec:csrfInput />
					<button type="submit" class="btn btn-primary">ajouter</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>