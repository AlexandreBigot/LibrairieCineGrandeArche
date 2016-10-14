package fr.demos.formation.poe.CineGrandeArche;

import java.util.ArrayList;

public class Panier {
	
	private Compte compte;
	ArrayList<LignePanier> lignesPanier = new ArrayList<LignePanier>();
	
	public double getPrixTotal(){
		double prixTotal=0;
		for (LignePanier ligneDuPanier : lignesPanier){
			prixTotal = prixTotal + (ligneDuPanier.getQuantite() * ligneDuPanier.getArticle().getPrixHt());
		}
		return prixTotal;
	}
	
	//sera appel�e par ajouterUnArticle
	private void ajouterLignePanier(Article a, int quantite) throws ExceptionQuantiteDemandeeSuperieureAuStock{
		//si quantit� demand�e inf�rieure ou �gale au stock
		if (quantite <= a.getStock()){
			lignesPanier.add(new LignePanier(a, quantite));
		} else { // si quantite demand�e sup�rieure au stock
			if (a.isDematerialise()){ // si d�materialise on s'en fiche c'est illimit�
				lignesPanier.add(new LignePanier(a, quantite));
			} else { // si mat�rialis� stock insuffisant
				throw new ExceptionQuantiteDemandeeSuperieureAuStock("Le stock n'est pas suffisant, nombre d'articles en stock : ", a.getStock());
				}//else
		}//if
	}// ajouterLignePanier
	
	public void ajouterUnArticle(Article a, int quantiteAjout�e) throws ExceptionQuantiteDemandeeSuperieureAuStock{
		LignePanier lp1 = new LignePanier(a, quantiteAjout�e);
		int indexDeMaLigne = lignesPanier.indexOf(lp1);
		// si la ligne article existe d�j�
		if (indexDeMaLigne != -1){
			// si la quantit� ajout�e <= stock de l'article
			if (quantiteAjout�e <= a.getStock()){
				lignesPanier.get(indexDeMaLigne).setQuantite(
						lignesPanier.get(indexDeMaLigne).getQuantite() + quantiteAjout�e);
			} else { // si quantite demand�e sup�rieure au stock
				 // si d�materialise on s'en fiche c'est illimit�
				if (a.isDematerialise()){
					lignesPanier.get(indexDeMaLigne).setQuantite(
							lignesPanier.get(indexDeMaLigne).getQuantite() + quantiteAjout�e);
				} else { // si mat�rialis� et stock insuffisant
					throw new ExceptionQuantiteDemandeeSuperieureAuStock(
							"Le stock n'est pas suffisant, nombre d'articles en stock : ", a.getStock());
					}//else
			}//if
		}//if
		// si la ligne article n'existe pas
		else { 
			this.ajouterLignePanier(a, quantiteAjout�e);
		}//else
	}//ajouterUnArticle
	
	// appel�e par retirer un article
	private void supprimerLignePanier(int i){
		lignesPanier.remove(i);
	}
	
	public void retirerUnArticle(Article a, int quantiteRetiree) throws ExceptionRetirerArticlePanier, ExceptionRetirerArticleAbsentDuPanier{
		LignePanier lp1 = new LignePanier(a, quantiteRetiree);
		int indexDeMaLigne = lignesPanier.indexOf(lp1);
		if (indexDeMaLigne != -1){
			if (quantiteRetiree == lignesPanier.get(indexDeMaLigne).getQuantite()){
				this.supprimerLignePanier(indexDeMaLigne);
			}
			else if (quantiteRetiree > lignesPanier.get(indexDeMaLigne).getQuantite()){
				throw new ExceptionRetirerArticlePanier(
					"Vous essayez de retirer trop d'exemplaires de cet article, quantit� dans le panier = ",
					lignesPanier.get(indexDeMaLigne).getQuantite());
			}
			else if (quantiteRetiree < lignesPanier.get(indexDeMaLigne).getQuantite()) {
				int nouvelleQuantite = lignesPanier.get(indexDeMaLigne).getQuantite() - quantiteRetiree;
				lignesPanier.get(indexDeMaLigne).setQuantite(nouvelleQuantite);	
			}
		} else {
			throw new ExceptionRetirerArticleAbsentDuPanier("Impossible de supprimer un exemplaire, cet article n'est pas pr�sent dans le panier");
			}
	

	}//retirerUnArticle
}// class
