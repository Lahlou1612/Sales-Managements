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
@Table(name = "commandeClient")
public class CommandeClient implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	private Long idCommandeClient;
	private String code;

	@Temporal(TemporalType.TIMESTAMP)
	private Date dateCommande;

	@ManyToOne
	@JoinColumn(name = "idClient")
	private Client client;

	@OneToMany(mappedBy = "commandeClient", cascade = CascadeType.ALL)
	private List<LigneCmdClient> ligneCmdClients;

	@Transient
	private BigDecimal totalCommande;

	public CommandeClient() {
		super();
	}

	public Long getIdCommandeClient() {
		return idCommandeClient;
	}

	public void setIdCommandeClient(Long idCommandeClient) {
		this.idCommandeClient = idCommandeClient;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getDateCommande() {
		return dateCommande;
	}

	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	@JsonIgnore
	public List<LigneCmdClient> getLigneCmdClients() {
		return ligneCmdClients;
	}

	public void setLigneCmdClients(List<LigneCmdClient> ligneCmdClients) {
		this.ligneCmdClients = ligneCmdClients;
	}

	public BigDecimal getTotalClient() {
		totalCommande = BigDecimal.ZERO;
		if (!ligneCmdClients.isEmpty()) {

			for (LigneCmdClient cmdClient : ligneCmdClients) {
				if (cmdClient.getQuantite() != null && cmdClient.getPrixUnitaire() != null) {
					BigDecimal totalLigne = cmdClient.getQuantite().multiply(cmdClient.getPrixUnitaire());
					totalCommande = totalCommande.add(totalLigne);
				}
			}
		} else {
			return null;
		}
		return totalCommande;
	}

	@Transient
	public String getLigneCommandeJson()
	{
		if(!ligneCmdClients.isEmpty())
		{ 
			try 
			{
				ObjectMapper mapper = new ObjectMapper();
				return mapper.writeValueAsString(ligneCmdClients);
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
