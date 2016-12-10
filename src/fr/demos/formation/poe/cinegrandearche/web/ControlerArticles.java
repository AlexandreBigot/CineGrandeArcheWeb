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
