package fr.demos.formation.poe.CineGrandeAche;

public class ExceptionQuantiteDemandeeSuperieureAuStock extends Exception {

	private int quantiteDepassement;

	public ExceptionQuantiteDemandeeSuperieureAuStock(String message, int quantiteDepassement) {
		super(message);
		this.quantiteDepassement = quantiteDepassement;
	}
	
	public int getQuantiteDepassement(){
		return quantiteDepassement;
	}
	
}
