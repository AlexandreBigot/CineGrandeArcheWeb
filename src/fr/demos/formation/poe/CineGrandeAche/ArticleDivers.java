package fr.demos.formation.poe.CineGrandeAche;

public class ArticleDivers extends Article {

	private String type;
	private String caracteristiques;
	
	// constructeur article divers dématérialisé
	public ArticleDivers(String argRef, double argPrixHt, String argNom, String argFormat, String argUrlDownload, String type, String caracteristiques) {
		super(argRef, argPrixHt, argNom, argFormat, argUrlDownload);
		this.type = type;
		this.caracteristiques = caracteristiques;
	}
	
	// constructeur article divers matérialisé neuf
	public ArticleDivers(String argRef, double argPrixHt, String argNom, int argStock, String type, String caracteristiques) {
		super(argRef, argPrixHt, argNom, argStock);
		this.type = type;
		this.caracteristiques = caracteristiques;
	}
	
	// constructeur article divers matérialisé non neuf
	public ArticleDivers(String argRef, double argPrixHt, String argNom, int argStock, Etat argEtat, String type, String caracteristiques) {
		super(argRef, argPrixHt, argNom, argStock, argEtat);
		this.type = type;
		this.caracteristiques = caracteristiques;
	}
	
	public void addCaracteristique (){
		
	}

	public String getType() {
		return type;
	}

	public String getCaracteristiques(String newCaracteristique) {
		this.caracteristiques = this.caracteristiques + ", " + newCaracteristique;
		return caracteristiques;
	}

	@Override
	public String toString() {
		return "ArticleDivers [type=" + type + ", caracteristiques=" + caracteristiques + ", toString()="
				+ super.toString() + "]";
	}

}
