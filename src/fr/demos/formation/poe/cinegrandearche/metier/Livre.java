package fr.demos.formation.poe.cinegrandearche.metier;

import java.time.LocalDate;

public class Livre extends Article {
	
	private String auteur;
	private String isbn;
	private String editeur;
	private String genre;
	private LocalDate date;
	
	// constructeur livre d�mat�rialis�
	public Livre(String argRef, double argPrixHt, String argNom, String argFormat, String argUrlDownload, String argAuteur,
			String argIsbn, String argEditeur, String argGenre) {
		super(argRef, argPrixHt, argNom, argFormat, argUrlDownload);
		this.auteur = argAuteur;
		this.isbn = argIsbn;
		this.editeur = argEditeur;
		this.genre = argGenre;
	}
	
	// constructeur livre mat�rialis� neuf
	public Livre(String argRef, double argPrixHt, String argNom, int argStock, String argAuteur,
			String argIsbn, String argEditeur, String argGenre) {
		super(argRef, argPrixHt, argNom, argStock);
		this.auteur = argAuteur;
		this.isbn = argIsbn;
		this.editeur = argEditeur;
		this.genre = argGenre;
	}

	// constructeur livre mat�rialis� non neuf
	public Livre(String argRef, double argPrixHt, String argNom, int argStock,  Etat argEtat, String argAuteur,
			String argIsbn, String argEditeur, String argGenre) {
		super(argRef, argPrixHt, argNom, argStock, argEtat);
		this.auteur = argAuteur;
		this.isbn = argIsbn;
		this.editeur = argEditeur;
		this.genre = argGenre;
	}

	@Override
	public String toString() {
		if (super.getMateriel() == null){		
		return "Livre D�mat�rialis� [auteur=" + auteur + ", isbn=" + isbn + ", editeur=" + editeur + ", genre=" + genre + ", date="
				+ date + ", getPrixHt()=" + getPrixHt() + ", getRef()=" + getRef() + ", getNom()=" + getNom()
				+ ", getFormat()=" + super.getImmateriel().getFormat() + ", getUrlDownload()="+ super.getImmateriel().getUrlDownload() + "]";
		} else {
		return "Livre mat�riel [auteur=" + auteur + ", isbn=" + isbn + ", editeur=" + editeur + ", genre=" + genre + ", date="
				+ date + ", PrixHt=" + getPrixHt() + ", Ref=" + getRef() + ", Nom=" + getNom()
				+ ", mat�rialis�" + ", Stock=" + getStock() + ", Etat=" + super.getMateriel().getEtat() + "]";
		}
	}//toString

	public String getAuteur() {
		return auteur;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getEditeur() {
		return editeur;
	}

	public String getGenre() {
		return genre;
	}

	public LocalDate getDate() {
		return date;
	}
	
	
	
}