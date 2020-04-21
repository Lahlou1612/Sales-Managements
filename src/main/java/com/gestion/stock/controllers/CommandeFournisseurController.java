package com.gestion.stock.controllers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gestion.stock.entites.Achat;
import com.gestion.stock.entites.Article;
import com.gestion.stock.entites.Client;
import com.gestion.stock.entites.CommandeClient;
import com.gestion.stock.entites.CommandeFournisseur;
import com.gestion.stock.entites.Fournisseur;
import com.gestion.stock.entites.LigneAchat;
import com.gestion.stock.entites.LigneCmdClient;
import com.gestion.stock.entites.LigneCmdFournisseur;
import com.gestion.stock.entites.LigneVente;
import com.gestion.stock.entites.Vente;
import com.gestion.stock.export.IFileExporter;
import com.gestion.stock.model.IModelCommandeClient;
import com.gestion.stock.model.IModelCommandeFournisseur;
import com.gestion.stock.services.IAchatService;
import com.gestion.stock.services.IArticleService;
import com.gestion.stock.services.IClientService;
import com.gestion.stock.services.ICommandeClientService;
import com.gestion.stock.services.ICommandeFournisseurService;
import com.gestion.stock.services.IFournisseurService;
import com.gestion.stock.services.ILigneAchatService;
import com.gestion.stock.services.ILigneCmdClientService;
import com.gestion.stock.services.ILigneCmdFournisseurService;
import com.gestion.stock.services.ILigneVenteService;
import com.gestion.stock.services.IVenteService;

@Controller
@RequestMapping("/commandefournisseur")
@SuppressWarnings("unused")
public class CommandeFournisseurController {
	@Autowired
	private ICommandeFournisseurService commandeService;
	@Autowired
	private IFournisseurService fournisseurService;
	@Autowired
	private IArticleService articleService;
	@Autowired
	private ILigneCmdFournisseurService ligneCommandeService;
	@Autowired
	private IModelCommandeFournisseur modelCommande;
	@Autowired
	private IAchatService achatService;
	@Autowired
	private ILigneAchatService achatLigneService;
	Long id;
	@Autowired
	@Qualifier("commandeFournisseurExporter")
	private IFileExporter exporter;

	@RequestMapping("/")
	public String CommandeFournisseurHome(Model model) {
		List<CommandeFournisseur> commandesFournisseur = commandeService.selectAll();
		if (commandesFournisseur.isEmpty()) {
			commandesFournisseur = new ArrayList<CommandeFournisseur>();
		} else {
			for (CommandeFournisseur commandeFournisseur : commandesFournisseur) {
				List<LigneCmdFournisseur> ligneCmdFrn = ligneCommandeService
						.getByIdCommande(commandeFournisseur.getIdCommandeFournisseur());
				commandeFournisseur.setLigneCmdFournisseurs(ligneCmdFrn);
			}
		}
		model.addAttribute("commandeFournisseur", commandesFournisseur);
		return "fournisseur/commandefournisseur/commandefournisseur";
	}
	//Lors le chargement de la page
	@RequestMapping(value = "/nouveau")
	public String nouvelleCommande(Model model) {
		List<Fournisseur> fournisseurs = fournisseurService.selectAll();
		List<Article> articles= articleService.selectAll();
		if (fournisseurs.isEmpty() && articles.isEmpty()) {
			fournisseurs = new ArrayList<Fournisseur>();
			articles = new ArrayList<Article>();
		}
		modelCommande.creerCommande();
		model.addAttribute("code", modelCommande.getCommande().getCode());
		model.addAttribute("dateCmd", modelCommande.getCommande().getDateCommande());
		model.addAttribute("fournisseurs", fournisseurs);
		model.addAttribute("articles", articles);
		return "fournisseur/commandefournisseur/nouvellecommande";
	}
	//lors le changement sur le SELECT
	@RequestMapping(value = "/creerCommande")
	@ResponseBody
	public Long creerCommande(final Long idFournisseur) {
		if (idFournisseur == null) {
			return null;
		}
		Fournisseur fournisseur = fournisseurService.getById(idFournisseur);
		if (fournisseur == null) {
			return null;
		}
		id = fournisseur.getIdFournisseur();
		return id;
	}
	//lors de la recherche d'Article
	@RequestMapping(value = "/ajouterLigne")
	@ResponseBody
	public LigneCmdFournisseur getArticleById(final Long codeArticle, BigDecimal quantite) {
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
	public LigneCmdFournisseur supprimerLigneCommande(final Long idArticle) {
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
		CommandeFournisseur nouvelleCommande = null;
		Achat achat = null;
		if (modelCommande.getCommande() != null) {
			Fournisseur fournisseur = fournisseurService.getById(id);
			modelCommande.getCommande().setFournisseur(fournisseur);
			nouvelleCommande = commandeService.save(modelCommande.getCommande());
			achat = new Achat();
			achat.setCode(achat.generateCodeCommande());
			achat.setDateAchat(modelCommande.getCommande().getDateCommande());
			achat.setNomFournisseur(modelCommande.getCommande().getFournisseur().getNom());
			achat.setIdCommande(modelCommande.getCommande().getIdCommandeFournisseur());
			achat.setStatut("En cours");
			achat.setMontantGlobal(modelCommande.getTotalFournisseur());
			achatService.save(achat);
		}
		if (nouvelleCommande != null) {
			Collection<LigneCmdFournisseur> ligneCommandes = modelCommande.getLignesCommandeFournisseur(nouvelleCommande);
			if (ligneCommandes != null && !ligneCommandes.isEmpty()) {
				for (LigneCmdFournisseur  ligneCmdFournisseur : ligneCommandes) {
					ligneCommandeService.save(ligneCmdFournisseur);
					LigneAchat ligneAchat = new LigneAchat();
					ligneAchat.setArticle(ligneCmdFournisseur.getArticle());
					ligneAchat.setQuantite(ligneCmdFournisseur.getQuantite());
					ligneAchat.setPrixUnitaireTTC(ligneCmdFournisseur.getPrixUnitaire());
					ligneAchat.setAchat(achat);
					achatLigneService.save(ligneAchat);
				}
				modelCommande.init();
			}
		}
		return "redirect:/commandefournisseur/";
	}


	// URL de redirection vers la Suppression
	@RequestMapping(value = "/supprimer/{idCommandeFournisseur}")
	public String supprimerCommandeFournisseur(Model model, @PathVariable Long idCommandeFournisseur) {
		if (idCommandeFournisseur != null) {
			CommandeFournisseur cmdFournisseur= commandeService.getById(idCommandeFournisseur);
			if (cmdFournisseur != null) {
				commandeService.remove(idCommandeFournisseur);
			}
		}
		return "redirect:/commandefournisseur/";
	}
	
	@RequestMapping(value = "/export/")
	private String exporterArticles(HttpServletResponse response) {
		exporter.exportDataToExcel(response, null, null);
		
		return "fournisseur/commandefournisseur/commandefournisseur";
	}
}
