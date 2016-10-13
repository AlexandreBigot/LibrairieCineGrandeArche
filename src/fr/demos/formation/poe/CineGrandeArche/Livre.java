package fr.demos.formation.poe.CineGrandeArche;

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
			String argIsbn, String argEditeur) {
		super(argRef, argPrixHt, argNom, argStock);
		this.auteur = argAuteur;
		this.isbn = argIsbn;
		this.editeur = argEditeur;
	}

	// constructeur livre mat�rialis� non neuf
	public Livre(String argRef, double argPrixHt, String argNom, int argStock,  Etat argEtat, String argAuteur,
			String argIsbn, String argEditeur) {
		super(argRef, argPrixHt, argNom, argStock, argEtat);
		this.auteur = argAuteur;
		this.isbn = argIsbn;
		this.editeur = argEditeur;
	}

	@Override
	public String toString() {
		if (this.isDematerialise()){		
		return "Livre [auteur=" + auteur + ", isbn=" + isbn + ", editeur=" + editeur + ", genre=" + genre + ", date="
				+ date + ", getPrixHt()=" + getPrixHt() + ", getRef()=" + getRef() + ", getNom()=" + getNom()
				+ ", non mat�rialis�" + ", getFormat()=" + getFormat() + "]";
		} else {
		return "Livre [auteur=" + auteur + ", isbn=" + isbn + ", editeur=" + editeur + ", genre=" + genre + ", date="
				+ date + ", PrixHt=" + getPrixHt() + ", Ref=" + getRef() + ", Nom=" + getNom()
				+ ", mat�rialis�" + ", Stock=" + getStock() + ", Etat=" + getEtat() + "]";
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
