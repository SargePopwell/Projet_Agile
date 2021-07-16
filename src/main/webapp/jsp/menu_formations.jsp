<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ANPEP - Menu</title>
</head>
<body>

<h1>ANPEP</h1>

<h2>Menu Formations</h2>


<c:forEach var='formation' items="${ formations }">
<div class="formation">
<h3>NOM formation</h3>
<p>Date de début / Date de fin</p>
<p>Description </p>
</div>
</c:forEach>


<form action="menuFormations" method="POST" id="add_formation">
<h2>Ajouter une formation</h2>

<div class="field">
<label for="intitule" id="intitule">Intitulé de la formation</label>
<input type="text" name="name" id="name">
</div>

<div class="field">
<label for="date_start" id="date_start">Début de la formation</label>
<input type="text" name="date_start" id="date_start">
</div>

<div class="field">
<label for="date_end" id="date_end">Fin de la formation</label>
<input type="text" name="date_end" id="date_end">
</div>

<div class="field">
<label for="description" id="description">Description</label>
<input type="text" name="description" id="description">
</div>

<input type="submit" value="Envoyer">
</form>

<form action="menuFormations" method="POST" id="add_user">
	<h2>Ajouter un utilisateur</h2>
	
	<div class="field">
	<label for="surname" id="surname">Nom</label>
	<input type="text" name="surname" id="surname">
	</div>
	
	<div class="field">
	<label for="name" id="name">Prénom</label>
	<input type="text" name="name" id="name">
	</div>
	
	<div class="field">
	<label for="email" id="email">Email</label>
	<input type="text" name="email" id="email">
	</div>

	<div class="inscription_field">
	<p>Type</p>
	<input type="radio" name="intern" id="intern" value="0">
	<label for="intern" id="intern">Stagiaire</label>
	<input type="radio" name="former" id="former" value="1">
	<label for="former" id="former">Formateur</label>
	</div>

	<input type="submit" value="Envoyer">
</form>

</body>
</html>