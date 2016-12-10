<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="styles.css" />
<title>Création de compte</title>
</head>
<body>
	<div class="divbody">
		<header id="headerPanier">
			<jsp:include page="/Menu.jsp"></jsp:include>
		</header>
		<br /> <br />

		<div class="titrePanier">
			<br />
			Créez votre compte utilisateur
			<br/><br/>
			</div>
			<br/>
			
		<!-- si connecté à un compte -->	
		<c:if test="${connecteAuCompte}">
		<br/>
		<br/>
		<br/>
		<br/>
		<p>Vous avez déjà un compte utilisateur et vous êtes connecté</p>
		<br/>
		<br/>
		<br/>
		<br/>
		</c:if>
		
		<!-- si non connecté à un compte -->
		<c:if test="${!connecteAuCompte}">
			<div id="formCreationCOmpte" class="contenuHeaderArticle">
				<br/>
				<form action="ControlerCompte" method="post">
					<label for="nom">Nom :</label>
					<input type="text" name="nom"/>
					<br/><br/>
					<label for="prenom">Prénom :</label>
					<input type="text" name="prenom"/>
					<br/><br/>
					<label for="adresse">Adresse :</label>
					<input type="text" name="prenom"/>
					<br/><br/>
					<label for="adresse">Adresse livraison :</label>
					<input type="text" name="prenom"/>
					<br/><br/>
					<label for="email">Email :</label>
					<input type="email" name="email"/>
					<br/><br/>
					<label for="telephone">Téléphone :</label>
					<input type="tel" name="telephone"/>
					<br/><br/>
					<label for="password">Mot de passe :</label>
					<input type="password" name="password"/>
					<br/><br/>
					<label for="testPassword">Confirmez votre mot de passe :</label>
					<input type="password" name="testPassword"/>
					<!-- à changer pour ${creationPasswordTest} par défaut-->
					<c:if test="${!creationPasswordTest}">
						<p class="pPassword">Vous avez entré des mots de passe différents
						</p>
						</c:if>
					<br/><br/>


					<br/><br/>
					<br/><br/>
					<input type=submit value="Valider" name="action" />
				
					</form>
			</div>
		
		
		
		
		</c:if>
		
		
		
		<c:forEach var="ligne" items="${panier.iterator()}">
			<div class="articlePanier">
				<div class="headerArticle">
					<c:if test="${empty ligne.article.materiel}">
						<div class="infosArticle">
							<ul>
								<li>${ligne.article.nom}</li>
								<li>Auteur : ${ligne.article.auteur}</li>
								<li>Prix HT : <fmt:formatNumber
										value="${ligne.article.prixHt}" minFractionDigits="2" /> €
								</li>
								<li>Format numérique : ${ligne.article.immateriel.format}</li>
							</ul>
						</div>
					</c:if>

					<!-- si article matériel -->
					<c:if test="${empty ligne.article.immateriel}">
						<div class="infosArticle">
							<ul>
								<li>${ligne.article.nom}</li>
								<li>Auteur : ${ligne.article.auteur}</li>
								<li>Etat : ${ligne.article.materiel.etat}</li>
								<li>Prix HT : <fmt:formatNumber
										value="${ligne.article.prixHt}" minFractionDigits="2" /> €
								</li>
								<li>Quantité disponible : ${ligne.article.stock}</li>
							</ul>
						</div>
					</c:if>
					<div id="formModifierPanier" class="contenuHeaderArticle">
					<br/>
						<form action="ControlerPanier" method="post">
							<input type="hidden" value="${ligne.article.ref}" name="refArticle">
							<label for="nom">Quantité :</label>
							<input class="champsAjoutPanier" type="number" value="${ligne.getQuantite()}" min="1" name="quantiteDansPanier"/>
							<input type=submit value="Modifier" name="action" />
							<input type=submit value="Supprimer" name="action" />
						</form>
					</div>
				</div>
			</div>



		</c:forEach>



	</div>
</body>
</html>