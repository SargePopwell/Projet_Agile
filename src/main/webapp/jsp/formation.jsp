<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="beans.Formation"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/style.css">
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
		<td colspan = 7>Formateur Intervenant = ${ d.surnameFormer } ${ d.nameFormer }</td>
	</tr>
		<c:forEach var="i" items="${ interns }">
		
		<tr>
			<td>${ i.surname }</td>
			<td>${ i.name }</td>
			<td>${ d.date }</td>
			
			<c:forEach var="hd" items="${ i.halfdays }">
			<c:if test="${ hd.date == d.date }">
			
			<c:choose>
				<c:when test="${ hd.ichecked }">
				<td>YES</td>
				</c:when>
				<c:when test="${ !hd.ichecked }">
				<td>NOPE</td>
				</c:when>
				<c:otherwise>
				<td>NOT YET</td>
				</c:otherwise>
			</c:choose>
			
			</c:if>
			</c:forEach>
			
			<c:forEach var="hd" items="${ i.halfdays }">
			<c:if test="${ hd.date == d.date }">
			
			<c:choose>
				<c:when test="${ hd.fchecked }">
				<td>YES</td>
				</c:when>
				<c:when test="${ !hd.fchecked }">
				<td>NOPE</td>
				</c:when>
				<c:otherwise>
				<td>NOT YET</td>
				</c:otherwise>
			</c:choose>
			
			</c:if>
			</c:forEach>
		</tr>
		</c:forEach>
	</c:forEach>	
</table>

</body>
</html>