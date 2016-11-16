package fr.demos.formation.poe.cinegrandearche.metier;

public class ArticleDivers extends Article {

	private String type;
	private String caracteristiques;

	// constructeur article divers dématérialisé
	public ArticleDivers(String argRef, double argPrixHt, String argNom, String argUrlImage, String argFormat, String argUrlDownload,
			String type, String caracteristiques) {
		super(argRef, argPrixHt, argNom, argUrlImage, argFormat, argUrlDownload);
		this.type = type;
		this.caracteristiques = caracteristiques;
	}

	// constructeur article divers matérialisé neuf
	public ArticleDivers(String argRef, double argPrixHt, String argNom, String argUrlImage, int argStock, String type,
			String caracteristiques) {
		super(argRef, argPrixHt, argNom, argUrlImage, argStock);
		this.type = type;
		this.caracteristiques = caracteristiques;
	}

	// constructeur article divers matérialisé non neuf
	public ArticleDivers(String argRef, double argPrixHt, String argNom, String argUrlImage, int argStock, Etat argEtat, String type,
			String caracteristiques) {
		super(argRef, argPrixHt, argNom, argUrlImage, argStock, argEtat);
		this.type = type;
		this.caracteristiques = caracteristiques;
	}

	@Override
	public String toString() {
		if (super.getMateriel() == null) {
			return "ArticleDivers [type=" + type + ", caracteristiques=" + caracteristiques + ", toString()="
					+ super.getImmateriel().getFormat() + ", getUrlDownload()=" + super.getImmateriel().getUrlDownload()
					+ "]";
		} else {
			return "ArticleDivers [type=" + type + ", caracteristiques=" + caracteristiques + ", toString()="
					+ ", Etat=" + super.getMateriel().getEtat() + "]";
		}
	}

	public void addCaracteristique(String newCaracteristique) {
		this.caracteristiques = this.caracteristiques + ", " + newCaracteristique;
	}

	public String getType() {
		return type;
	}

	public String getCaracteristiques() {
		return caracteristiques;
	}

}
