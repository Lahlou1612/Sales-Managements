package com.gestion.stock.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.gestion.stock.entites.Article;
import com.gestion.stock.entites.Client;
import com.gestion.stock.entites.CommandeClient;
import com.gestion.stock.entites.LigneCmdClient;

@Component
public class ModelCommandeClientImpl implements IModelCommandeClient{
	
	private CommandeClient commande;
	private Map<Long, LigneCmdClient> ligneCmd= new Hashtable<Long, LigneCmdClient>();

	@Override
	public void creerCommande(Client client) {
		if(client==null)
		{return;}
		commande = new CommandeClient();
		commande.setClient(client);
		commande.setDateCommande(new Date());
		commande.setCode(generateCodeCommande());
	}

	@Override
	public LigneCmdClient ajouterLigneCommande(Article article) {
		if(article ==null)
		{
			return null;
		}
		LigneCmdClient lc = ligneCmd.get(article.getIdArticle());
		if(lc != null)
		{
			BigDecimal qte = lc.getQuantite().add(BigDecimal.ONE);
			lc.setQuantite(qte);
			ligneCmd.put(article.getIdArticle(), lc);
		}
		else
		{
			LigneCmdClient ligne = new LigneCmdClient();
			ligne.setCommandeClient(commande);
			ligne.setQuantite(BigDecimal.ONE);
			ligne.setPrixUnitaire(article.getPrixUnitaireTTC());
			ligne.setArticle(article);
			ligneCmd.put(article.getIdArticle(), ligne);
			return ligne;
		}
		return null;
	}

	@Override
	public LigneCmdClient supprimerLigneCommande(Article article) {
		if(article == null) {return null;}
		return ligneCmd.remove(article.getIdArticle());
	}

	@Override
	public LigneCmdClient modifierLigneCommande(Article article, double qte) {
		if(article == null) {return null;}
		LigneCmdClient lc = ligneCmd.get(article.getIdArticle());
		if(lc == null) {return null;}
		lc.setQuantite(BigDecimal.valueOf(qte));
		return lc;
	}

	@Override
	public String generateCodeCommande() {
		return UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
	}

	public CommandeClient getCommande() {
		return commande;
	}

	public void setCommande(CommandeClient commande) {
		this.commande = commande;
	}

	public Map<Long, LigneCmdClient> getLigneCmd() {
		return ligneCmd;
	}

	public void setLigneCmd(Map<Long, LigneCmdClient> ligneCmd) {
		this.ligneCmd = ligneCmd;
	}
	

}