package fr.demos.formation.poe.cinegrandearche.metier;

import java.util.ArrayList;
import java.util.Iterator;

import fr.demos.formation.poe.cinegrandearche.exceptions.ExceptionQuantiteDemandeeSuperieureAuStock;
import fr.demos.formation.poe.cinegrandearche.exceptions.ExceptionRetirerArticleAbsentDuPanier;
import fr.demos.formation.poe.cinegrandearche.exceptions.ExceptionRetirerArticlePanier;

public class Panier implements Iterable<LignePanier> {
	
//	private Compte compte;
//	relier un panier � une session ou un compte
	private ArrayList<LignePanier> lignesPanier = new ArrayList<LignePanier>();
	
	public double getPrixTotal(){
		double prixTotal=0;
		for (LignePanier ligneDuPanier : lignesPanier){
			prixTotal = prixTotal + (ligneDuPanier.getQuantite() * ligneDuPanier.getArticle().getPrixHt());
		}
		return prixTotal;
	}
	
	//sera appel�e par ajouterUnArticle
	private void ajouterLignePanier(Article a, int quantite) throws ExceptionQuantiteDemandeeSuperieureAuStock{
		//si quantit� demand�e inf�rieure ou �gale au stock
		if (quantite <= a.getStock()){
			lignesPanier.add(new LignePanier(a, quantite));
		} else { // si quantite demand�e sup�rieure au stock
			if (a.getMateriel() == null){ // si d�materialise on s'en fiche c'est illimit�
				lignesPanier.add(new LignePanier(a, quantite));
			} else { // si mat�rialis� stock insuffisant
				throw new ExceptionQuantiteDemandeeSuperieureAuStock("Le stock n'est pas suffisant, nombre d'articles en stock : ", a.getStock());
				}//else
		}//if
	}// ajouterLignePanier
	
	public void ajouterUnArticle(Article a, int quantiteAjout�e) throws ExceptionQuantiteDemandeeSuperieureAuStock{
		LignePanier lp1 = new LignePanier(a, quantiteAjout�e);
		int indexDeMaLigne = lignesPanier.indexOf(lp1);
		
		// modif importante !!!
		// si la ligne a d�j� des articles il faut les comptabiliser pour comparer au stock
		// test sur quantit�LignePlusQuantiteAjout�e, pas uniquement sur la quantit� ajout�e !!!
		int quantit�LignePlusQuantiteAjout�e = lp1.getQuantite() + quantiteAjout�e;
		
		// si la ligne article existe d�j�
		if (indexDeMaLigne != -1){
			// si la quantit� ajout�e <= stock de l'article
			if (quantit�LignePlusQuantiteAjout�e <= a.getStock()){
				lignesPanier.get(indexDeMaLigne).setQuantite(
						lignesPanier.get(indexDeMaLigne).getQuantite() + quantiteAjout�e);
			} else { // si quantite demand�e sup�rieure au stock
				 // si d�materialise on s'en fiche c'est illimit�
				if (a.getMateriel() == null){
					lignesPanier.get(indexDeMaLigne).setQuantite(
							lignesPanier.get(indexDeMaLigne).getQuantite() + quantiteAjout�e);
				} else { // si mat�rialis� et stock insuffisant
					throw new ExceptionQuantiteDemandeeSuperieureAuStock(
							"Le stock n'est pas suffisant, nombre d'articles en stock : ", a.getStock());
					}//else
			}//if
		}//if
		// si la ligne article n'existe pas
		else { 
			this.ajouterLignePanier(a, quantiteAjout�e);
		}//else
	}//ajouterUnArticle
	
	// appel�e par retirer un article
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
					"Vous essayez de retirer trop d'exemplaires de cet article, quantit� dans le panier = ",
					quantiteLigne);
			}
			else if (quantiteRetiree < quantiteLigne) {
				int nouvelleQuantite = quantiteLigne - quantiteRetiree;
				lignesPanier.get(indexDeMaLigne).setQuantite(nouvelleQuantite);	
			}
		} else {
			throw new ExceptionRetirerArticleAbsentDuPanier("Impossible de supprimer un exemplaire, cet article n'est pas pr�sent dans le panier");
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

	// m�thod epour modifier la quantit� de la ligne panier
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
