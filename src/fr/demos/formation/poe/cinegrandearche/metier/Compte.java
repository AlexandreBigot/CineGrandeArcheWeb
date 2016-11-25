package fr.demos.formation.poe.cinegrandearche.metier;

public class Compte {

	private String nom;
	private String prenom;
	private String nomUtilisateur;
	private String adresse;
	private String email;
	private String telephone;
	private String password;
	private boolean connecteAuCompte = false;
	private boolean creationPasswordTest = false;
	
	
	public Compte(String nom, String prenom, String nomUtilisateur, String password) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.nomUtilisateur = nomUtilisateur;
		this.password = password;
	}

		
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
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

	public boolean isConnecteAuCompte() {
		return connecteAuCompte;
	}

	public void setConnecteAuCompte(boolean connecteAuCompte) {
		this.connecteAuCompte = connecteAuCompte;
	}

	public boolean isCreationPasswordTest() {
		return creationPasswordTest;
	}

	public void setCreationPasswordTest(boolean creationPasswordTest) {
		this.creationPasswordTest = creationPasswordTest;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}	
}
