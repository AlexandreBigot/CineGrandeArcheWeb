package fr.demos.formation.poe.cinegrandearche.metier;

public abstract class Article {

	private String ref;
	private double prixHt;
	private String nom;
	private String description;
	private String urlImage;
	private Materialise materiel;
	private Dematerialise immateriel;
	private int stock;

	// constructeur si dématérialisé pdf, iso, exe... (appel avec arguments
	public Article(String argRef, double argPrixHt, String argNom, String argUrlImage, String argFormat, String argUrlDownload) {
		super();
		this.ref = argRef;
		this.prixHt = argPrixHt;
		this.nom = argNom;
		this.urlImage = argUrlImage;
		this.stock = 1;
		this.immateriel = new Dematerialise(argFormat, argUrlDownload);
	}

	// constructeur si matérialisé et neuf car 80% de l'activité = livres neufs
	public Article(String argRef, double argPrixHt, String argNom, String argUrlImage, int argStock) {
		super();
		this.ref = argRef;
		this.prixHt = argPrixHt;
		this.nom = argNom;
		this.urlImage = argUrlImage;
		this.stock = argStock;
		this.materiel = new Materialise(Etat.NEUF);
	}

	// constructeur si matérialisé livre, dvd, cd... (appel avec arguments stock
	// et etat)
	public Article(String argRef, double argPrixHt, String argNom, String argUrlImage, int argStock, Etat argEtat) {
		super();
		this.ref = argRef;
		this.prixHt = argPrixHt;
		this.nom = argNom;
		this.urlImage = argUrlImage;
		this.stock = argStock;
		this.materiel = new Materialise(argEtat);
	}
	
	@Override
	public String toString() {
		return "Article [ref=" + ref + ", prixHt=" + prixHt + ", nom=" + nom + ", description=" + description
				+ ", urlImage=" + urlImage + ", materiel=" + materiel + ", immateriel=" + immateriel + ", stock="
				+ stock + "]";
	}

	//METHODE INUTILE POUR LE MOMENT
	public void addStock(int quantite) {
		if (this.immateriel == null) {
			this.stock = this.stock + quantite;
		} else {
			// exception "ça rime à rien d'ajouter un article immateriel !!!" ADMINISTRATION
		}
	}// addStock

	//METHODE INUTILE POUR LE MOMENT
	public void removeStock(int quantite) {
		if (this.immateriel == null) { // si dematérialisé est false
			if (this.stock == 0) { // si stock null
				// exception "on ne peut rien retirer de zero stock" ADMINISTRATION
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

	public int getStock() {
		if (this.materiel == null){ // si dématerialisé, pas de stock à gérer, "1 valeur finale"
			return 1;
		} else {
		return stock;	
		}
	}	

	public String getRef() {
		return ref;
	}

	public String getNom() {
		return nom;
	}

	public Materialise getMateriel() {
		return materiel;
	}

	public Dematerialise getImmateriel() {
		return immateriel;
	}

//	public boolean isDematerialise() {
//		return dematerialise;
//	}

	

} // class
