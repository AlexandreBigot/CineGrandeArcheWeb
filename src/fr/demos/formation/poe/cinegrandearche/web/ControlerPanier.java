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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	} // do get

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		// je stocke le param�tre de requete (le name du bouton)
		String action = request.getParameter("action");

		// si on clique sur ajouter au panier
		// TODO : v�rification nom utilisateur et mdp pour valider connection
		if (action != null && action.equals("Ajouter au panier")) {
		
			// j'identifie et je stocke la session actuelle
			HttpSession session = request.getSession();
			
			// ### je r�cup�re la quantit� mais c'est un string ###
			String stringQuantiteAjoutee = request.getParameter("quantiteAjouteePanier");
			
			// transforme stringQuantit�Ajoutee en intQuantiteAjoutee
			int intQuantiteAjoutee = Integer.parseInt(stringQuantiteAjoutee);			
			
			// ### je r�cupere la r�f de l'article ###
			String refArticle = request.getParameter("refArticle");
			
			// ### je r�cup�re l'arraylist du controlerArticle en le castant en arrayList
			ArrayList<Article> catalogue = (ArrayList<Article>)session.getAttribute("catalogue");
			
			//et je cherche l'article de cette r�f###
			for (Article article : catalogue) {
				if (article.getRef().equals(refArticle)){
					Panier p = (Panier)session.getAttribute("panier");
					try {
						p.ajouterUnArticle(article, intQuantiteAjoutee);
					} catch (ExceptionQuantiteDemandeeSuperieureAuStock e) {
						e.getMessage();
					}
				}
			}

			
			
			// ### je d�clenche la m�thode panier ajouterArticle###	
							
			// appel de la m�thode sur l'attribut de session d�fini dans le listener panier
			// on r�cup�re un objet donc il faut le caster en Panier
			
			
			
			
			
			// je r�cup�re la requ�te et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/Articles.jsp");
			rd.forward(request, response);

			// m�thode ajouter article de l'objet panier
			
		} // if bouton connection

		
		
	} // do post
}
