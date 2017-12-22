<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap/css/bootstrap.css">
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<div class="container" style="margin-left: auto; margin-right: auto;">
	<div class="row">
		<div class="col-md-6 col-md-offset-3">
			<div class="well well-sm">
				<a type="button"class="btn btn-link">employes</button>
				<button type="button" class="btn btn-link">Bulletins</button>
			</div>
			<center>
				<h1>Liste les Employes</h1>
			</center>

			<a class="btn btn-secondary" style="float: right" href="<c:url value='creer'/>">Ajouter un employe</a>

			<br><br><br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>Date/heure de création</th>
						<th>Matricule</th>
						<th>Grade</th>
					</tr>
				</thead>
				<tbody>
					<tr>
					<c:forEach var="employe" items="${employes}">
					   <td><${employe.entreprise.denomination}</td>
						<td>${employe.matricule}</td>
						<td>${employe.grade.code}</td>
	
						</c:forEach>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
					<tr>
						<td></td>
						<td></td>
						<td></td>
					</tr>
				</tbody>
			</table>
		</div>

		</body>
</html>

