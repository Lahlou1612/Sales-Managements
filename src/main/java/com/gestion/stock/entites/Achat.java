package com.gestion.stock.entites;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@Entity
@Table(name = "achat")
public class Achat implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long idAchat;

	private String code;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateAchat;

	@OneToMany(mappedBy = "achat", cascade = CascadeType.ALL)
	private List<LigneAchat> ligneAchats;
	
	private String nomFournisseur;
	
	private Long idCommande;
	
	private String statut;
	
	private BigDecimal montantGlobal;
	
	public Achat() {
		super();
	}

	public Long getIdAchat() {
		return idAchat;
	}

	public void setIdAchat(Long idAchat) {
		this.idAchat = idAchat;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDateAchat() {
		return dateAchat;
	}

	public void setDateAchat(Date dateAchat) {
		this.dateAchat = dateAchat;
	}
	
	@JsonIgnore
	public List<LigneAchat> getLigneAchats() {
		return ligneAchats;
	}

	public void setLigneAchats(List<LigneAchat> ligneAchats) {
		this.ligneAchats = ligneAchats;
	}

	public String getNomFournisseur() {
		return nomFournisseur;
	}

	public void setNomFournisseur(String nomFournisseur) {
		this.nomFournisseur = nomFournisseur;
	}

	public Long getIdCommande() {
		return idCommande;
	}

	public void setIdCommande(Long idCommande) {
		this.idCommande = idCommande;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public BigDecimal getMontantGlobal() {
		return montantGlobal;
	}

	public void setMontantGlobal(BigDecimal montantGlobal) {
		this.montantGlobal = montantGlobal;
	}
	public static String generateCodeCommande() {
		return UUID.randomUUID().toString().replaceAll("-","").toUpperCase();
	}
	@Transient
	public String getLigneAchatJson()
	{
		if(!ligneAchats.isEmpty())
		{ 
			try 
			{
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(ligneAchats);
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
