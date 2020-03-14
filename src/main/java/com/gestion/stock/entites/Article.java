package com.gestion.stock.entites;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "article")
public class Article implements Serializable {
	@Id
	@GeneratedValue
	private Long idArticle;
	@NotNull
    @Size(min = 1, message = "Veuillez saisir votre Code svp !")
	private String codeArticle;
	@NotNull
    @Size(min = 1, message = "Veuillez saisir votre Désignation svp !")
	private String designation;
//	@NotNull
//    @Size(min = 1, message = "Veuillez saisir le prix unitaire HT svp !")
	private BigDecimal prixUnitaireHT;
//	@NotNull
//    @Size(min = 1, message = "Veuillez saisir le taux TVA svp !")
	private BigDecimal tauxTva;
//	@NotNull
//    @Size(min = 1, message = "Veuillez saisir le prix unitaire TTC svp !")
	private BigDecimal prixUnitaireTTC;
	private String photo;

	@ManyToOne
	@JoinColumn(name = "idCategory")
	private Category category;

	public Article() {
		super();
	}

	public Long getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
	}

	public String getCodeArticle() {
		return codeArticle;
	}

	public void setCodeArticle(String codeArticle) {
		this.codeArticle = codeArticle;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public BigDecimal getPrixUnitaireHT() {
		return prixUnitaireHT;
	}

	public void setPrixUnitaireHT(BigDecimal prixUnitaireHT) {
		this.prixUnitaireHT = prixUnitaireHT;
	}

	public BigDecimal getTauxTva() {
		return tauxTva;
	}

	public void setTauxTva(BigDecimal tauxTva) {
		this.tauxTva = tauxTva;
	}

	public BigDecimal getPrixUnitaireTTC() {
		return prixUnitaireTTC;
	}

	public void setPrixUnitaireTTC(BigDecimal prixUnitaireTTC) {
		this.prixUnitaireTTC = prixUnitaireTTC;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
