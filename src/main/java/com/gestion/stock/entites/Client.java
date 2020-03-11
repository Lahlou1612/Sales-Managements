package com.gestion.stock.entites;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "client")
public class Client implements Serializable {
	@Id
	@GeneratedValue
	private Long idClient;
	

	@NotNull
    @Size(min = 1, message = "Veuillez saisir votre Nom svp !")
	private String nom;
	
	@NotNull
    @Size(min = 1, message = "Veuillez saisir votre Prénom svp !")
	private String prenom;
	
	@NotNull(message = "Veuillez saisir votre Adresse svp !")
    @Size(min = 1, max = 100, message = "Adresse doit contenir entre 1 et 200 caractères !")
	private String adresse;
	
	private String photo;

	@NotNull(message = "Veuillez saisir votre Adresse Mail svp !")
    @Email(message="Veuillez re-vérifier votre Adresse Mail Svp !")
	private String mail;

	@OneToMany(mappedBy = "client")
	private List<CommandeClient> commandeClients;

	public Client() {
		super();
	}

	public Long getIdClient() {
		return idClient;
	}

	public void setIdClient(Long idClient) {
		this.idClient = idClient;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public List<CommandeClient> getCommandeClients() {
		return commandeClients;
	}

	public void setCommandeClients(List<CommandeClient> commandeClients) {
		this.commandeClients = commandeClients;
	}

}
