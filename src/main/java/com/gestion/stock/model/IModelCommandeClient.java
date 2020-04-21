package com.gestion.stock.model;

import java.math.BigDecimal;
import java.util.Collection;


import com.gestion.stock.entites.Article;
import com.gestion.stock.entites.CommandeClient;
import com.gestion.stock.entites.LigneCmdClient;

public interface IModelCommandeClient {
	void creerCommande();
	//void modifierCommande(Client client);
	LigneCmdClient ajouterLigneCommande(Article article);
	LigneCmdClient supprimerLigneCommande(Article article);
	//LigneCmdClient modifierLigneCommande(Article article, double qte);
	String 	generateCodeCommande();
	CommandeClient getCommande();
	//Map<Long, LigneCmdClient> getLigneCmd();
	Collection<LigneCmdClient> getLignesCommandeClient(CommandeClient commande);
	void init();
	public BigDecimal getTotalClient();
	public BigDecimal getQuantite();
	public void setQuantite(BigDecimal quantite);
}
