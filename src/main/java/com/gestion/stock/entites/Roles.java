package com.gestion.stock.entites;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity
public class Roles implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long idRole;
	private String roleName;
	
	@ManyToOne
	@JoinColumn(name = "idUtilisateur")
	private Utilisateur utilisateur;
	
	public Roles() {}

	public Long getIdRole() {
		return idRole;
	}

	public void setIdRole(Long idRole) {
		this.idRole = idRole;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@JsonIgnore
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public Roles(String roleName, Utilisateur user) {
		super();
		this.roleName = roleName;
		this.utilisateur= user;
	}
	
	
}
