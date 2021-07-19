<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.Formation"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ANPEP - ${ formation.name }</title>
</head>
<body>
<h1>ANPEP</h1>

<h2>Emargements - ${ formation.name }</h2>

<table class="emargement">

<tr>
	<th colspan = 5>Stagiaire</th>
	<th colspan = 2>Formateur</th>
</tr>

<tr>
	<th>Nom</th>
	<th>Prénom</th>
	<th>Date</th>
	<th>Matin</th>
	<th>Après-midi</th>
	<th>Matin</th>
	<th>Après-midi</th>
</tr>

	<c:forEach var="d" items="${ days }">
	<tr>
		<td colspan = 7>Formateur Intervenant = ${ former.name }</td>
	</tr>
		<c:forEach var="i" items="${ interns }">
		<tr>
			<td>${ i.surname }</td>
			<td>${ i.name }</td>
			<td>${ d.date }</td>
			<td>${ presentAM? "good":"pas good" }</td>
			<td>${ presentPM? "good":"pas good" }</td>
			<td>${ presentAMF? "good":"pas good" }</td>
			<td>${ presentPMF? "good":"pas good" }</td>
		</tr>
		</c:forEach>
	
	</c:forEach>


</table>

</body>
</html>