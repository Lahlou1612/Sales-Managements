package com.gestion.stock.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.gestion.stock.entites.Article;
import com.gestion.stock.entites.CommandeClient;
import com.gestion.stock.entites.LigneCmdClient;

@Component
public class ModelCommandeClientImpl implements IModelCommandeClient{
	private CommandeClient commande;
	private Map<Long, LigneCmdClient> ligneCmd= new Hashtable<Long, LigneCmdClient>();
	private static BigDecimal quantite;
	@Override
	public BigDecimal getQuantite() {
		return quantite;
	}
	
	@SuppressWarnings("static-access")
	@Override
	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	@Override
	public void creerCommande() {
		commande = new CommandeClient();
		commande.setDateCommande(new Date());
		commande.setCode(generateCodeCommande());
	}
	
	@Override
	public Collection<LigneCmdClient> getLignesCommandeClient(CommandeClient commande) {
		for (LigneCmdClient ligneCmdClient: ligneCmd.values()) {
			ligneCmdClient.setCommandeClient(commande);
		}
		return ligneCmd.values();
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
			//BigDecimal qte = lc.getQuantite().add(BigDecimal.ONE);
			lc.setQuantite(this.getQuantite());
     		ligneCmd.put(article.getIdArticle(), lc);
			return lc;
		}
		else
		{
			LigneCmdClient ligne = new LigneCmdClient();
			ligne.setCommandeClient(commande);
			ligne.setQuantite(this.getQuantite());
			ligne.setPrixUnitaire(article.getPrixUnitaireTTC());
			ligne.setArticle(article);
			ligneCmd.put(article.getIdArticle(), ligne);
			return ligne;
		}
	}

	@Override
	public LigneCmdClient supprimerLigneCommande(Article article) {
		if(article == null) {return null;}
		return ligneCmd.remove(article.getIdArticle());
	}

	@Override
	public String generateCodeCommande() {
		return UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
	}

	@Override
	public CommandeClient getCommande() {
		return commande;
	}

	public void setCommande(CommandeClient commande) {
		this.commande = commande;
	}
	
	public void setLigneCmd(Map<Long, LigneCmdClient> ligneCmd) {
		this.ligneCmd = ligneCmd;
	}

	@Override
	public void init() {
		commande=null;
		ligneCmd.clear();
	}
	@Override
	public BigDecimal getTotalClient() {
		BigDecimal totalCommande = BigDecimal.ZERO;
		if (!ligneCmd.isEmpty()) {
		 for (Map.Entry<Long, LigneCmdClient > ligne : ligneCmd.entrySet())  {		
				if (ligne.getValue().getQuantite() != null && ligne.getValue().getPrixUnitaire() != null) {
					BigDecimal totalLigne = ligne.getValue().getQuantite().multiply(ligne.getValue().getPrixUnitaire());
					totalCommande = totalCommande.add(totalLigne);
				}
			}
		} else {
			return null;
		}
		return totalCommande;
	}

//	@Override
//	public LigneCmdClient modifierLigneCommande(Article article, double qte) {
//		if(article == null) {return null;}
//		LigneCmdClient lc = ligneCmd.get(article.getIdArticle());
//		if(lc == null) {return null;}
//		lc.setQuantite(BigDecimal.valueOf(qte));
//		return lc;
//	}	
//	@Override
//	public Map<Long, LigneCmdClient> getLigneCmd() {
//		return ligneCmd;
//	}	
//	
//	@Override
//	public void modifierCommande(Client client) {
//		if(client== null) { return; }
//		if(commande != null) {
//			commande.setClient(client);
//		}
//	}
}