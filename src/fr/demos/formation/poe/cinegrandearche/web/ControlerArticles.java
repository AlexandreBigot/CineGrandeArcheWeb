package fr.demos.formation.poe.cinegrandearche.web;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.demos.formation.poe.cinegrandearche.data.ArticleDAOMySql;
import fr.demos.formation.poe.cinegrandearche.metier.Article;
import fr.demos.formation.poe.cinegrandearche.metier.Livre;

@WebServlet("/ControlerArticles")
public class ControlerArticles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControlerArticles() {
		super();
		// TODO Auto-generated constructor stub
	} // constructeur

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// j'identifie et je stocke la session actuelle
		HttpSession session = request.getSession();

		// ########## cr�ation de la BDD avant SQL ##########
		// // je simule ma BDD de livres
		// ArrayList<Article> catalogue = new ArrayList<Article>();
		//
		// Livre l1 = new Livre("livre_mat_00001", 42.00,
		// "La conduite de projet - 3e ed. - 126 r�gles pour piloter vos projets
		// avec succ�s",
		// "images/img_article_livre_mat_00001.jpg", 10, "Thierry HOUGRON",
		// "9782100724857", "DUNOD",
		// "Management");
		// l1.setDescription(
		// "Le chef de projet agit en patron d�une micro-entreprise. Acteur
		// majeur de la r�ussite d�un projet, il doit � la fois anticiper
		// (planifier, coordonner, accompagner, int�grer son projet dans
		// l�environnement), g�rer (les budgets, les �quipes, les fournisseurs,
		// les diff�rentes parties prenantes au projet, les risques, la
		// rentabilit� des travaux), assurer (la qualit�, le respect des d�lais
		// et des objectifs, la satisfaction de son client et de son
		// management), communiquer (plaider, n�gocier, convaincre). A travers
		// 126 r�gles concr�tes, chacun trouvera des rep�res solides pour
		// construire et r�ussir son projet. Cette 3e �dition mise � jour s�est
		// enrichie de 25 nouvelles r�gles qui abordent les aspects humains de
		// la gestion de projet (gestion des �quipes interculturelles, gestion
		// des comp�tences, motivation, agilit�).");
		// catalogue.add(l1);
		// session.setAttribute("livre1", l1);
		//
		// Livre l2 = new Livre("livre_demat_00001", 32.99,
		// "La conduite de projet - 3e ed. - 126 r�gles pour piloter vos projets
		// avec succ�s",
		// "images/img_article_livre_mat_00001.jpg", "PDF", "URL telechargement
		// l2", "Thierry HOUGRON",
		// "9782100729517", "DUNOD", "Management");
		// l2.setDescription(
		// "Le chef de projet agit en patron d�une micro-entreprise. Acteur
		// majeur de la r�ussite d�un projet, il doit � la fois anticiper
		// (planifier, coordonner, accompagner, int�grer son projet dans
		// l�environnement), g�rer (les budgets, les �quipes, les fournisseurs,
		// les diff�rentes parties prenantes au projet, les risques, la
		// rentabilit� des travaux), assurer (la qualit�, le respect des d�lais
		// et des objectifs, la satisfaction de son client et de son
		// management), communiquer (plaider, n�gocier, convaincre). A travers
		// 126 r�gles concr�tes, chacun trouvera des rep�res solides pour
		// construire et r�ussir son projet. Cette 3e �dition mise � jour s�est
		// enrichie de 25 nouvelles r�gles qui abordent les aspects humains de
		// la gestion de projet (gestion des �quipes interculturelles, gestion
		// des comp�tences, motivation, agilit�).");
		// catalogue.add(l2);
		//
		// Livre l3 = new Livre("livre_demat_00002", 32.99,
		// "La conduite de projet - 3e ed. - 126 r�gles pour piloter vos projets
		// avec succ�s",
		// "images/img_article_livre_mat_00001.jpg", "ePub", "URL telechargement
		// l3", "Thierry HOUGRON",
		// "9782100729524", "DUNOD", "Management");
		// l3.setDescription(
		// "Le chef de projet agit en patron d�une micro-entreprise. Acteur
		// majeur de la r�ussite d�un projet, il doit � la fois anticiper
		// (planifier, coordonner, accompagner, int�grer son projet dans
		// l�environnement), g�rer (les budgets, les �quipes, les fournisseurs,
		// les diff�rentes parties prenantes au projet, les risques, la
		// rentabilit� des travaux), assurer (la qualit�, le respect des d�lais
		// et des objectifs, la satisfaction de son client et de son
		// management), communiquer (plaider, n�gocier, convaincre). A travers
		// 126 r�gles concr�tes, chacun trouvera des rep�res solides pour
		// construire et r�ussir son projet. Cette 3e �dition mise � jour s�est
		// enrichie de 25 nouvelles r�gles qui abordent les aspects humains de
		// la gestion de projet (gestion des �quipes interculturelles, gestion
		// des comp�tences, motivation, agilit�).");
		// catalogue.add(l3);
		//
		// Livre l4 = new Livre("livre_mat_00002", 39.00,
		// "Android - Guide de d�veloppement d'applications Java pour
		// Smartphones et Tablettes (3e �dition)",
		// "images/img_article_livre_mat_00002.jpg", 10, "Sylvain HEBUTERNE",
		// "9782409002533", "ENI",
		// "Multim�dia et Graphisme");
		// l4.setDescription(
		// "V�ritable guide d'apprentissage, ce livre accompagne le lecteur dans
		// le d�veloppement d'applications Android pour Smartphones et Tablettes
		// tactiles. Il s'adresse aux d�veloppeurs disposant d'un minimum de
		// connaissances sur la programmation orient�e objet, le langage Java et
		// les environnements de d�veloppement int�gr�s type Eclipse ou Android
		// Studio et couvre toutes les versions d'Android jusqu'� la 6 incluse.
		// Le livre pr�sente l'int�gralit� du processus de cr�ation
		// d'applications, de la mise en place de l'environnement de
		// d�veloppement Android Studio jusqu'� la publication de l'application,
		// et d�crit une large s�lection de fonctionnalit�s propos�es par le
		// syst�me Android.");
		// catalogue.add(l4);
		//
		// Livre l5 = new Livre("livre_mat_00003", 33.00,
		// "Scrum - 4e �d.- Le guide pratique de la m�thode agile la plus
		// populaire",
		// "images/img_article_livre_mat_00003.jpg", 10, "Claude AUBRY",
		// "9782100738748", "DUNOD",
		// "Ing�nierie informatique - G�nie logiciel");
		// l5.setDescription(
		// "Cet ouvrage s'adresse � tous ceux qui souhaitent s'initier � Scrum.
		// Ceux qui poss�dent d�j� une exp�rience 'agile' y trouveront de quoi
		// revoir et approfondir leurs connaissances. Les r�les embl�matiques de
		// ScrumMaster et de Product Owner sont expliqu�s en d�tail, ainsi que
		// la fa�on dont l'�quipe s'organise pour produire une version � chaque
		// sprint, � partir du backlog et en suivant des pratiques
		// collaboratives. Ces notions, qui ont fait le succ�s de Scrum, sont
		// toujours le coeur de cette quatri�me �dition qui a �t� repens�e et
		// remani�e en profondeur pour apporter au lecteur l'esprit de Scrum,
		// au-del� du simple mode d'emploi. L'ouvrage montre comment Scrum
		// continue � se r�pandre, faisant surgir de nouveaux d�fis, poussant �
		// de nouvelles r�ponses, contribuant ainsi � cr�er un �cosyst�me
		// agile.");
		// catalogue.add(l5);
		//
		// Livre l6 = new Livre("livre_demat_00003", 23.99,
		// "Scrum - 4e �d.- Le guide pratique de la m�thode agile la plus
		// populaire",
		// "images/img_article_livre_mat_00003.jpg", "Kindle", "URL
		// telechargement l6", "Claude AUBRY",
		// "B0168E6THE", "DUNOD", "Ing�nierie informatique - G�nie logiciel");
		// l6.setDescription(
		// "Cet ouvrage s'adresse � tous ceux qui souhaitent s'initier � Scrum.
		// Ceux qui poss�dent d�j� une exp�rience 'agile' y trouveront de quoi
		// revoir et approfondir leurs connaissances. Les r�les embl�matiques de
		// ScrumMaster et de Product Owner sont expliqu�s en d�tail, ainsi que
		// la fa�on dont l'�quipe s'organise pour produire une version � chaque
		// sprint, � partir du backlog et en suivant des pratiques
		// collaboratives. Ces notions, qui ont fait le succ�s de Scrum, sont
		// toujours le coeur de cette quatri�me �dition qui a �t� repens�e et
		// remani�e en profondeur pour apporter au lecteur l'esprit de Scrum,
		// au-del� du simple mode d'emploi. L'ouvrage montre comment Scrum
		// continue � se r�pandre, faisant surgir de nouveaux d�fis, poussant �
		// de nouvelles r�ponses, contribuant ainsi � cr�er un �cosyst�me
		// agile.");
		// catalogue.add(l6);
		//
		// session.setAttribute("catalogue", catalogue);
		// ########## fin cr�ation de la BDD avant SQL ##########

		// je r�cup�re la requ�te et je renvoie vers la JSP
		RequestDispatcher rd = request.getRequestDispatcher("/Articles.jsp");
		rd.forward(request, response);

		// je renseigne la nouvelle jsp courante apr�s chaque rd.forward
		String jspCourante = "/Articles.jsp";
		session.setAttribute("jspCourante", jspCourante);

	}// do get

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// je dis � tomcat d'utiliser les accente et caract�res sp�ciaux
		request.setCharacterEncoding("UTF-8");

		// j'identifie et je stocke la session actuelle
		HttpSession session = request.getSession();

		// je stocke le param�tre de requete (le name du bouton)
		String action = request.getParameter("action");

		// si on clique sur Voir les articles j'affiche la page articles
		// TODO : v�rification nom utilisateur et mdp pour valider connection
		if (action != null && action.equals("Voir les articles")) {

			// je r�cup�re la requ�te et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/Articles.jsp");
			rd.forward(request, response);

			// je renseigne la nouvelle jsp courante apr�s chaque rd.forward (la
			// m�me que le forward)
			String jspCourante = "/Articles.jsp";
			session.setAttribute("jspCourante", jspCourante);

		} // if bouton Voir les articles

		// si bouton rechercher
		if (action != null && action.equals("Rechercher")) {
			
			// recherche va renseigner le "string critere" en argument de la m�thode select
			String recherche = request.getParameter("recherche").toUpperCase();
			
			// chercher en BDD les correspondance
			// it�rer et mettre les r�sultats dans catalogue dans session
			// apr�s une recherche on ne remet pas le catalogue � son �tat d'avant la recherche
			// il faudra faire une nouvelle recherche ou appeler select sans argument
			try {
				ArticleDAOMySql articleDAOMySql = new ArticleDAOMySql();
				ArrayList<Article> catalogue = (ArrayList<Article>) articleDAOMySql.select(recherche);
				session.setAttribute("catalogue", catalogue);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			// je r�cup�re la requ�te et je renvoie vers la JSP courante
			String uriCible = (String)session.getAttribute("jspCourante");
			RequestDispatcher rd = request.getRequestDispatcher(uriCible);
			rd.forward(request, response);
			// pas besoin de changer le jspCourante car c'est la m�me
		
		}// if Rechercher

		
		
	}// do post

} // class
