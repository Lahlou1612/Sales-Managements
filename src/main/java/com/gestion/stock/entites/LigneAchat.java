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
@Table(name = "ligneAchat")
public class LigneAchat implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long idLigneAchat;

	@ManyToOne
	@JoinColumn(name = "idArticle")
	private Article article;
	
	@ManyToOne
	@JoinColumn(name = "achat")
	private Achat achat;
	
	private BigDecimal prixUnitaireTTC;
	private BigDecimal quantite;

	public LigneAchat() {
		super();
	}

	public Long getIdLigneAchat() {
		return idLigneAchat;
	}

	public void setIdLigneAchat(Long idLigneAchat) {
		this.idLigneAchat = idLigneAchat;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
	@JsonIgnore
	public Achat getAchat() {
		return achat;
	}

	public void setAchat(Achat achat) {
		this.achat = achat;
	}

	public BigDecimal getPrixUnitaireTTC() {
		return prixUnitaireTTC;
	}

	public void setPrixUnitaireTTC(BigDecimal prixUnitaireTTC) {
		this.prixUnitaireTTC = prixUnitaireTTC;
	}

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}
	
}
