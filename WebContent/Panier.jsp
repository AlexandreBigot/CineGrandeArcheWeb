<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="styles.css" />
<title>Panier</title>
</head>
<body>
	<div class="divbody">
		<header id="headerPanier">
			<jsp:include page="/Menu.jsp"></jsp:include>
		</header>
		<br /> <br />

		<div class="titrePanier">
			<br />
			VOTRE PANIER
			</div>
		<br/>
		<c:forEach var="ligne" items="${panier.iterator()}">
			<div class="articlePanier">
				<br/>
				<div class="headerArticle">
					<c:if test="${empty ligne.article.materiel}">
						<div class="infosArticle">
							<ul>
								<li>${ligne.article.nom}</li>
								<li>Auteur : ${ligne.article.auteur}</li>
								<li>Prix HT : <fmt:formatNumber value="${ligne.article.prixHt}" minFractionDigits="2" /> €</li>
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
								<li>Prix HT : <fmt:formatNumber value="${ligne.article.prixHt}" minFractionDigits="2" /> €</li>
								<li>Quantité disponible : ${ligne.article.stock}</li>
							</ul>
						</div>
					</c:if>
					
					<div id="formModifierPanier" class="contenuHeaderArticle">
						<form action="ControlerPanier" method="post">
							<input type="hidden" value="${ligne.article.ref}" name="refArticle">
							<label for="nom">Quantité :</label>
							<input class="champsAjoutPanier" type="number" value="1" min="1" name="quantiteDansPanier"/>
							<input type=submit value="Modifier" name="action"/>
							<input type=submit value="Supprimer" name="action"/>
						</form>
					</div>
				</div>
				<br />

		</c:forEach>



	</div>
</body>
</html>