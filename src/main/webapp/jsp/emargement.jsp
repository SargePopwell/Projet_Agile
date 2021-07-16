<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ANPEP - Emargement</title>
</head>
<body>

<h1>ANPEP</h1>

<h2>Bienvenue, Utilisateur</h2>

<form action="dataIn" method="POST" id="emargement_AM">
<p>Je soussigné X atteste avoir été présent durant la matinée du Y. <p>
<input type="checkbox" name="AM" id="AM">
<input type="submit" value="Envoyer">
</form>

<form action="dataIn" method="POST" id="emargement_PM">
<p>Je soussigné X atteste avoir été présent durant l'après-midi du Y.<p>
<input type="checkbox" name="PM" id="PM">
<input type="submit" value="Envoyer">
</form>

</body>
</html>