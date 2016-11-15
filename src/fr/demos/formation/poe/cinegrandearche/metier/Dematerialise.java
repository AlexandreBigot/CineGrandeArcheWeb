package fr.demos.formation.poe.cinegrandearche.metier;

public class Dematerialise {

	private String format;
	private String urlDownload;
	
	public Dematerialise(String format, String urlDownload) {
		super();
		this.format = format;
		this.urlDownload = urlDownload;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getUrlDownload() {
		return urlDownload;
	}

	public void setUrlDownload(String urlDownload) {
		this.urlDownload = urlDownload;
	}
	
}
