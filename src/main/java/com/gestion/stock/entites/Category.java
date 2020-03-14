package com.gestion.stock.entites;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
@Table(name = "category")
public class Category implements Serializable {

	@Id
	@GeneratedValue
	private Long idCategory;
	
	@NotNull
    @Size(min = 1, message = "Veuillez saisir votre Code svp !")
	private String code;
	
	@NotNull(message = "Veuillez saisir votre Désignation svp !")
    @Size(min = 1, max = 100, message = "La désignation doit contenir entre 1 et 200 caractères !")
	private String designation;

	@OneToMany(mappedBy = "category")
	private List<Article> articles;

	public Category() {
		super();
	}

	public Long getIdCategory() {
		return idCategory;
	}

	public void setIdCategory(Long idCategory) {
		this.idCategory = idCategory;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	@JsonIgnore
	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

}
