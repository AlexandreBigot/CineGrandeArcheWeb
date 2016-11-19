<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="styles.css" />
<title>Articles</title>
</head>
<body>
	<div class="divbody">
		<header>
			<jsp:include page="/Menu.jsp"></jsp:include>
		</header>
		<br /> <br />

		<c:forEach var="article" items="${catalogue}">
			<div class="article">
				<br/>
				<div class="headerArticle">
					<div id="nomArticle" class="contenuHeaderArticle">${article.nom}</div>
					<div id="formAjoutPanier" class="contenuHeaderArticle">
						<form action="ControlerPanier" method="post">
						<input type="hidden" value="${article.ref}" name="refArticle">
						<label for="nom">Quantité :</label>
						<input class="champsAjoutPanier" type="number" value="" min="1" name="quantiteAjouteePanier"/>
						<input type=submit value="Ajouter au panier" name="action"/>
						</form>					
					</div>
				</div>
				<br/>
				<c:if test="${referenceArticlePanier == article.ref}">
				<div class="messageException">
					${ExceptionQuantiteDemandeeSuperieureAuStock}
				</div>
				<br/>
				</c:if>
				<div class="imageEtInfos">
					<div class="imageArticle">
						<img src="<c:url value='${article.urlImage}'/>" />
					</div>
					<!-- si article dématérialisé -->
					<c:if test="${empty article.materiel}">
						<div class="infosArticle">
							<ul>
								<li>Auteur : ${article.auteur}</li>
								<li>Prix HT : <fmt:formatNumber value="${article.prixHt}" minFractionDigits="2" /> €</li>
								<li>Format numérique : ${article.immateriel.format}</li>
							</ul>
						</div>
					</c:if>

					<!-- si article matériel -->
					<c:if test="${empty article.immateriel}">
						<div class="infosArticle">
							<ul>
								<li>Auteur : ${article.auteur}</li>
								<li>Etat : ${article.materiel.etat}</li>
								<li>Prix HT : <fmt:formatNumber value="${article.prixHt}" minFractionDigits="2" /> €</li>
								<li>Quantité disponible : ${article.stock}</li>
							</ul>
						</div>
					</c:if>

				</div>
				<br /> Description : <br />
				<div class="descriptionArticle">${article.description}</div>
				<br />
			</div>
		</c:forEach>


	</div>
</body>
</html>