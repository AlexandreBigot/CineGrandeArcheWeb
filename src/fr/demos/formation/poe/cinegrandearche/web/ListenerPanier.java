package fr.demos.formation.poe.cinegrandearche.web;

import java.util.ArrayList;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import fr.demos.formation.poe.cinegrandearche.data.ArticleDAOMySql;
import fr.demos.formation.poe.cinegrandearche.metier.Article;
import fr.demos.formation.poe.cinegrandearche.metier.Compte;
import fr.demos.formation.poe.cinegrandearche.metier.Panier;

@WebListener
public class ListenerPanier implements HttpSessionListener {
	
    public ListenerPanier() {
        // TODO Auto-generated constructor stub
    }

    public void sessionCreated(HttpSessionEvent arg0)  { 
    	// je cr�e mon arrayList � partir de la BDD SQL
    	try {
			ArticleDAOMySql articleDAOMySql = new ArticleDAOMySql();
			
			ArrayList<Article> catalogue = (ArrayList<Article>) articleDAOMySql.select("");
			arg0.getSession().setAttribute("catalogue", catalogue);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	// je cr�e un panier au d�marrage de la session
    	Panier p = new Panier();
    	// je pourrai utiliser mon panier dans ma servlet avec session.getAttribute(panier)
    	// ou en EL ${panier} dans jsp
    	arg0.getSession().setAttribute("panier", p);
    	
    	// je cr�e un attribut pour la session pour savoir de quelle jsp je viens
    	// Page Articles par d�faut car page d'accueil
    	String jspCourante = "/Articles.jsp";
    	arg0.getSession().setAttribute("jspCourante", jspCourante);
    	
    	// Je mets compte dans la session
    	// si compte null je ne suis pas connect�
    	Compte compte = null;
    	arg0.getSession().setAttribute("compteSession", compte);

    	
    }

    public void sessionDestroyed(HttpSessionEvent arg0)  { 
         // cr�er une sauvegarde panier pour le compte utilisateur lors d�connection
    	// ou destruction panier si panier non reli� � utilisateur
    }
	
}
