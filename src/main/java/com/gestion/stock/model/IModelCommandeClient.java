package com.gestion.stock.model;

import com.gestion.stock.entites.Article;
import com.gestion.stock.entites.Client;
import com.gestion.stock.entites.LigneCmdClient;

public interface IModelCommandeClient {
	void creerCommande(Client client);
	LigneCmdClient ajouterLigneCommande(Article article);
	LigneCmdClient supprimerLigneCommande(Article article);
	LigneCmdClient modifierLigneCommande(Article article, double qte);
	String 	generateCodeCommande();
}
