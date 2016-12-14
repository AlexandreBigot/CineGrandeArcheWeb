package fr.demos.formation.poe.cinegrandearche.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.demos.formation.poe.cinegrandearche.exceptions.ExceptionPasswordFail;
import fr.demos.formation.poe.cinegrandearche.metier.Compte;
import fr.demos.formation.poe.cinegrandearche.metier.CompteLogin;

@WebServlet("/ControlerCompte")
public class ControlerCompte extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// a gérer dans Compte.java puis instancier un compte et le récupérer
//	private boolean connecteAuCompte = false;

	public ControlerCompte() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// j'identifie et je stocke la session actuelle
		HttpSession session = request.getSession();
		
		// je récupère la requête et je renvoie vers la JSP
		RequestDispatcher rd = request.getRequestDispatcher("/GestionCompte.jsp");
		rd.forward(request, response);
		
		//je renseigne la nouvelle jsp courante après chaque rd.forward (la même que le forward)
    	String jspCourante = "/GestionCompte.jsp";
    	session.setAttribute("jspCourante", jspCourante);
		
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// je dis à tomcat d'utiliser les accente et caractères spéciaux
		request.setCharacterEncoding("UTF-8");
		
		// j'identifie et je stocke la session actuelle
		HttpSession session = request.getSession();
		
		// je stocke le paramètre de requete (le name du bouton)
		String action = request.getParameter("action");
		
		
		
		// je mets mon boolean connecteAuCompte en attribut de session pour
		// pouvoir utiliser EL
	//	session.setAttribute("connecteAuCompte", connecteAuCompte);

		// si on clique sur connection je me connecte
		// TODO ??? : vérification nom utilisateur et mdp pour valider connection
		if (action != null && action.equals("Connection")) {
						
			// je récupère les email et password
			String email = request.getParameter("email").toLowerCase();
			String password = request.getParameter("password");
			
			// je crée un objet pour tester ma connexion au compte
			CompteLogin compteLogin = new CompteLogin();
			
			// j'appelle la méthode pour me connecter avec les infos saisies dans la vue
			// et me retourner un compte ou null
			try {
				Compte compteConnecte = compteLogin.getCompteSiConnexionReussie(email, password);
		    	session.setAttribute("compteSession", compteConnecte);
				if(compteConnecte == null){
					System.out.println("mon compteSession est null : " + compteConnecte);
			    	String messageErreurConnexion = "Echec connexion";
			    	request.setAttribute("messageErreurConnexion", messageErreurConnexion);
			    	// déclencher un message mauvais identifiant ou email, exception ou pas ? pas de sortie en erreur...
				} //if

	
			} catch (Exception e) {
				System.out.println("exception connexion datasource SQL dans select(String email, String password) de CompteDAOMySql");			
			}
			
			//méthode bourrin avant d'avoir un compte
//			// je redéfini la valeur de ma variable
//			// il fait de l'autoboxing donc pas besoin de luimettre un objet  mais la valeur compatible avec le type
//			session.setAttribute("connecteAuCompte", true);

			// je récupère la requête et je renvoie vers la JSP
			String uriCible = (String)session.getAttribute("jspCourante");
			RequestDispatcher rd = request.getRequestDispatcher(uriCible);
			rd.forward(request, response);

			// pas besoin de changer le jspCourante car c'est la même
		
		} // if connexion

			
			
		// if bouton Se déconnecter
		if (session.getAttribute("compteSession") != null && action != null && action.equals("Se déconnecter")) {

			// je déconnecte
			// je redéfini la valeur de ma variable
			// il fait de l'autoboxing donc pas besoin de luimettre un objet  mais la valeur compatible avec le type
			session.setAttribute("compteSession", null);
			
			// je récupère la requête et je renvoie vers la JSP
			// mais si je me déconnecte dans la page gestion de compte je dois changer de page !
			if (session.getAttribute("jspCourante").equals("/GestionCompte.jsp")){

				// on va dire qu'on retourne à l'acceuil après une déconnexion du compte
				RequestDispatcher rd = request.getRequestDispatcher("/Articles.jsp");
				rd.forward(request, response);

				//je renseigne la nouvelle jsp courante après chaque rd.forward (la même que le forward)
		    	String jspCourante = "/Articles.jsp";
		    	session.setAttribute("jspCourante", jspCourante);
				
			} else {
			String uriCible = (String)session.getAttribute("jspCourante");
			RequestDispatcher rd = request.getRequestDispatcher(uriCible);
			rd.forward(request, response);
			// pas besoin de changer le jspCourante car c'est la même
			}
			
		} // if bouton Se déconnecter

		
		
		// if bouton voir le compte
		if (session.getAttribute("compteSession") != null && action != null && action.equals("Voir le compte")) {
			// je récupère la requête et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/GestionCompte.jsp");
			rd.forward(request, response);
			
			//je renseigne la nouvelle jsp courante après chaque rd.forward (la même que le forward)
	    	String jspCourante = "/GestionCompte.jsp";
	    	session.setAttribute("jspCourante", jspCourante);
			
		} // if bouton Voir le compte
		
		
		
		
		
		
		
		
		// if bouton Créer un Compte	
		if (action != null && action.equals("Créer un compte")) {
			
			// je récupère la requête et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/CreerCompte.jsp");
			rd.forward(request, response);
			
			//je renseigne la nouvelle jsp courante après chaque rd.forward (la même que le forward)
	    	String jspCourante = "/CreerCompte.jsp";
	    	session.setAttribute("jspCourante", jspCourante);
			
		} // if bouton Créer un Compte

		
// ###	if bouton Valider (nouveau compte)   ###
		if (action != null && action.equals("Valider")) {
			
			// je récupère la requête et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/CreerCompte.jsp");
			rd.forward(request, response);
			
			//je renseigne la nouvelle jsp courante après chaque rd.forward (la même que le forward)
	    	String jspCourante = "/CreerCompte.jsp";
	    	session.setAttribute("jspCourante", jspCourante);

		} // if bouton Créer un Compte


	} // do post

}
