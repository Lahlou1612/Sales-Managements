package com.gestion.stock.controllers;

import java.io.Console;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.config.annotation.web.builders.WebSecurity.IgnoredRequestConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gestion.stock.entites.Article;
import com.gestion.stock.entites.Client;
import com.gestion.stock.entites.CommandeClient;
import com.gestion.stock.entites.LigneCmdClient;
import com.gestion.stock.entites.LigneVente;
import com.gestion.stock.entites.Vente;
import com.gestion.stock.export.IFileExporter;
import com.gestion.stock.model.IModelCommandeClient;
import com.gestion.stock.services.IArticleService;
import com.gestion.stock.services.IClientService;
import com.gestion.stock.services.ICommandeClientService;
import com.gestion.stock.services.ILigneCmdClientService;
import com.gestion.stock.services.ILigneVenteService;
import com.gestion.stock.services.IVenteService;

@Controller
@RequestMapping("/commandeclient")
@SuppressWarnings("unused")
public class CommandeClientController {
	@Autowired
	private ICommandeClientService commandeService;
	@Autowired
	private IClientService clientService;
	@Autowired
	private IArticleService articleService;
	@Autowired
	private ILigneCmdClientService ligneCommandeService;
	@Autowired
	private IModelCommandeClient modelCommande;
	@Autowired
	private IVenteService venteService;
	@Autowired
	private ILigneVenteService venteLigneService;
	@Autowired
	@Qualifier("commandeClientExporter")
	private IFileExporter exporter;
	Long id;

	@RequestMapping("/")
	public String CommandeClientHome(Model model) {
		List<CommandeClient> commandesClient = commandeService.selectAll();
		if (commandesClient.isEmpty()) {
			commandesClient = new ArrayList<CommandeClient>();
		} else {
			for (CommandeClient commandeClient : commandesClient) {
				List<LigneCmdClient> ligneCmdClt = ligneCommandeService
						.getByIdCommande(commandeClient.getIdCommandeClient());
				commandeClient.setLigneCmdClients(ligneCmdClt);
			}
		}
		model.addAttribute("commandeClient", commandesClient);
		return "client/commandeclient/commandeclient";
	}
	//Lors le chargement de la page
	@RequestMapping(value = "/nouveau")
	public String nouvelleCommande(Model model) {
		List<Client> clients = clientService.selectAll();
		List<Article> articles= articleService.selectAll();
		if (clients.isEmpty() && articles.isEmpty()) {
			clients = new ArrayList<Client>();
			articles = new ArrayList<Article>();
		}
		modelCommande.creerCommande();
		model.addAttribute("code", modelCommande.getCommande().getCode());
		model.addAttribute("dateCmd", modelCommande.getCommande().getDateCommande());
		model.addAttribute("clients", clients);
		model.addAttribute("articles", articles);
		return "client/commandeclient/nouvellecommande";
	}
	//lors le changement sur le SELECT
	@RequestMapping(value = "/creerCommande")
	@ResponseBody
	public Long creerCommande(final Long idClient) {
		if (idClient == null) {
			return null;
		}
		Client client = clientService.getById(idClient);
		if (client == null) {
			return null;
		}
		id = client.getIdClient();
		return id;
	}
	//lors de la recherche d'Article
	@RequestMapping(value = "/ajouterLigne")
	@ResponseBody
	public LigneCmdClient getArticleById(final Long codeArticle, BigDecimal quantite) {
		if (codeArticle == null) {
			return null;
		}
		Article article = articleService.findOne("codeArticle", "" + codeArticle);
		if (article == null) {
			return null;
		}
		modelCommande.setQuantite(quantite);
		return modelCommande.ajouterLigneCommande(article);
	}

	@RequestMapping(value = "/supprimerLigne")
	@ResponseBody
	public LigneCmdClient supprimerLigneCommande(final Long idArticle) {
		if (idArticle == null) {
			return null;
		}
		Article article = articleService.getById(idArticle);
		if (article == null) {
			return null;
		}
		return modelCommande.supprimerLigneCommande(article);
	}

	@SuppressWarnings("static-access")
	@RequestMapping(value = "/enregistrerCommande", produces = "application/json")
	@ResponseBody
	public String enregistrerCommande() {
		CommandeClient nouvelleCommande = null;
		Vente vente = null;
		if (modelCommande.getCommande() != null) {
			Client client = clientService.getById(id);
			modelCommande.getCommande().setClient(client);
			nouvelleCommande = commandeService.save(modelCommande.getCommande());
			vente = new Vente();
			vente.setCode(vente.generateCodeCommande());
			vente.setDateVente(modelCommande.getCommande().getDateCommande());
			vente.setNomClient(modelCommande.getCommande().getClient().getNom());
			vente.setIdCommande(modelCommande.getCommande().getIdCommandeClient());
			vente.setStatut("En cours");
			vente.setMontantGlobal(modelCommande.getTotalClient());
			venteService.save(vente);
		}
		if (nouvelleCommande != null) {
			Collection<LigneCmdClient> ligneCommandes = modelCommande.getLignesCommandeClient(nouvelleCommande);
			if (ligneCommandes != null && !ligneCommandes.isEmpty()) {
				for (LigneCmdClient ligneCmdClient : ligneCommandes) {
					ligneCommandeService.save(ligneCmdClient);
					LigneVente ligneVente = new LigneVente();
					ligneVente.setArticle(ligneCmdClient.getArticle());
					ligneVente.setQuantite(ligneCmdClient.getQuantite());
					ligneVente.setPrixUnitaireTTC(ligneCmdClient.getPrixUnitaire());
					ligneVente.setVente(vente);
					venteLigneService.save(ligneVente);
				}
				modelCommande.init();
			}
		}
		return "redirect:/commandeclient/";
	}


	// URL de redirection vers la Suppression
	@RequestMapping(value = "/supprimer/{idCommandeClient}")
	public String supprimerCommandeClient(Model model, @PathVariable Long idCommandeClient ) {
		if (idCommandeClient != null) {
			CommandeClient cmdClient= commandeService.getById(idCommandeClient);
			if (cmdClient != null) {
				commandeService.remove(idCommandeClient);
			}
		}
		return "redirect:/commandeclient/";
	}
	@RequestMapping(value = "/export/")
	private String exporterArticles(HttpServletResponse response) {
		exporter.exportDataToExcel(response, null, null);
		
		return "client/commandeclient/commandeclient";
	}
}
