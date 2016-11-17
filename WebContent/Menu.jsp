<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="menu">

	<div class="elementMenu">
		<c:if test="${!connecteAuCompte}">
			<!-- si non connecté à un compte -->
			<form id="formConnectionCompte" method="post" action="ControlerCompte">
				<label for="nom">Nom d'utilisateur</label> <br /> <input type="text"
					value="${param.nomUtilisateur}" name="nomUtilisateur" /> <br /> <label
					for="password">Mot de passe</label> <br /> <input type="password"
					value="${param.motDePasse}" name="motDePasse" /> <br />
				<br /> <input type=submit value="Connection" name="action" />
			</form>
		</c:if>
	</div>

	<div class="elementMenu">
		<c:if test="${connecteAuCompte}">
			<!-- si connecté -->
			<form id="formPageCompte" method="POST" action="ControlerCompte">
				<input type=submit name="compte" value="Voir le compte">
			</form>
		</c:if>
	</div>

	<div class="elementMenu">
		<form id="formCreerCompte" method="POST" action="/CreerCompte.jsp">
			<input type=submit name="creerCompte" value="Créer un Compte">
		</form>
	</div>

	<div class="elementMenu">
		<form id="formPagePanier" method="POST" action="/Panier.jsp">
			<input type=submit name="panier" value="Voir le panier">
		</form>
	</div>

</div>

