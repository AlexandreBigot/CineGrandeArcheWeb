<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Articles</title>
</head>
<body>
<header>
<jsp:include page="/Menu.jsp"></jsp:include>
</header>
<div>
<ul>
<li>Nom de l'article : ${livre1.nom}</li>
<li>Référence de l'article : ${livre1.ref}</li>
<c:if test="${not empty livre1.materiel}">
<li>Etat de l'article : ${livre1.materiel.etat}</li>
<li>Quantité disponible : ${livre1.stock}</li>
</c:if> 
<c:if test="${not empty immateriel}">
<li>Format de l'article : ${formatArticle}</li>
<li>Adresse de téléchargement de l'article : ${urlArticle}</li>
</c:if>
<li></li>
<li></li>
<li></li>
<li></li>
<li></li>
<li></li>
</ul>
</div>


</body>
</html>