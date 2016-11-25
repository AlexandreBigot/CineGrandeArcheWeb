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

//#######		 test m�thode modif quantite ligne panier
		// ### LA ### METHODE ### FONCTIONNE ### BIEN ###
		
    	// je cr�e un panier au d�marrage de la session
//    	Panier p = new Panier();
//
//    	Livre l1 = new Livre("livre_mat_00001", 42.00,
//				"La conduite de projet - 3e ed. - 126 r�gles pour piloter vos projets avec succ�s",
//				"images/img_article_livre_mat_00001.jpg", 10, "Thierry HOUGRON", "9782100724857", "DUNOD",
//				"Management");
//		l1.setDescription("Le chef de projet agit en patron d�une micro-entreprise. Acteur majeur de la r�ussite d�un projet, il doit � la fois anticiper (planifier, coordonner, accompagner, int�grer son projet dans l�environnement), g�rer (les budgets, les �quipes, les fournisseurs, les diff�rentes parties prenantes au projet, les risques, la rentabilit� des travaux), assurer (la qualit�, le respect des d�lais et des objectifs, la satisfaction de son client et de son management), communiquer (plaider, n�gocier, convaincre). A travers 126 r�gles concr�tes, chacun trouvera des rep�res solides pour construire et r�ussir son projet. Cette 3e �dition mise � jour s�est enrichie de 25 nouvelles r�gles qui abordent les aspects humains de la gestion de projet (gestion des �quipes interculturelles, gestion des comp�tences, motivation, agilit�).");
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
//		// appel m�thode modifier quantite ligne panier
//		p.modifierQuantiteLignePanier(l1.getRef(), 5);
//
//		System.out.println("j'ai modifi� la quantite de la ligne : 5");
//		System.out.println("nombre de lignes dans mon panier");
//		System.out.println(p.getSizeContenuPanier());
//		
//		System.out.println("nombre d'articles dans mon panier");
//		System.out.println(p.getArticlesCumulesPanier());
//		
// ### LA ### METHODE ### FONCTIONNE ### BIEN ###
		
//#######	
		
		// je r�cup�re la requ�te et je renvoie vers la JSP
		RequestDispatcher rd = request.getRequestDispatcher("/Panier.jsp");
		rd.forward(request, response);

	} // do get

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// je dis � tomcat d'utiliser les accente et caract�res sp�ciaux
		request.setCharacterEncoding("UTF-8");
		
		// j'identifie et je stocke la session actuelle
		HttpSession session = request.getSession();

		// je stocke le param�tre de requete (le name du bouton)
		String action = request.getParameter("action");

// ########### �v�nements Article.jsp ###########
		// si on clique sur ajouter au panier
		// TODO : v�rification nom utilisateur et mdp pour valider connection
		if (action != null && action.equals("Ajouter au panier")) {

			// ### je r�cup�re la quantit� mais c'est un string ###
			String stringQuantiteAjoutee = request.getParameter("quantiteAjouteePanier");
			// transforme stringQuantit�Ajoutee en intQuantiteAjoutee
			int intQuantiteAjoutee = Integer.parseInt(stringQuantiteAjoutee);

			// ### je r�cupere la r�f de l'article ###
			String refArticle = request.getParameter("refArticle");

			// ### je r�cup�re l'arraylist du controlerArticle en le castant en arrayList
			ArrayList<Article> catalogue = (ArrayList<Article>) session.getAttribute("catalogue");

			// et je cherche l'article de cette r�f###
			// pour chaque article du catalogue
			for (Article article : catalogue) {
				// l� o� r�f de l'article est la m�me que celle r�cup�r�e dans le bouton
				if (article.getRef().equals(refArticle)) {
					// Je me connecte au panier de la session en le castant
					Panier p = (Panier) session.getAttribute("panier");
					// ### je d�clenche la m�thode panier ajouterArticle###
					try {
						p.ajouterUnArticle(article, intQuantiteAjoutee);

					} catch (ExceptionQuantiteDemandeeSuperieureAuStock e) {
						String message = e.getMessage();
						int quantiteStock = e.getQuantiteStock();
						// je mets les �l�ments du message dans une variable
						// unique pour la mettre en argument du setAttribute
						String messageExceptionQDSAS = message + quantiteStock;
						// je mets � disposition le message en EL
						session.setAttribute("ExceptionQuantiteDemandeeSuperieureAuStock", messageExceptionQDSAS);
						// je mets dans la session la r�f�rence de l'article mis
						// dans le panier
						session.setAttribute("referenceArticlePanier", refArticle);
					}
					// break pour arr�ter de boucler car on aura qu'une seule
					// fois cet article (r�f�rence unique)
					break;
				} // if
			} // for article catalogue
			// je r�cup�re la requ�te et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/Articles.jsp");
			rd.forward(request, response);
		} // if bouton ajouter au panier

		
		// si on clique sur Voir le panier j'affiche la page Panier
		if (action != null && action.equals("Voir le panier")) {
			// je r�cup�re la requ�te et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/Panier.jsp");
			rd.forward(request, response);
		} // si on clique sur voir le panier

	
		
		
// ########### �v�nements Panier.jsp ###########

		
		
		
		// si on clique sur Modifier
		if (action != null && action.equals("Modifier")) {

			System.out.println("on a cliqu� sur le bouton modifier");

			
			// je r�cup�re la quantit� mais c'est un string
			String stringQuantiteModifiee = request.getParameter("quantiteDansPanier");
			// transforme stringQuantit�Ajoutee en intQuantiteAjoutee
			int intQuantiteModifiee = Integer.parseInt(stringQuantiteModifiee);

			System.out.println("intQuantiteModifiee : " + intQuantiteModifiee);
			
			// Je r�cupere la r�f de l'article pour identifier la ligne � modifier
			String refArticleLigne = request.getParameter("refArticle");
		
			System.out.println("refArticleLigne : " + refArticleLigne);
			
			// Je me connecte au panier de la session en le castant
			Panier p = (Panier) session.getAttribute("panier");

			System.out.println("panier : " + p);
			System.out.println("quantite articles toutes lignes du panier : " + p.getArticlesCumulesPanier());

			
			// appel m�thode modifier quantite ligne panier
			p.modifierQuantiteLignePanier(refArticleLigne, intQuantiteModifiee);
			System.out.println("j'essaie de modifier � " + intQuantiteModifiee);
			System.out.println("quantite articles toutes lignes du panier : " + p.getArticlesCumulesPanier());
			
			// je r�cup�re la requ�te et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/Panier.jsp");
			rd.forward(request, response);
		} // si on clique sur voir le panier

		
		
		// si on clique sur supprimer
		if (action != null && action.equals("Supprimer")) {

			System.out.println("on a cliqu� sur le bouton supprimer");

			// Je r�cupere la r�f de l'article pour identifier la ligne � supprimer
			String refArticleLigne = request.getParameter("refArticle");
		
			System.out.println("refArticleLigne : " + refArticleLigne);
			
			// Je me connecte au panier de la session en le castant
			Panier p = (Panier) session.getAttribute("panier");

			System.out.println("panier : " + p);
			System.out.println("nombre lignes du panier : " + p.getSizeContenuPanier());
			
			// je supprime la ligne panier
			// il faut la r�f�rence de la ligne � supprimer en argument
			
			p.supprimerLignePanier(refArticleLigne);
			
			System.out.println("j'ai supprim� la ligne");
			System.out.println("nombre lignes du panier : " + p.getSizeContenuPanier());

			// je r�cup�re la requ�te et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/Panier.jsp");
			rd.forward(request, response);
			
		} // si on clic bouton supprimer	

	
	
	
	} // do post
}
