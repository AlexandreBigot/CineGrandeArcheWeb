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

		// je récupère la requête et je renvoie vers la JSP
		RequestDispatcher rd = request.getRequestDispatcher("/GestionCompte.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// je stocke le paramètre de requete (le name du bouton)
		String action = request.getParameter("action");

		// si on clique sur connection je me connecte
		// TODO : vérification nom utilisateur et mdp pour valider connection
		if (action != null && action.equals("Connection")) {
			connecteAuCompte = true;

			// j'identifie et je stocke la session actuelle
			HttpSession session = request.getSession();
			// je mets mon boolean connecteAuCompte en attribut de session pour
			// pouvoir utiliser EL
			session.setAttribute("connecteAuCompte", connecteAuCompte);

			// je récupère la requête et je renvoie vers la JSP
			RequestDispatcher rd = request.getRequestDispatcher("/Articles.jsp");
			rd.forward(request, response);

		} // if bouton connection

		// écouter le bouton connection et si champs pas vides changer
		// connecteAuCompte en true et modifier affichage : bouton pour accéder
		// au compte

	} // do post

}
