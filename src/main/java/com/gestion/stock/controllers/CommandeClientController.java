package com.gestion.stock.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.WebSecurity.IgnoredRequestConfigurer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gestion.stock.entites.Article;
import com.gestion.stock.entites.Client;
import com.gestion.stock.entites.CommandeClient;
import com.gestion.stock.entites.LigneCmdClient;
import com.gestion.stock.model.IModelCommandeClient;
import com.gestion.stock.services.IArticleService;
import com.gestion.stock.services.IClientService;
import com.gestion.stock.services.ICommandeClientService;
import com.gestion.stock.services.ILigneCmdClientService;

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
	
	@RequestMapping("/")
	public String CommandeClientHome(Model model)
	{
		List<CommandeClient> commandesClient = commandeService.selectAll();
		if(commandesClient.isEmpty())
		{
			commandesClient = new ArrayList<CommandeClient>();
		}
		else
		{
			for (CommandeClient commandeClient : commandesClient) {
				List<LigneCmdClient> ligneCmdClt =  ligneCommandeService.getByIdCommande(commandeClient.getIdCommandeClient());
				commandeClient.setLigneCmdClients(ligneCmdClt);
			}
		}
		model.addAttribute("commandeClient", commandesClient);
		return "client/commandeclient/commandeclient";
	}
	
	@RequestMapping(value = "/nouveau")
	public String nouvelleCommande(Model model)
	{
		List<Client> clients= clientService.selectAll();
		if(clients.isEmpty())
		{
			clients= new ArrayList<Client>();
		}
		model.addAttribute("codeCde",modelCommande.generateCodeCommande());
		model.addAttribute("dateCde", new Date());
		model.addAttribute("clients",clients);
		return "client/commandeclient/nouvellecommande";
	}
	
	@RequestMapping(value = "/detailArticle")
	@ResponseBody
	public Article getArticleById(final Long codeArticle)
	{
		if(codeArticle == null) { return null; }
		Article article = articleService.findOne("codeArticle",""+codeArticle);
		if(article == null) { return null; }
		return article;
	}

}
