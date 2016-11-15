package fr.demos.formation.poe.cinegrandearche.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.demos.formation.poe.cinegrandearche.metier.Livre;

@WebServlet("/ControlerArticles")
public class ControlerArticles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControlerArticles() {
        super();
        // TODO Auto-generated constructor stub
    } // constructeur

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// j'identifie et je stocke la session actuelle
		HttpSession session = request.getSession();

		//livre standard matériel neuf
		Livre l1 = new Livre("refDuLivreL1", 20.00, "nomL1", 10, "auteur de 1l", "isbn de l1",
				"editeur de l1", "genre de l1");
		session.setAttribute("livre1", l1);
		
		//livre dématerialise
		Livre l2 = new Livre("ref du livre l2", 15, "nomL2", "PDF", "URL telechargement l2",
				"auteur l2", "isbn l2", "editeur l2", "genre l2");
		session.setAttribute("livre2", l2);
		
		// je récupère la requête et je renvoie vers la JSP
		RequestDispatcher rd = request.getRequestDispatcher("/Articles.jsp");
		rd.forward(request, response);
		
	}// do get

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}// do post

} //class
