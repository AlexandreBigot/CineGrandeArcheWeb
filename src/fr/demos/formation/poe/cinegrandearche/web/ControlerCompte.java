package fr.demos.formation.poe.cinegrandearche.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/ControlerCompte")
public class ControlerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private boolean connecteAuCompte = false;

	public ControlerCompte() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// je r�cup�re la requ�te et je renvoie vers la JSP
		RequestDispatcher rd = request.getRequestDispatcher("/GestionCompte.jsp");
		rd.forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// je dis � tomcat d'utiliser les accente et caract�res sp�ciaux
		request.setCharacterEncoding("UTF-8");
		
		// j'identifie et je stocke la session actuelle
		HttpSession session = request.getSession();
		
		// je stocke le param�tre de requete (le name du bouton)
		String action = request.getParameter("action");

		// si on clique sur connection je me connecte
		// TODO : v�rification nom utilisateur et mdp pour valider connection
		if (action != null && action.equals("Connection")) {
			connecteAuCompte = true;

			// je mets mon boolean connecteAuCompte en attribut de session pour
			// pouvoir utiliser EL
			session.setAttribute("connecteAuCompte", connecteAuCompte);

			// je r�cup�re la requ�te et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/Articles.jsp");
			rd.forward(request, response);
		} // if bouton connection
		
		
		
		// if bouton voir le compte
		if (connecteAuCompte && action != null && action.equals("Voir le compte")) {
			// je r�cup�re la requ�te et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/GestionCompte.jsp");
			rd.forward(request, response);
		} // if bouton Voir le compte
		
		
		
		
		// if bouton Se d�connecter
		if (connecteAuCompte && action != null && action.equals("Se d�connecter")) {
			// je r�cup�re la requ�te et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/GestionCompte.jsp");
			rd.forward(request, response);
		} // if bouton Se d�connecter
		
		
		
		
		// if bouton Cr�er un Compte	
		if (action != null && action.equals("Cr�er un compte")) {
			System.out.println("j'ai cliqu� sur cr�er un compte");
			// je r�cup�re la requ�te et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/CreerCompte.jsp");
			rd.forward(request, response);
		} // if bouton Cr�er un Compte


	} // do post

}
