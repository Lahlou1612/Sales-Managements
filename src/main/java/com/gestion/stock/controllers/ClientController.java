package com.gestion.stock.controllers;

import java.util.*;

import javax.validation.Valid;

import com.gestion.stock.entites.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gestion.stock.services.IClientService;


@Controller
@RequestMapping(value = "/client")
public class ClientController {

	@Autowired
	private IClientService clientService;

	// Redirection vers la page gestion des Clients.
	@RequestMapping(value = "/")
	public String Client(Model model) {
		List<Client> clients = clientService.selectAll();
		if (clients == null) {
			System.out.println("OK");
			clients = new ArrayList<Client>();
		}
		model.addAttribute("clients", clients);
		return "client/client";
	}

	// Redirection vers la page ajouter un nouveau Client.
	@RequestMapping(value = "/nouveau", method = RequestMethod.GET)
	public String ajouterClient(Model model) {
		Client client = new Client();
		model.addAttribute("client", client);
		return "client/ajouterClient";
	}

	// Enregister/Modifier un nouvel Client
	@RequestMapping(value = "/enregistrer")
	public String enregistrerClient(Model model, @ModelAttribute("client") @Valid Client client, BindingResult result) {
		if (result.hasErrors()) {
	        return "client/ajouterClient";
	    }
		if (client != null) {
			if (client.getIdClient() != null) {
				clientService.update(client);
			} else {
				clientService.save(client);
			}
		}
		return "redirect:/client/";
	}

	// URL de redirection vers la modification
	@RequestMapping(value = "/modifier/{idClient}")
	public String modifierClient(Model model, @PathVariable Long idClient) {
		if (idClient != null) {
			Client client = clientService.getById(idClient);
			if (client != null) {
				model.addAttribute("client", client);
			}
		}
		return "client/ajouterClient";
	}

	// URL de redirection vers la Suppression
	@RequestMapping(value = "/supprimer/{idClient}")
	public String supprimerClient(Model model, @PathVariable Long idClient) {
		if (idClient != null) {
			Client client = clientService.getById(idClient);
			if (client != null) {
				clientService.remove(idClient);
			}
		}
		return "redirect:/client/";
	}
}
