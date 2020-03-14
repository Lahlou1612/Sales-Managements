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

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {

	@Id
	@GeneratedValue
	private Long idUtilisateur;
	@NotNull
    @Size(min = 1, message = "Veuillez saisir le Nom svp !")
	private String nom;
	@NotNull
    @Size(min = 1, message = "Veuillez saisir le Prénom svp !")
	private String prenom;
	@NotNull
    @Size(min = 1, message = "Veuillez saisir le Mail svp !")
	private String mail;
	@NotNull
    @Size(min = 1, message = "Veuillez saisir le Mot de passe svp !")
	private String motDePasse;
	private String photo;
	private boolean actived;
	
	@OneToMany(mappedBy = "utilisateur")
	private List<Roles> roles;

	public Utilisateur() {
		super();
	}

	public Long getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(Long idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public boolean isActived() {
		return actived;
	}

	public void setActived(boolean actived) {
		this.actived = actived;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
}
