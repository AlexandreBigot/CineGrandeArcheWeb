<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<div id="menu">
<c:if test="${connect==true}"><!-- si non connecté à un compte -->
<form id="formConnection" method="post" action="ControlerCompte">
<label for="nom">Nom d'utilisateur :</label>
<input type="text" value="${param.nomUtilisateur}" name="nomUtilisateur"/>
<label for="password">Mot de passe :</label>
<input type="password" value="${param.motDePasse}" name="motDePasse"/>
<input type=submit value="connection" name="action"/>
</form>
</c:if>
<c:if test=""><!-- si connecté -->
<form id="formPageCompte" method="POST" action="/Compte.jsp">
<input type=submit name="compte" value="Voir le compte" >
</form>
</c:if>
<form id="formPagePanier" method="POST" action="/Panier.jsp">
<input type=submit name="panier" value="Voir le panier" >
</form>
</div>
    
    