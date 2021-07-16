<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Emargement - Inscription</title>
</head>
<body>

<h1>Inscription</h1>

<form action='home?to=inscription' method="POST" id="inscription">

	<label for="surname" id="surname">Nom de Famille</label>
	<input type="text" name="surname" id="surname">
	
	<label for="name" id="name">Prénom</label>
	<input type="text" name="name" id="name">
	
	<label for="email" id="email">Email</label>
	<input type="text" name="email" id="email">
	
	<label for="phone" id="phone">Téléphone</label>
	<input type="text" name="phone" id="phone">
	
	<label for="adress" id="adress">Adresse</label>
	<input type="text" name="adress" id="adress">
	
	<label for="gender" id="gender">Genre</label>
	<input type="radio" name="homme" id="homme" value="0">
	<input type="radio" name="femme" id="femme" value="1">
	<input type="radio" name="autre" id="autre" value="2">
	
	<input type="submit" value="Envoyer">

</form>

</body>
</html>