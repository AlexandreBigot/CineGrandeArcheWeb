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

import fr.demos.formation.poe.cinegrandearche.exceptions.ExceptionQuantiteDemandeeSuperieureAuStock;
import fr.demos.formation.poe.cinegrandearche.metier.Article;
import fr.demos.formation.poe.cinegrandearche.metier.Panier;

@WebServlet("/ControlerPanier")
public class ControlerPanier extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControlerPanier() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// j'identifie et je stocke la session actuelle
		HttpSession session = request.getSession();

		// je récupère la requête et je renvoie vers la JSP
		RequestDispatcher rd = request.getRequestDispatcher("/Panier.jsp");
		rd.forward(request, response);

	} // do get

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// je dis à tomcat d'utiliser les accente et caractères spéciaux
		request.setCharacterEncoding("UTF-8");
		
		// j'identifie et je stocke la session actuelle
		HttpSession session = request.getSession();

		// je stocke le paramètre de requete (le name du bouton)
		String action = request.getParameter("action");

		// si on clique sur ajouter au panier
		// TODO : vérification nom utilisateur et mdp pour valider connection
		if (action != null && action.equals("Ajouter au panier")) {

			// ### je récupère la quantité mais c'est un string ###
			String stringQuantiteAjoutee = request.getParameter("quantiteAjouteePanier");
			// transforme stringQuantitéAjoutee en intQuantiteAjoutee
			int intQuantiteAjoutee = Integer.parseInt(stringQuantiteAjoutee);

			// ### je récupere la réf de l'article ###
			String refArticle = request.getParameter("refArticle");

			// ### je récupère l'arraylist du controlerArticle en le castant en arrayList
			ArrayList<Article> catalogue = (ArrayList<Article>) session.getAttribute("catalogue");

			// et je cherche l'article de cette réf###
			// pour chaque article du catalogue
			for (Article article : catalogue) {
				// là où réf de l'article est la même que celle récupérée dans le bouton
				if (article.getRef().equals(refArticle)) {
					// stock le panier de la session en le castant
					Panier p = (Panier) session.getAttribute("panier");
					// ### je déclenche la méthode panier ajouterArticle###
					try {
						p.ajouterUnArticle(article, intQuantiteAjoutee);

					} catch (ExceptionQuantiteDemandeeSuperieureAuStock e) {
						String message = e.getMessage();
						int quantiteStock = e.getQuantiteStock();
						// je mets les éléments du message dans une variable
						// unique pour la mettre en argument du setAttribute
						String messageExceptionQDSAS = message + quantiteStock;
						// je mets à disposition le message en EL
						session.setAttribute("ExceptionQuantiteDemandeeSuperieureAuStock", messageExceptionQDSAS);
						// je mets dans la session la référence de l'article mis
						// dans le panier
						session.setAttribute("referenceArticlePanier", refArticle);

						

					}
					// break pour arrêter de boucler car on aura qu'une seule
					// fois cet article (référence unique)
					break;
				} // if
			} // for article catalogue
			// je récupère la requête et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/Articles.jsp");
			rd.forward(request, response);
		} // if bouton ajouter au panier

		// si on clique sur Voir le panier j'affiche la page Panier
		if (action != null && action.equals("Voir le panier")) {
			System.out.println("j'ai cliqué sur voir le panier");
			// je récupère la requête et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/Panier.jsp");
			rd.forward(request, response);
		} // si on clique sur voir le panier

	} // do post
}
