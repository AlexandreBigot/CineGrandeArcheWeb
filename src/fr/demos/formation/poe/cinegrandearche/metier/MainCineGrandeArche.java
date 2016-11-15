package fr.demos.formation.poe.cinegrandearche.metier;

import fr.demos.formation.poe.cinegrandearche.exceptions.ExceptionQuantiteDemandeeSuperieureAuStock;
import fr.demos.formation.poe.cinegrandearche.exceptions.ExceptionRetirerArticleAbsentDuPanier;
import fr.demos.formation.poe.cinegrandearche.exceptions.ExceptionRetirerArticlePanier;

public class MainCineGrandeArche {

	public static void main(String[] args) {

		//livre standard matériel neuf
		Livre l1 = new Livre("refDuLivreL1", 20.00, "nomL1", 10, "auteur de 1l", "isbn de l1", "editeur de l1", "genre de l1");
		System.out.println(l1);
		
		//livre dématerialise
		Livre l2 = new Livre("ref du livre l2", 15, "nomL2", "format l2", "URL telechargement l2",
				"auteur l2", "isbn l2", "editeur l2", "genre l2");
		
	//	ArticleDivers ad1 = new ArticleDivers("ref de l'article ad1", 15.00, "nom AD1", "Format PDF", "URL de ad1", "Affiche", "est une affiche du film machin truc");
	//	System.out.println(ad1);

		Panier p1 = new Panier();
		System.out.println("j'ajoute un livre l1 dans panier"); 
		try {
			p1.ajouterUnArticle(l2, 2);
		} catch (ExceptionQuantiteDemandeeSuperieureAuStock e1) {
			System.out.println(e1.getMessage());
		}
		
		for (LignePanier ligneArticle : p1.lignesPanier){
			System.out.println(ligneArticle);
		}

		System.out.println("j'ajoute un second livre l1 dans panier"); 
		try {
			p1.ajouterUnArticle(l2, 1);
			for (LignePanier ligneArticle : p1.lignesPanier){
				System.out.println(ligneArticle);}
		} catch (ExceptionQuantiteDemandeeSuperieureAuStock e1) {
			System.out.println(e1.getMessage());
		}
	
		System.out.println("je calcule le montant total du panier"); 
		System.out.println(p1.getPrixTotal());
	
		try {
			p1.retirerUnArticle(l2, 1);
			for (LignePanier ligneArticle : p1.lignesPanier){
				System.out.println(ligneArticle);
			}

		} catch (ExceptionRetirerArticlePanier e1) {
			System.out.println(e1.getMessage());
		} catch (ExceptionRetirerArticleAbsentDuPanier e1) {
			System.out.println(e1.getMessage());
		}
		
		System.out.println("je calcule le montant total du panier"); 
		System.out.println(p1.getPrixTotal());
	
			try {
				p1.retirerUnArticle(l2, 3);
				for (LignePanier ligneArticle : p1.lignesPanier){
					System.out.println(ligneArticle);
				}
			} catch (ExceptionRetirerArticlePanier e) {
				System.out.println(e.getMessage() + e.getQuantiteDepassement());
			} catch (ExceptionRetirerArticleAbsentDuPanier e) {
				System.out.println(e.getMessage());
			}

			
			
			//System.out.println(e.getMessage() + e.getQuantiteDepassement());		}

		System.out.println("je calcule le montant total du panier"); 
		System.out.println(p1.getPrixTotal());
		
		
	}//main

}
