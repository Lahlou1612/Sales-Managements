package com.gestion.stock.entites;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "mvtStock")
public class MvtStock implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int ENTREE = 1;
	public static final int SORTIE = 2;

	@Id
	@GeneratedValue
	private Long idMvtStock;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateMvt;

	private BigDecimal quantite;

	private String typeMvt;

	@ManyToOne
	@JoinColumn(name = "idArticle")
	private Article article;

	
	
	
	public MvtStock(Date dateMvt, BigDecimal quantite, String typeMvt, Article article) {
		super();
		this.dateMvt = dateMvt;
		this.quantite = quantite;
		this.typeMvt = typeMvt;
		this.article = article;
	}

	public MvtStock() {
		super();
	}

	public Long getIdMvtStock() {
		return idMvtStock;
	}

	public void setIdMvtStock(Long idMvtStock) {
		this.idMvtStock = idMvtStock;
	}

	public Date getDateMvt() {
		return dateMvt;
	}

	public void setDateMvt(Date dateMvt) {
		this.dateMvt = dateMvt;
	}

	public BigDecimal getQuantite() {
		return quantite;
	}

	public void setQuantite(BigDecimal quantite) {
		this.quantite = quantite;
	}

	public String getTypeMvt() {
		return typeMvt;
	}

	public void setTypeMvt(String typeMvt) {
		this.typeMvt = typeMvt;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}
}
