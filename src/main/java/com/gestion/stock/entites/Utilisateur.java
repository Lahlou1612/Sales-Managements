package com.gestion.stock.entites;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@Entity
@Table(name = "utilisateur")
public class Utilisateur implements Serializable {
	private static final long serialVersionUID = 1L;
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
	
	private String roleName;
	
	@OneToMany(mappedBy = "utilisateur", cascade = CascadeType.ALL)
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
	@JsonIgnore
	public List<Roles> getRoles() {
		return roles;
	}
	
//	public String getRolesName() {
//		String name="";
//		for (Roles roles : getRoles()) {
//			name = roles.getRoleName();
//		}
//		return name;
//	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}
	
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	@Transient
	public String getRoleUtilisateurJson()
	{
		if(!roles.isEmpty())
		{ 
			try 
			{
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(roles);
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}
}
