package fr.demos.formation.poe.cinegrandearche.metier;

public abstract class Article {

	private String ref;
	private double prixHt;
	private String nom;
	private String description;
	private String urlImage;
	private boolean dematerialise;
	// si dematerialise = true
	private String format;
	private String urlDownload;
	// si dematerialise = false
	private int stock;
	private Etat etat;

	// constructeur si dématérialisé pdf, iso, exe... (appel avec arguments
	// format et urlDownload)
	public Article(String argRef, double argPrixHt, String argNom, String argFormat, String argUrlDownload) {
		super();
		this.dematerialise = true;
		this.ref = argRef;
		this.prixHt = argPrixHt;
		this.nom = argNom;
		this.format = argFormat;
		this.urlDownload = argUrlDownload;
	}

	// constructeur si matérialisé et neuf car 80% de l'activité = livres neufs
	public Article(String argRef, double argPrixHt, String argNom, int argStock) {
		super();
		this.dematerialise = false;
		this.ref = argRef;
		this.prixHt = argPrixHt;
		this.nom = argNom;
		this.stock = argStock;
		this.etat = Etat.NEUF;
	}

	// constructeur si matérialisé livre, dvd, cd... (appel avec arguments stock
	// et etat)
	public Article(String argRef, double argPrixHt, String argNom, int argStock, Etat argEtat) {
		super();
		this.dematerialise = false;
		this.ref = argRef;
		this.prixHt = argPrixHt;
		this.nom = argNom;
		this.stock = argStock;
		this.etat = argEtat;
	}

	@Override
	public String toString() {
		if (this.isDematerialise()){
			return "Article [ref=" + ref + ", prixHt=" + prixHt + ", nom=" + nom + ", non matérialisé"
					+ ", format=" + format + "]";

		} else {
		
		return "Article [ref=" + ref + ", prixHt=" + prixHt + ", nom=" + nom + ", matérialisé"
				+ ", stock=" + stock + ", etat=" + etat + "]";
		}
	}	

	public void addStock(int quantite) {
		if (!this.isDematerialise()) {
			this.stock = this.stock + quantite;
		}
	}// addStock

	public void removeStock(int quantite) {
		if (!this.isDematerialise()) { // si dematérialisé est false
			if (this.stock == 0) {
			} else {
				this.stock = this.stock - quantite;
			}
		}
	}// removeStock

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		if (ref == null) {
			if (other.ref != null)
				return false;
		} else if (!ref.equals(other.ref))
			return false;
		return true;
	}

	public double getPrixHt() {
		return prixHt;
	}

	public void setPrixHt(double prixHt) {
		this.prixHt = prixHt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrlImage() {
		return urlImage;
	}

	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}

	public String getUrlDownload() {
		return urlDownload;
	}

	public void setUrlDownload(String urlDownload) {
		this.urlDownload = urlDownload;
	}

	public int getStock() {
		if (this.isDematerialise()){ // si dématerialisé, pas de stock à gérer, "1 valeur finale"
			return 1;
		} else {
		return stock;
		}
	}	

	public Etat getEtat() {
		return etat;
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public String getRef() {
		return ref;
	}

	public String getNom() {
		return nom;
	}

	public boolean isDematerialise() {
		return dematerialise;
	}

	public String getFormat() {
		return format;
	}

} // class
