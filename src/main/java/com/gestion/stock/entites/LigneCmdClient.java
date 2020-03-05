package com.gestion.stock.entites;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ligneCmdClient")
public class LigneCmdClient implements Serializable {
	@Id
	@GeneratedValue
	private Long idLigneCmdClient;

	@ManyToOne
	@JoinColumn(name = "idArticle")
	private Article article;

	@ManyToOne
	@JoinColumn(name = "idCommandeClient")
	private CommandeClient commandeClient;

	public LigneCmdClient() {
		super();
	}

	public Long getIdLigneCmdClient() {
		return idLigneCmdClient;
	}

	public void setIdLigneCmdClient(Long idLigneCmdClient) {
		this.idLigneCmdClient = idLigneCmdClient;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public CommandeClient getCommandeClient() {
		return commandeClient;
	}

	public void setCommandeClient(CommandeClient commandeClient) {
		this.commandeClient = commandeClient;
	}
}
