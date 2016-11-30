package fr.demos.formation.poe.cinegrandearche.metier;

public class ArticleDivers extends Article {

	private String typeArticleDivers;
	private String caracteristiques;

	// constructeur article divers dématérialisé
	public ArticleDivers(String argRef, double argPrixHt, String argNom, String argUrlImage, String argFormat, String argUrlDownload,
			String argTypeArticleDivers, String caracteristiques) {
		super(argRef, argPrixHt, argNom, argUrlImage, argFormat, argUrlDownload);
		this.typeArticleDivers = argTypeArticleDivers;
		this.caracteristiques = caracteristiques;
	}

	// constructeur article divers matérialisé neuf
	public ArticleDivers(String argRef, double argPrixHt, String argNom, String argUrlImage, int argStock, String argTypeArticleDivers,
			String caracteristiques) {
		super(argRef, argPrixHt, argNom, argUrlImage, argStock);
		this.typeArticleDivers = argTypeArticleDivers;
		this.caracteristiques = caracteristiques;
	}

	// constructeur article divers matérialisé non neuf
	public ArticleDivers(String argRef, double argPrixHt, String argNom, String argUrlImage, int argStock, Etat argEtat, String argTypeArticleDivers,
			String caracteristiques) {
		super(argRef, argPrixHt, argNom, argUrlImage, argStock, argEtat);
		this.typeArticleDivers = argTypeArticleDivers;
		this.caracteristiques = caracteristiques;
	}

	@Override
	public String toString() {
		if (super.getMateriel() == null) {
			return "ArticleDivers [type=" + typeArticleDivers + ", caracteristiques=" + caracteristiques + ", toString()="
					+ super.getImmateriel().getFormat() + ", getUrlDownload()=" + super.getImmateriel().getUrlDownload()
					+ "]";
		} else {
			return "ArticleDivers [type=" + typeArticleDivers + ", caracteristiques=" + caracteristiques + ", toString()="
					+ ", Etat=" + super.getMateriel().getEtat() + "]";
		}
	}

	public void addCaracteristique(String newCaracteristique) {
		this.caracteristiques = this.caracteristiques + ", " + newCaracteristique;
	}

	public String getTypeArticleDivers() {
		return typeArticleDivers;
	}

	public void setTypeArticleDivers(String typeArticleDivers) {
		this.typeArticleDivers = typeArticleDivers;
	}

	public String getCaracteristiques() {
		return caracteristiques;
	}

	public void setCaracteristiques(String caracteristiques) {
		this.caracteristiques = caracteristiques;
	}

}
