package com.gestion.stock.entites;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "commandeFournisseur")
public class CommandeFournisseur implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long idCommandeFournisseur;
	private String code;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCommande;

	@ManyToOne
	@JoinColumn(name = "idFournisseur")
	private Fournisseur fournisseur;

	@OneToMany(mappedBy = "commandeFournisseur", cascade = CascadeType.ALL)
	private List<LigneCmdFournisseur> ligneCmdFournisseurs;
	
	@Transient
	private BigDecimal totalCommande;

	public CommandeFournisseur() {
		super();
	}

	public Long getIdCommandeFournisseur() {
		return idCommandeFournisseur;
	}

	public void setIdCommandeFournisseur(Long idCommandeFournisseur) {
		this.idCommandeFournisseur = idCommandeFournisseur;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Fournisseur getFournisseur() {
		return fournisseur;
	}

	public void setFournisseur(Fournisseur fournisseur) {
		this.fournisseur = fournisseur;
	}
	@JsonIgnore
	public List<LigneCmdFournisseur> getLigneCmdFournisseurs() {
		return ligneCmdFournisseurs;
	}

	public void setLigneCmdFournisseurs(List<LigneCmdFournisseur> ligneCmdFournisseurs) {
		this.ligneCmdFournisseurs = ligneCmdFournisseurs;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public BigDecimal getTotalCommande() {
		return totalCommande;
	}

	public void setTotalCommande(BigDecimal totalCommande) {
		this.totalCommande = totalCommande;
	}
	public BigDecimal getTotalFournisseur() {
		totalCommande = BigDecimal.ZERO;
		if (!ligneCmdFournisseurs.isEmpty()) {

			for (LigneCmdFournisseur cmdFournisseur: ligneCmdFournisseurs) {
				if (cmdFournisseur.getQuantite() != null && cmdFournisseur.getPrixUnitaire() != null) {
					BigDecimal totalLigne = cmdFournisseur.getQuantite().multiply(cmdFournisseur.getPrixUnitaire());
					totalCommande = totalCommande.add(totalLigne);
				}
			}
		}
		return totalCommande;
	}
	@Transient
	public String getLigneCommandeJson()
	{
		if(!ligneCmdFournisseurs.isEmpty())
		{ 
			try 
			{
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(ligneCmdFournisseurs);
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
