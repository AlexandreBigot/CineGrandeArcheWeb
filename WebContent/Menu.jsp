<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="menuVertical">
	<div class="menuHorizontal">

			<c:if test="${empty compteSession}">
		<div class="elementMenu">
				<!-- si non connecté à un compte -->
				<form id="formConnectionCompte" method="post"
					action="ControlerCompte">
					<label for="nom">Email</label> <br /> <input
						type="email" value="${param.email}" name="email" />alex_bigot@hotmail.com
					<br /> <label for="password">Mot de passe</label> <br /> <input
						type="password" value="${param.password}" name="password" />password
					<br />
					${messageExceptionPasswordFail}
					<br />
					<input type=submit value="Connection" name="action" />
				</form>
			</div>		
			</c:if>
					<c:if test="${not empty compteSession}">
		
		<div class="elementMenu">
				<!-- si connecté -->
				<form id="formPageCompte" method="POST" action="ControlerCompte">
					<input type=submit value="Voir le compte" name="action">
				</form>
		</div>
			</c:if>
			
				<c:if test="${not empty compteSession}">
		<div class="elementMenu">
				<!-- si connecté -->
				<form id="formPageCompte" method="POST" action="ControlerCompte">
					<input type=submit value="Se déconnecter" name="action">
				</form>
		</div>
					</c:if>
		
			<c:if test="${empty compteSession}">
		<div class="elementMenu">
				<!-- si non connecté à un compte -->
				<form id="formCreerCompte" method="POST" action="ControlerCompte">
					<input type=submit value="Créer un compte" name="action">
				</form>
						</div>
				
			</c:if>

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
		</div>
		
		<div class="elementMenu">
			<div>
				Le panier contient ${panier.getArticlesCumulesPanier()} articles <br />
				Montant total TTC :
				<fmt:formatNumber value="${panier.getPrixTotal()}"
					minFractionDigits="2" />€
			</div>
		</div>
	</div>

	<br>
	<br>
	<div class="elementMenu" id="rechercheArticleMenu">
		<form id="formRecherche" method="post" action="ControlerArticles">
			<input type=submit value="Rechercher" name="action" />
			<input type="text" value="${param.recherche}" name="recherche" />
			</form>
	</div>
</div>

