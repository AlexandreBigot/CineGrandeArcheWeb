package fr.demos.formation.poe.CineGrandeAche;

public class Commande {

	private Panier panier;
	private Compte compte;
	private double totalTTC;
	private boolean paiementConfirme; // get pour vérifier si payé
	
	
	public Commande(Panier panier, Compte compte) {
		super();
		this.panier = panier;
		this.compte = compte;
		// TVA 20% sur tous les articles...
		this.totalTTC = this.panier.getPrixTotal()*1.2;
	} // constructeur
	
	
	public void validationCommande () {
		if (paiementConfirme == true) {
			// this.commande.creationFacture
			// this.commande.declencherLivraison
			
		} else {
		//	return exception paiement non validé
		}
	}
	
	
	
	//TODO
	// ### méthode pour payer
	// ### méthode pour enregistrer et stocker la commande
	// ### méthode pour émettre la facture
	// ### méthode pour déclencher la livraison
	
} // class
