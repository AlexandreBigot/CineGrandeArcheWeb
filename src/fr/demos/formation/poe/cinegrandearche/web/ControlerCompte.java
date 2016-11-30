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

	// a g�rer dans Compte.java puis instancier un compte et le r�cup�rer
//	private boolean connecteAuCompte = false;

	public ControlerCompte() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// j'identifie et je stocke la session actuelle
		HttpSession session = request.getSession();
		
		// je r�cup�re la requ�te et je renvoie vers la JSP
		RequestDispatcher rd = request.getRequestDispatcher("/GestionCompte.jsp");
		rd.forward(request, response);
		
		//je renseigne la nouvelle jsp courante apr�s chaque rd.forward (la m�me que le forward)
    	String jspCourante = "/GestionCompte.jsp";
    	session.setAttribute("jspCourante", jspCourante);
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// je dis � tomcat d'utiliser les accente et caract�res sp�ciaux
		request.setCharacterEncoding("UTF-8");
		
		// j'identifie et je stocke la session actuelle
		HttpSession session = request.getSession();
		
		// je stocke le param�tre de requete (le name du bouton)
		String action = request.getParameter("action");
		
		
		
		// je mets mon boolean connecteAuCompte en attribut de session pour
		// pouvoir utiliser EL
	//	session.setAttribute("connecteAuCompte", connecteAuCompte);

		// si on clique sur connection je me connecte
		// TODO : v�rification nom utilisateur et mdp pour valider connection
		if (action != null && action.equals("Connection")) {
			
			// je red�fini la valeur de ma variable
			// il fait de l'autoboxing donc pas besoin de luimettre un objet  mais la valeur compatible avec le type
			session.setAttribute("connecteAuCompte", true);

			// je r�cup�re la requ�te et je renvoie vers la JSP
			String uriCible = (String)session.getAttribute("jspCourante");
			RequestDispatcher rd = request.getRequestDispatcher(uriCible);
			rd.forward(request, response);
			// pas besoin de changer le jspCourante car c'est la m�me
		
		} // if bouton connection
	
		
		// if bouton Se d�connecter
		if ((boolean)session.getAttribute("connecteAuCompte") && action != null && action.equals("Se d�connecter")) {

			// je d�connecte
			// plus tard il faudra sauvegarder le panier pour la prochaine connection
			// je red�fini la valeur de ma variable
			// il fait de l'autoboxing donc pas besoin de luimettre un objet  mais la valeur compatible avec le type
			session.setAttribute("connecteAuCompte", false);
			
			// je r�cup�re la requ�te et je renvoie vers la JSP
			String uriCible = (String)session.getAttribute("jspCourante");
			RequestDispatcher rd = request.getRequestDispatcher(uriCible);
			rd.forward(request, response);
			// pas besoin de changer le jspCourante car c'est la m�me
			
		} // if bouton Se d�connecter

		
		
		// if bouton voir le compte
		if ((boolean)session.getAttribute("connecteAuCompte") && action != null && action.equals("Voir le compte")) {
			// je r�cup�re la requ�te et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/GestionCompte.jsp");
			rd.forward(request, response);
			
			//je renseigne la nouvelle jsp courante apr�s chaque rd.forward (la m�me que le forward)
	    	String jspCourante = "/GestionCompte.jsp";
	    	session.setAttribute("jspCourante", jspCourante);
			
		} // if bouton Voir le compte
		
		
		
		
		
		
		
		
		// if bouton Cr�er un Compte	
		if (action != null && action.equals("Cr�er un compte")) {
			
			// je r�cup�re la requ�te et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/CreerCompte.jsp");
			rd.forward(request, response);
			
			//je renseigne la nouvelle jsp courante apr�s chaque rd.forward (la m�me que le forward)
	    	String jspCourante = "/CreerCompte.jsp";
	    	session.setAttribute("jspCourante", jspCourante);
			
		} // if bouton Cr�er un Compte

		
// ###	if bouton Valider (nouveau compte)   ###
		if (action != null && action.equals("Valider")) {
			
			// je r�cup�re la requ�te et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/CreerCompte.jsp");
			rd.forward(request, response);
			
			//je renseigne la nouvelle jsp courante apr�s chaque rd.forward (la m�me que le forward)
	    	String jspCourante = "/CreerCompte.jsp";
	    	session.setAttribute("jspCourante", jspCourante);

		} // if bouton Cr�er un Compte


	} // do post

}
