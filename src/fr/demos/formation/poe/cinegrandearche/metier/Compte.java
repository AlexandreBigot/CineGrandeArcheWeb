package fr.demos.formation.poe.cinegrandearche.metier;

import fr.demos.formation.poe.cinegrandearche.exceptions.ExceptionPasswordFormat;

public class Compte {

	private String nom;
	private String prenom;
	private String adresseCompte;
	private String adresseLivraison;
	private String email;
	private String telephone;
	private String password;
	private int longueurPasswordMini = 6;
			
	// Ne créer le compte que si la connection est OK
	// tester la connection sur compte ou null
	
	public Compte(String nom, String prenom, String adresseCompte, String adresseLivraison,
			String email, String telephone, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresseCompte = adresseCompte;
		this.adresseLivraison = adresseLivraison;
		this.email = email;
		this.telephone = telephone;
		this.password = password;
	}

	public void testCreationPassword (String password, String passwordConfirmation)throws ExceptionPasswordFormat{
		//test si les passwords entrés sont identiques
		if(password == passwordConfirmation){
			// test taille 6 caractères mini
			if (password.length() < longueurPasswordMini){
				
			} else {
				throw new ExceptionPasswordFormat("Votre mot de passe n'est pas assez long, vous avez entré un mot de passe de "
												+ password.length() + " caractères et il en faut au moins 6");
			}// test longueur
		} else {
			throw new ExceptionPasswordFormat("Vous avez entré des mots de passe différents");
		} // if else passwords identiques
	} // testCreationPassword
	
	
	
	@Override
	public String toString() {
		return "Compte [nom=" + nom + ", prenom=" + prenom + ", adresseCompte=" + adresseCompte + ", adresseLivraison="
				+ adresseLivraison + ", email=" + email + ", telephone=" + telephone + ", password=" + password
				+ ", longueurPasswordMini=" + longueurPasswordMini + "]";
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public String getAdresseCompte() {
		return adresseCompte;
	}

	public void setAdresseCompte(String adresseCompte) {
		this.adresseCompte = adresseCompte;
	}

	public String getAdresseLivraison() {
		return adresseLivraison;
	}

	public void setAdresseLivraison(String adresseLivraison) {
		this.adresseLivraison = adresseLivraison;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

}

