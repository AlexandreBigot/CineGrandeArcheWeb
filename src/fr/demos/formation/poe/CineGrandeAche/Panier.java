package fr.demos.formation.poe.CineGrandeAche;

import java.util.ArrayList;

public class Panier {
	
//	private Compte compte;
//	relier un panier à une session ou un compte
	ArrayList<LignePanier> lignesPanier = new ArrayList<LignePanier>();
	
	public double getPrixTotal(){
		double prixTotal=0;
		for (LignePanier ligneDuPanier : lignesPanier){
			prixTotal = prixTotal + (ligneDuPanier.getQuantite() * ligneDuPanier.getArticle().getPrixHt());
		}
		return prixTotal;
	}
	
	//sera appelée par ajouterUnArticle
	private void ajouterLignePanier(Article a, int quantite) throws ExceptionQuantiteDemandeeSuperieureAuStock{
		//si quantité demandée inférieure ou égale au stock
		if (quantite <= a.getStock()){
			lignesPanier.add(new LignePanier(a, quantite));
		} else { // si quantite demandée supérieure au stock
			if (a.isDematerialise()){ // si dématerialise on s'en fiche c'est illimité
				lignesPanier.add(new LignePanier(a, quantite));
			} else { // si matérialisé stock insuffisant
				throw new ExceptionQuantiteDemandeeSuperieureAuStock("Le stock n'est pas suffisant, nombre d'articles en stock : ", a.getStock());
				}//else
		}//if
	}// ajouterLignePanier
	
	public void ajouterUnArticle(Article a, int quantiteAjoutée) throws ExceptionQuantiteDemandeeSuperieureAuStock{
		LignePanier lp1 = new LignePanier(a, quantiteAjoutée);
		int indexDeMaLigne = lignesPanier.indexOf(lp1);
		// si la ligne article existe déjà
		if (indexDeMaLigne != -1){
			// si la quantité ajoutée <= stock de l'article
			if (quantiteAjoutée <= a.getStock()){
				lignesPanier.get(indexDeMaLigne).setQuantite(
						lignesPanier.get(indexDeMaLigne).getQuantite() + quantiteAjoutée);
			} else { // si quantite demandée supérieure au stock
				 // si dématerialise on s'en fiche c'est illimité
				if (a.isDematerialise()){
					lignesPanier.get(indexDeMaLigne).setQuantite(
							lignesPanier.get(indexDeMaLigne).getQuantite() + quantiteAjoutée);
				} else { // si matérialisé et stock insuffisant
					throw new ExceptionQuantiteDemandeeSuperieureAuStock(
							"Le stock n'est pas suffisant, nombre d'articles en stock : ", a.getStock());
					}//else
			}//if
		}//if
		// si la ligne article n'existe pas
		else { 
			this.ajouterLignePanier(a, quantiteAjoutée);
		}//else
	}//ajouterUnArticle
	
	// appelée par retirer un article
	private void supprimerLignePanier(int i){
		lignesPanier.remove(i);
	}
	
	public void retirerUnArticle(Article a, int quantiteRetiree) throws ExceptionRetirerArticlePanier, ExceptionRetirerArticleAbsentDuPanier{
		LignePanier lp1 = new LignePanier(a, quantiteRetiree);
		int indexDeMaLigne = lignesPanier.indexOf(lp1);
		if (indexDeMaLigne != -1){
			if (quantiteRetiree == lignesPanier.get(indexDeMaLigne).getQuantite()){
				this.supprimerLignePanier(indexDeMaLigne);
			}
			else if (quantiteRetiree > lignesPanier.get(indexDeMaLigne).getQuantite()){
				throw new ExceptionRetirerArticlePanier(
					"Vous essayez de retirer trop d'exemplaires de cet article, quantité dans le panier = ",
					lignesPanier.get(indexDeMaLigne).getQuantite());
			}
			else if (quantiteRetiree < lignesPanier.get(indexDeMaLigne).getQuantite()) {
				int nouvelleQuantite = lignesPanier.get(indexDeMaLigne).getQuantite() - quantiteRetiree;
				lignesPanier.get(indexDeMaLigne).setQuantite(nouvelleQuantite);	
			}
		} else {
			throw new ExceptionRetirerArticleAbsentDuPanier("Impossible de supprimer un exemplaire, cet article n'est pas présent dans le panier");
			}
	

	}//retirerUnArticle
}// class
