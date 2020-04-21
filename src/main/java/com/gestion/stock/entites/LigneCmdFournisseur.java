package com.gestion.stock.entites;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "ligneCmdFournisseur")
public class LigneCmdFournisseur implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long idLigneCmdFournisseur;

	@ManyToOne
	@JoinColumn(name = "idArticle")
	private Article article;

	@ManyToOne
	@JoinColumn(name = "idCommandeFournisseur")
	private CommandeFournisseur commandeFournisseur;

	private BigDecimal quantite;

	private BigDecimal prixUnitaire;

	public LigneCmdFournisseur() {
		super();
	}

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	public BigDecimal getPrixUnitaire() {
		return prixUnitaire;
	}

	public void setPrixUnitaire(BigDecimal prixUnitaire) {
		this.prixUnitaire = prixUnitaire;
	}
	
	public Long getIdLigneCmdFournisseur() {
		return idLigneCmdFournisseur;
	}

	public void setIdLigneCmdFournisseur(Long idLigneCmdFournisseur) {
		this.idLigneCmdFournisseur = idLigneCmdFournisseur;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	@JsonIgnore
	public CommandeFournisseur getCommandeFournisseur() {
		return commandeFournisseur;
	}

	public void setCommandeFournisseur(CommandeFournisseur commandeFournisseur) {
		this.commandeFournisseur = commandeFournisseur;
	}
}
