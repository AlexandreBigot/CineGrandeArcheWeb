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
import fr.demos.formation.poe.cinegrandearche.metier.LignePanier;
import fr.demos.formation.poe.cinegrandearche.metier.Livre;
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
		
		
		System.out.println("on viens de faire un doGet sur panier.jsp");

		
		// j'identifie et je stocke la session actuelle
		HttpSession session = request.getSession();

//#######		 test méthode modif quantite ligne panier
		// ### LA ### METHODE ### FONCTIONNE ### BIEN ###
		
    	// je crée un panier au démarrage de la session
//    	Panier p = new Panier();
//
//    	Livre l1 = new Livre("livre_mat_00001", 42.00,
//				"La conduite de projet - 3e ed. - 126 règles pour piloter vos projets avec succès",
//				"images/img_article_livre_mat_00001.jpg", 10, "Thierry HOUGRON", "9782100724857", "DUNOD",
//				"Management");
//		l1.setDescription("Le chef de projet agit en patron d’une micro-entreprise. Acteur majeur de la réussite d’un projet, il doit à la fois anticiper (planifier, coordonner, accompagner, intégrer son projet dans l’environnement), gérer (les budgets, les équipes, les fournisseurs, les différentes parties prenantes au projet, les risques, la rentabilité des travaux), assurer (la qualité, le respect des délais et des objectifs, la satisfaction de son client et de son management), communiquer (plaider, négocier, convaincre). A travers 126 règles concrètes, chacun trouvera des repères solides pour construire et réussir son projet. Cette 3e édition mise à jour s’est enrichie de 25 nouvelles règles qui abordent les aspects humains de la gestion de projet (gestion des équipes interculturelles, gestion des compétences, motivation, agilité…).");
//
//		System.out.println("voici un Livre l1 : ");
//		System.out.println(l1);
//		
//		System.out.println("nombre de lignes dans mon panier");
//		System.out.println(p.getSizeContenuPanier());
//		
//		System.out.println("nombre d'articles dans mon panier");
//		System.out.println(p.getArticlesCumulesPanier());
//		
//		try {
//			p.ajouterUnArticle(l1, 2);
//		} catch (ExceptionQuantiteDemandeeSuperieureAuStock e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("j'ai mis 2 exemplaires de l1 dans mon panier = 1 ligne, 2 articles");
//		System.out.println("nombre de lignes dans mon panier");
//		System.out.println(p.getSizeContenuPanier());
//		
//		System.out.println("nombre d'articles dans mon panier");
//		System.out.println(p.getArticlesCumulesPanier());
//		
//		// appel méthode modifier quantite ligne panier
//		p.modifierQuantiteLignePanier(l1.getRef(), 5);
//
//		System.out.println("j'ai modifié la quantite de la ligne : 5");
//		System.out.println("nombre de lignes dans mon panier");
//		System.out.println(p.getSizeContenuPanier());
//		
//		System.out.println("nombre d'articles dans mon panier");
//		System.out.println(p.getArticlesCumulesPanier());
//		
// ### LA ### METHODE ### FONCTIONNE ### BIEN ###
		
//#######	
		
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

// ########### événements Article.jsp ###########
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
					// Je me connecte au panier de la session en le castant
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
			// je récupère la requête et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/Panier.jsp");
			rd.forward(request, response);
		} // si on clique sur voir le panier

	
		
		
// ########### événements Panier.jsp ###########

		
		
		
		// si on clique sur Modifier
		if (action != null && action.equals("Modifier")) {

			System.out.println("on a cliqué sur le bouton modifier");

			
			// je récupère la quantité mais c'est un string
			String stringQuantiteModifiee = request.getParameter("quantiteDansPanier");
			// transforme stringQuantitéAjoutee en intQuantiteAjoutee
			int intQuantiteModifiee = Integer.parseInt(stringQuantiteModifiee);

			System.out.println("intQuantiteModifiee : " + intQuantiteModifiee);
			
			// Je récupere la réf de l'article pour identifier la ligne à modifier
			String refArticleLigne = request.getParameter("refArticle");
		
			System.out.println("refArticleLigne : " + refArticleLigne);
			
			// Je me connecte au panier de la session en le castant
			Panier p = (Panier) session.getAttribute("panier");

			System.out.println("panier : " + p);
			System.out.println("quantite articles toutes lignes du panier : " + p.getArticlesCumulesPanier());

			
			// appel méthode modifier quantite ligne panier
			p.modifierQuantiteLignePanier(refArticleLigne, intQuantiteModifiee);
			System.out.println("j'essaie de modifier à " + intQuantiteModifiee);
			System.out.println("quantite articles toutes lignes du panier : " + p.getArticlesCumulesPanier());
			
			// je récupère la requête et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/Panier.jsp");
			rd.forward(request, response);
		} // si on clique sur voir le panier

		
		
		// si on clique sur supprimer
		if (action != null && action.equals("Supprimer")) {

			System.out.println("on a cliqué sur le bouton supprimer");

			// Je récupere la réf de l'article pour identifier la ligne à supprimer
			String refArticleLigne = request.getParameter("refArticle");
		
			System.out.println("refArticleLigne : " + refArticleLigne);
			
			// Je me connecte au panier de la session en le castant
			Panier p = (Panier) session.getAttribute("panier");

			System.out.println("panier : " + p);
			System.out.println("nombre lignes du panier : " + p.getSizeContenuPanier());
			
			// je supprime la ligne panier
			// il faut la référence de la ligne à supprimer en argument
			
			p.supprimerLignePanier(refArticleLigne);
			
			System.out.println("j'ai supprimé la ligne");
			System.out.println("nombre lignes du panier : " + p.getSizeContenuPanier());

			// je récupère la requête et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/Panier.jsp");
			rd.forward(request, response);
			
		} // si on clic bouton supprimer	

	
	
	
	} // do post
}
