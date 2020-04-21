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
@Table(name = "vente")
public class Vente implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long idVente;

	private String code;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateVente;

	@OneToMany(mappedBy = "vente", cascade = CascadeType.ALL)
	private List<LigneVente> ligneVentes;
	
	private String nomClient;
	
	private Long idCommande;
	
	private String statut;
	
	private BigDecimal montantGlobal;
	

	public Vente() {
		super();
	}

	public Long getIdVente() {
		return idVente;
	}

	public void setIdVente(Long idVente) {
		this.idVente = idVente;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDateVente() {
		return dateVente;
	}

	public void setDateVente(Date dateVente) {
		this.dateVente = dateVente;
	}
	@JsonIgnore
	public List<LigneVente> getLigneVentes() {
		return ligneVentes;
	}

	public void setLigneVentes(List<LigneVente> ligneVentes) {
		this.ligneVentes = ligneVentes;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
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
	public String getLigneVenteJson()
	{
		if(!ligneVentes.isEmpty())
		{ 
			try 
			{
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(ligneVentes);
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
