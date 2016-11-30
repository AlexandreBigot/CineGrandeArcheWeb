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

		// ########## création de la BDD avant SQL ##########
		// // je simule ma BDD de livres
		// ArrayList<Article> catalogue = new ArrayList<Article>();
		//
		// Livre l1 = new Livre("livre_mat_00001", 42.00,
		// "La conduite de projet - 3e ed. - 126 règles pour piloter vos projets
		// avec succès",
		// "images/img_article_livre_mat_00001.jpg", 10, "Thierry HOUGRON",
		// "9782100724857", "DUNOD",
		// "Management");
		// l1.setDescription(
		// "Le chef de projet agit en patron d’une micro-entreprise. Acteur
		// majeur de la réussite d’un projet, il doit à la fois anticiper
		// (planifier, coordonner, accompagner, intégrer son projet dans
		// l’environnement), gérer (les budgets, les équipes, les fournisseurs,
		// les différentes parties prenantes au projet, les risques, la
		// rentabilité des travaux), assurer (la qualité, le respect des délais
		// et des objectifs, la satisfaction de son client et de son
		// management), communiquer (plaider, négocier, convaincre). A travers
		// 126 règles concrètes, chacun trouvera des repères solides pour
		// construire et réussir son projet. Cette 3e édition mise à jour s’est
		// enrichie de 25 nouvelles règles qui abordent les aspects humains de
		// la gestion de projet (gestion des équipes interculturelles, gestion
		// des compétences, motivation, agilité…).");
		// catalogue.add(l1);
		// session.setAttribute("livre1", l1);
		//
		// Livre l2 = new Livre("livre_demat_00001", 32.99,
		// "La conduite de projet - 3e ed. - 126 règles pour piloter vos projets
		// avec succès",
		// "images/img_article_livre_mat_00001.jpg", "PDF", "URL telechargement
		// l2", "Thierry HOUGRON",
		// "9782100729517", "DUNOD", "Management");
		// l2.setDescription(
		// "Le chef de projet agit en patron d’une micro-entreprise. Acteur
		// majeur de la réussite d’un projet, il doit à la fois anticiper
		// (planifier, coordonner, accompagner, intégrer son projet dans
		// l’environnement), gérer (les budgets, les équipes, les fournisseurs,
		// les différentes parties prenantes au projet, les risques, la
		// rentabilité des travaux), assurer (la qualité, le respect des délais
		// et des objectifs, la satisfaction de son client et de son
		// management), communiquer (plaider, négocier, convaincre). A travers
		// 126 règles concrètes, chacun trouvera des repères solides pour
		// construire et réussir son projet. Cette 3e édition mise à jour s’est
		// enrichie de 25 nouvelles règles qui abordent les aspects humains de
		// la gestion de projet (gestion des équipes interculturelles, gestion
		// des compétences, motivation, agilité…).");
		// catalogue.add(l2);
		//
		// Livre l3 = new Livre("livre_demat_00002", 32.99,
		// "La conduite de projet - 3e ed. - 126 règles pour piloter vos projets
		// avec succès",
		// "images/img_article_livre_mat_00001.jpg", "ePub", "URL telechargement
		// l3", "Thierry HOUGRON",
		// "9782100729524", "DUNOD", "Management");
		// l3.setDescription(
		// "Le chef de projet agit en patron d’une micro-entreprise. Acteur
		// majeur de la réussite d’un projet, il doit à la fois anticiper
		// (planifier, coordonner, accompagner, intégrer son projet dans
		// l’environnement), gérer (les budgets, les équipes, les fournisseurs,
		// les différentes parties prenantes au projet, les risques, la
		// rentabilité des travaux), assurer (la qualité, le respect des délais
		// et des objectifs, la satisfaction de son client et de son
		// management), communiquer (plaider, négocier, convaincre). A travers
		// 126 règles concrètes, chacun trouvera des repères solides pour
		// construire et réussir son projet. Cette 3e édition mise à jour s’est
		// enrichie de 25 nouvelles règles qui abordent les aspects humains de
		// la gestion de projet (gestion des équipes interculturelles, gestion
		// des compétences, motivation, agilité…).");
		// catalogue.add(l3);
		//
		// Livre l4 = new Livre("livre_mat_00002", 39.00,
		// "Android - Guide de développement d'applications Java pour
		// Smartphones et Tablettes (3e édition)",
		// "images/img_article_livre_mat_00002.jpg", 10, "Sylvain HEBUTERNE",
		// "9782409002533", "ENI",
		// "Multimédia et Graphisme");
		// l4.setDescription(
		// "Véritable guide d'apprentissage, ce livre accompagne le lecteur dans
		// le développement d'applications Android pour Smartphones et Tablettes
		// tactiles. Il s'adresse aux développeurs disposant d'un minimum de
		// connaissances sur la programmation orientée objet, le langage Java et
		// les environnements de développement intégrés type Eclipse ou Android
		// Studio et couvre toutes les versions d'Android jusqu'à la 6 incluse.
		// Le livre présente l'intégralité du processus de création
		// d'applications, de la mise en place de l'environnement de
		// développement Android Studio jusqu'à la publication de l'application,
		// et décrit une large sélection de fonctionnalités proposées par le
		// système Android.");
		// catalogue.add(l4);
		//
		// Livre l5 = new Livre("livre_mat_00003", 33.00,
		// "Scrum - 4e éd.- Le guide pratique de la méthode agile la plus
		// populaire",
		// "images/img_article_livre_mat_00003.jpg", 10, "Claude AUBRY",
		// "9782100738748", "DUNOD",
		// "Ingénierie informatique - Génie logiciel");
		// l5.setDescription(
		// "Cet ouvrage s'adresse à tous ceux qui souhaitent s'initier à Scrum.
		// Ceux qui possèdent déjà une expérience 'agile' y trouveront de quoi
		// revoir et approfondir leurs connaissances. Les rôles emblématiques de
		// ScrumMaster et de Product Owner sont expliqués en détail, ainsi que
		// la façon dont l'équipe s'organise pour produire une version à chaque
		// sprint, à partir du backlog et en suivant des pratiques
		// collaboratives. Ces notions, qui ont fait le succès de Scrum, sont
		// toujours le coeur de cette quatrième édition qui a été repensée et
		// remaniée en profondeur pour apporter au lecteur l'esprit de Scrum,
		// au-delà du simple mode d'emploi. L'ouvrage montre comment Scrum
		// continue à se répandre, faisant surgir de nouveaux défis, poussant à
		// de nouvelles réponses, contribuant ainsi à créer un écosystème
		// agile.");
		// catalogue.add(l5);
		//
		// Livre l6 = new Livre("livre_demat_00003", 23.99,
		// "Scrum - 4e éd.- Le guide pratique de la méthode agile la plus
		// populaire",
		// "images/img_article_livre_mat_00003.jpg", "Kindle", "URL
		// telechargement l6", "Claude AUBRY",
		// "B0168E6THE", "DUNOD", "Ingénierie informatique - Génie logiciel");
		// l6.setDescription(
		// "Cet ouvrage s'adresse à tous ceux qui souhaitent s'initier à Scrum.
		// Ceux qui possèdent déjà une expérience 'agile' y trouveront de quoi
		// revoir et approfondir leurs connaissances. Les rôles emblématiques de
		// ScrumMaster et de Product Owner sont expliqués en détail, ainsi que
		// la façon dont l'équipe s'organise pour produire une version à chaque
		// sprint, à partir du backlog et en suivant des pratiques
		// collaboratives. Ces notions, qui ont fait le succès de Scrum, sont
		// toujours le coeur de cette quatrième édition qui a été repensée et
		// remaniée en profondeur pour apporter au lecteur l'esprit de Scrum,
		// au-delà du simple mode d'emploi. L'ouvrage montre comment Scrum
		// continue à se répandre, faisant surgir de nouveaux défis, poussant à
		// de nouvelles réponses, contribuant ainsi à créer un écosystème
		// agile.");
		// catalogue.add(l6);
		//
		// session.setAttribute("catalogue", catalogue);
		// ########## fin création de la BDD avant SQL ##########

		// je récupère la requête et je renvoie vers la JSP
		RequestDispatcher rd = request.getRequestDispatcher("/Articles.jsp");
		rd.forward(request, response);

		// je renseigne la nouvelle jsp courante après chaque rd.forward
		String jspCourante = "/Articles.jsp";
		session.setAttribute("jspCourante", jspCourante);

	}// do get

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// je dis à tomcat d'utiliser les accente et caractères spéciaux
		request.setCharacterEncoding("UTF-8");

		// j'identifie et je stocke la session actuelle
		HttpSession session = request.getSession();

		// je stocke le paramètre de requete (le name du bouton)
		String action = request.getParameter("action");

		// si on clique sur Voir les articles j'affiche la page articles
		// TODO : vérification nom utilisateur et mdp pour valider connection
		if (action != null && action.equals("Voir les articles")) {

			// je récupère la requête et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/Articles.jsp");
			rd.forward(request, response);

			// je renseigne la nouvelle jsp courante après chaque rd.forward (la
			// même que le forward)
			String jspCourante = "/Articles.jsp";
			session.setAttribute("jspCourante", jspCourante);

		} // if bouton Voir les articles

		// si bouton rechercher
		if (action != null && action.equals("Rechercher")) {
			
			// recherche va renseigner le "string critere" en argument de la méthode select
			String recherche = request.getParameter("recherche").toUpperCase();
			
			// chercher en BDD les correspondance
			// itérer et mettre les résultats dans catalogue dans session
			// après une recherche on ne remet pas le catalogue à son état d'avant la recherche
			// il faudra faire une nouvelle recherche ou appeler select sans argument
			try {
				ArticleDAOMySql articleDAOMySql = new ArticleDAOMySql();
				ArrayList<Article> catalogue = (ArrayList<Article>) articleDAOMySql.select(recherche);
				session.setAttribute("catalogue", catalogue);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			// je récupère la requête et je renvoie vers la JSP courante
			String uriCible = (String)session.getAttribute("jspCourante");
			RequestDispatcher rd = request.getRequestDispatcher(uriCible);
			rd.forward(request, response);
			// pas besoin de changer le jspCourante car c'est la même
		
		}// if Rechercher

		
		
	}// do post

} // class
