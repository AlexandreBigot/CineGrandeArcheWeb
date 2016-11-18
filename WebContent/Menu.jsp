<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="menu">

	<div class="elementMenu">
		<c:if test="${!connecteAuCompte}">
			<!-- si non connecté à un compte -->
			<form id="formConnectionCompte" method="post" action="ControlerCompte">
				<label for="nom">Nom d'utilisateur</label> <br /> <input
					type="text" value="${param.nomUtilisateur}" name="nomUtilisateur" />
				<br /> <label for="password">Mot de passe</label> <br /> <input
					type="password" value="${param.motDePasse}" name="motDePasse" /> <br />
				<br /> <input type=submit value="Connection" name="action" />
			</form>
		</c:if>
	</div>

	<div class="elementMenu">
		<c:if test="${connecteAuCompte}">
			<!-- si connecté -->
			<form id="formPageCompte" method="POST" action="ControlerCompte">
				<input type=submit value="Voir le compte" name="action">
			</form>
		</c:if>
	</div>
	<div class="elementMenu">
		<c:if test="${connecteAuCompte}">
			<!-- si connecté -->
			<form id="formPageCompte" method="POST" action="ControlerCompte">
				<input type=submit value="Se déconnecter" name="action">
			</form>
		</c:if>
	</div>

	<div class="elementMenu">
		<c:if test="${!connecteAuCompte}">
			<!-- si non connecté à un compte -->
			<form id="formCreerCompte" method="POST" action="ControlerCompte">
				<input type=submit value="Créer un compte" name="action">
			</form>
		</c:if>
	</div>

	<div class="elementMenu">
		<form id="formPageArticle" method="POST" action="ControlerArticles">
			<input type=submit value="Voir les articles" name="action">
		</form>
	</div>

	<div class="elementMenu">
		<div>
			<form id="formPagePanier" method="POST" action="ControlerPanier">
				<input type=submit value="Voir le panier" name="action">
			</form>
		</div>
				<br/>
		<div>
		Le panier contient ${panier.getArticlesCumulesPanier()} articles
		<br/>
		Montant total TTC : <fmt:formatNumber value="${panier.getPrixTotal()}" minFractionDigits="2" /> €
		</div>
	</div>

</div>

