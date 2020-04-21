package com.gestion.stock.model;

import java.math.BigDecimal;
import java.util.Collection;

import com.gestion.stock.entites.Article;
import com.gestion.stock.entites.CommandeFournisseur;
import com.gestion.stock.entites.LigneCmdFournisseur;

public interface IModelCommandeFournisseur {
	void creerCommande();
	LigneCmdFournisseur ajouterLigneCommande(Article article);
	LigneCmdFournisseur supprimerLigneCommande(Article article);
	String 	generateCodeCommande();
	CommandeFournisseur getCommande();
	Collection<LigneCmdFournisseur> getLignesCommandeFournisseur(CommandeFournisseur commande);
	void init();
	public BigDecimal getQuantite();
	public void setQuantite(BigDecimal quantite);
	public BigDecimal getTotalFournisseur();
}
