package fr.demos.formation.poe.cinegrandearche.metier;

import java.util.ArrayList;
import java.util.Iterator;

import fr.demos.formation.poe.cinegrandearche.exceptions.ExceptionQuantiteDemandeeSuperieureAuStock;
import fr.demos.formation.poe.cinegrandearche.exceptions.ExceptionRetirerArticleAbsentDuPanier;
import fr.demos.formation.poe.cinegrandearche.exceptions.ExceptionRetirerArticlePanier;

public class Panier implements Iterable<LignePanier> {
	
//	private Compte compte;
//	relier un panier à une session ou un compte
	private ArrayList<LignePanier> lignesPanier = new ArrayList<LignePanier>();
	
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
			if (a.getMateriel() == null){ // si dématerialise on s'en fiche c'est illimité
				lignesPanier.add(new LignePanier(a, quantite));
			} else { // si matérialisé stock insuffisant
				throw new ExceptionQuantiteDemandeeSuperieureAuStock("Le stock n'est pas suffisant, nombre d'articles en stock : ", a.getStock());
				}//else
		}//if
	}// ajouterLignePanier
	
	public void ajouterUnArticle(Article a, int quantiteAjoutée) throws ExceptionQuantiteDemandeeSuperieureAuStock{
		LignePanier lp1 = new LignePanier(a, quantiteAjoutée);
		int indexDeMaLigne = lignesPanier.indexOf(lp1);
		
		// modif importante !!!
		// si la ligne a déjà des articles il faut les comptabiliser pour comparer au stock
		// test sur quantitéLignePlusQuantiteAjoutée, pas uniquement sur la quantité ajoutée !!!
		int quantitéLignePlusQuantiteAjoutée = lp1.getQuantite() + quantiteAjoutée;
		
		// si la ligne article existe déjà
		if (indexDeMaLigne != -1){
			// si la quantité ajoutée <= stock de l'article
			if (quantitéLignePlusQuantiteAjoutée <= a.getStock()){
				lignesPanier.get(indexDeMaLigne).setQuantite(
						lignesPanier.get(indexDeMaLigne).getQuantite() + quantiteAjoutée);
			} else { // si quantite demandée supérieure au stock
				 // si dématerialise on s'en fiche c'est illimité
				if (a.getMateriel() == null){
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
		int quantiteLigne = lignesPanier.get(indexDeMaLigne).getQuantite();
		if (indexDeMaLigne != -1){
			if (quantiteRetiree == quantiteLigne) {
				this.supprimerLignePanier(indexDeMaLigne);
			}
			else if (quantiteRetiree > quantiteLigne){
				throw new ExceptionRetirerArticlePanier(
					"Vous essayez de retirer trop d'exemplaires de cet article, quantité dans le panier = ",
					quantiteLigne);
			}
			else if (quantiteRetiree < quantiteLigne) {
				int nouvelleQuantite = quantiteLigne - quantiteRetiree;
				lignesPanier.get(indexDeMaLigne).setQuantite(nouvelleQuantite);	
			}
		} else {
			throw new ExceptionRetirerArticleAbsentDuPanier("Impossible de supprimer un exemplaire, cet article n'est pas présent dans le panier");
			}

	}//retirerUnArticle

	// nombre de lignes dans panier
	public int getSizeContenuPanier(){
		int quantite = lignesPanier.size();
		return quantite;
	}
	
	//nombre d'articles dans panier (plusieurs articles par ligne)
	public int getArticlesCumulesPanier(){
		int i = 0;
		for(LignePanier lignePanier : lignesPanier){
			i = i + lignePanier.getQuantite();		
		}
		return i;
	}

	// méthod epour modifier la quantité de la ligne panier
	public void modifierQuantiteLignePanier (String refArticleLigne, int nouvelleQuantiteLigne){
		for(LignePanier lignePanier : lignesPanier){
			if (lignePanier.getArticle().getRef() == refArticleLigne){
				lignePanier.setQuantite(nouvelleQuantiteLigne);
			} // if
		} //for
	} // modifierQuantiteLignePanier
			
	@Override
	public Iterator<LignePanier> iterator() {
		// TODO Auto-generated method stub
		return lignesPanier.iterator();
	}
}// class
