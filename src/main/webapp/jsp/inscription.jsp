<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ANPEP - Inscription</title>
</head>
<body>

<h1>ANPEP</h1>

<h1>Inscription</h1>

<form action='inscription?to=inscription' method="POST" id="inscription">

	<div class="inscription_field">
	<label for="surname" id="surname">Nom de Famille</label>
	<input type="text" name="surname" id="surname">
	</div>
	
	<div class="inscription_field">
	<label for="name" id="name">Prénom</label>
	<input type="text" name="name" id="name">
	</div>
	
	<div class="inscription_field">
	<label for="email" id="email">Email</label>
	<input type="text" name="email" id="email">
	</div>
	
	<div class="inscription_field">
	<label for="phone" id="phone">Téléphone</label>
	<input type="text" name="phone" id="phone">
	</div>
	
	<div class="inscription_field">
	<label for="adress" id="adress">Adresse</label>
	<input type="text" name="adress" id="adress">
	</div>
	
	
	<div class="inscription_field">
	<p>Genre</p>
	<input type="radio" name="homme" id="homme" value="0">
	<label for="homme" id="homme">Homme</label>
	<input type="radio" name="femme" id="femme" value="1">
	<label for="femme" id="femme">Femme</label>
	<input type="radio" name="autre" id="autre" value="2">
	<label for="autre" id="autre">Autre</label>
	</div>
	
	<input type="submit" value="Envoyer">

</form>

</body>
</html>