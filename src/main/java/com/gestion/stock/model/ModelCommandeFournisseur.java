package com.gestion.stock.model;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.gestion.stock.entites.Article;
import com.gestion.stock.entites.CommandeFournisseur;
import com.gestion.stock.entites.LigneCmdFournisseur;
@Component
public class ModelCommandeFournisseur implements IModelCommandeFournisseur{
	private CommandeFournisseur commande;
	private Map<Long, LigneCmdFournisseur> ligneCmd= new Hashtable<Long, LigneCmdFournisseur>();
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
		commande = new CommandeFournisseur();
		commande.setDateCommande(new Date());
		commande.setCode(generateCodeCommande());
	}

	@Override
	public LigneCmdFournisseur ajouterLigneCommande(Article article) {
		if(article ==null)
		{
			return null;
		}
		LigneCmdFournisseur lc = ligneCmd.get(article.getIdArticle());
		if(lc != null)
		{
			//BigDecimal qte = lc.getQuantite().add(BigDecimal.ONE);
			lc.setQuantite(this.getQuantite());
			ligneCmd.put(article.getIdArticle(), lc);
			return lc;
		}
		else
		{
			LigneCmdFournisseur ligne = new LigneCmdFournisseur();
			ligne.setCommandeFournisseur(commande);
			ligne.setQuantite(this.getQuantite());
			ligne.setPrixUnitaire(article.getPrixUnitaireTTC());
			ligne.setArticle(article);
			ligneCmd.put(article.getIdArticle(), ligne);
			return ligne;
		}
	}

	@Override
	public LigneCmdFournisseur supprimerLigneCommande(Article article) {
		if(article == null) {return null;}
		return ligneCmd.remove(article.getIdArticle());
	}

	@Override
	public String generateCodeCommande() {
		return UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
	}

	@Override
	public CommandeFournisseur getCommande() {
		return commande;
	}

	@Override
	public Collection<LigneCmdFournisseur> getLignesCommandeFournisseur(CommandeFournisseur commande) {
		for (LigneCmdFournisseur ligneCmdFournisseur: ligneCmd.values()) {
			ligneCmdFournisseur.setCommandeFournisseur(commande);
		}
		return ligneCmd.values();
	}

	@Override
	public void init() {
		commande=null;
		ligneCmd.clear();		
	}
	public Map<Long, LigneCmdFournisseur> getLigneCmd() {
		return ligneCmd;
	}
	public void setLigneCmd(Map<Long, LigneCmdFournisseur> ligneCmd) {
		this.ligneCmd = ligneCmd;
	}
	public void setCommande(CommandeFournisseur commande) {
		this.commande = commande;
	}
	@Override
	public BigDecimal getTotalFournisseur() {
		BigDecimal totalCommande = BigDecimal.ZERO;
		if (!ligneCmd.isEmpty()) {
		 for (Map.Entry<Long, LigneCmdFournisseur > ligne : ligneCmd.entrySet())  {		
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


}
