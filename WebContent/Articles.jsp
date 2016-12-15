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

		<c:if test="${empty catalogue}">
			<div class="article">
				<br/><br/><br/>
				<div class="headerArticle">
				<p class="pPanier">Aucun résultat trouvé pour la recherche "${critereRecherche}"</p>
				</div>
				<br/><br/><br/>
			</div>
		</c:if>

		<c:forEach var="article" items="${catalogue}">
			<div class="article">
				<br/>
				<div class="headerArticle">
					<div id="nomArticle" class="contenuHeaderArticle">${article.nom}</div>
					<div id="formAjoutPanier" class="contenuHeaderArticle">
						<form action="ControlerPanier" method="post">
						<input type="hidden" value="${article.ref}" name="refArticle">
						<label for="nom">Quantité :</label>
						<input class="champsAjoutPanier" type="number" value="1" min="1" name="quantiteAjouteePanier"/>
						<input type=submit value="Ajouter au panier" name="action"/>
						</form>					
					</div>
				</div>
				<br/>
				<c:if test="${referenceArticlePanier == article.ref}">
				<span class="messageException">
				<b>${ExceptionQuantiteDemandeeSuperieureAuStock}</b>
				</span>
				<br/>
				</c:if>
				<div class="imageEtInfos">
					<div class="imageArticle">
						<img src="<c:url value='${article.urlImage}'/>" />
					</div>
					<!-- si article dématérialisé -->
					<c:if test="${empty article.materiel}">
						<c:if test="${article.typeArticle eq 'LIVRE'}">
							<div class="infosArticle">
								<ul>
									<li>Auteur : ${article.auteur}</li>
									<li>Editeur : ${article.editeur}</li>
									<li>Prix HT : <fmt:formatNumber value="${article.prixHt}" minFractionDigits="2" /> €</li>
									<li>Format numérique : ${article.immateriel.format}</li>
									<li>Date de parution : ${article.date}</li>
									<li>Genre : ${article.genre}</li>
									<li>ASIN : ${article.isbn}</li>
									<li>Référence : ${article.ref}</li>
								</ul>
							</div>
						</c:if>
						<c:if test="${article.typeArticle eq 'ARTICLE_DIVERS'}">
							<div class="infosArticle">
								<ul>
									<li>Article : ${article.typeArticleDivers}</li>
									<li>Caractéristiques : ${article.caracteristiques}</li>
									<li>Prix HT : <fmt:formatNumber value="${article.prixHt}" minFractionDigits="2" /> €</li>
									<li>Format numérique : ${article.immateriel.format}</li>
									<li>Référence : ${article.ref}</li>
								</ul>
							</div>
						</c:if>
						
					</c:if>

					<!-- si article matériel -->
					<c:if test="${empty article.immateriel}">
						<c:if test="${article.typeArticle eq 'LIVRE'}">
							<div class="infosArticle">
								<ul>
									<li>Auteur : ${article.auteur}</li>
									<li>Editeur : ${article.editeur}</li>
									<li>Etat : ${article.materiel.etat}</li>
									<li>Prix HT : <fmt:formatNumber value="${article.prixHt}" minFractionDigits="2" /> €</li>
									<li>Quantité disponible : ${article.stock}</li>
									<li>Date de parution : ${article.date}</li>
									<li>Genre : ${article.genre}</li>
									<li>ISBN : ${article.isbn}</li>
									<li>Référence : ${article.ref}</li>
								</ul>
							</div>
						</c:if>
						<c:if test="${article.typeArticle eq 'ARTICLE_DIVERS'}">
							<div class="infosArticle">
								<ul>
									<li>Article : ${article.typeArticleDivers}</li>
									<li>Caractéristiques : ${article.caracteristiques}</li>
									<li>Etat : ${article.materiel.etat}</li>
									<li>Prix HT : <fmt:formatNumber value="${article.prixHt}" minFractionDigits="2" /> €</li>
									<li>Quantité disponible : ${article.stock}</li>
									<li>Référence : ${article.ref}</li>
								</ul>
							</div>
						
						</c:if>		
					</c:if>

				</div>
				<br/><!-- Description : <br />-->
				<div class="descriptionArticle">${article.description}</div>
				<br/>
			</div>
		</c:forEach>


	</div>
</body>
</html>