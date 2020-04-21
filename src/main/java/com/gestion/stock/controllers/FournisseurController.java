package com.gestion.stock.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gestion.stock.entites.*;
import com.gestion.stock.export.IFileExporter;
import com.gestion.stock.services.*;

@Controller
@RequestMapping(value = "/fournisseur")
public class FournisseurController {
	@Autowired
	private IFournisseurService fournisseurService;
	@Autowired
	@Qualifier("fournisseurExporter")
	private IFileExporter exporter;
	// Redirection vers la page gestion des Fournisseur.
		@RequestMapping(value = "/")
		public String Fournisseur(Model model) {
			List<Fournisseur> fournisseurs = fournisseurService.selectAll();
			if (fournisseurs == null) {
				fournisseurs = new ArrayList<Fournisseur>();
			}
			model.addAttribute("fournisseurs", fournisseurs);
			return "fournisseur/fournisseur";
		}

		// Redirection vers la page ajouter un nouveau Fournisseur.
		@RequestMapping(value = "/nouveau", method = RequestMethod.GET)
		public String ajouterFournisseur(Model model) {
			Fournisseur fournisseur= new Fournisseur();
			model.addAttribute("fournisseur", fournisseur);
			return "fournisseur/ajouterFournisseur";
		}

		// Enregister/Modifier un nouveau Fournisseur
		@RequestMapping(value = "/enregistrer")
		public String enregistrerFournisseur(Model model, @ModelAttribute("fournisseur") @Valid Fournisseur fournisseurs, BindingResult result) {
			if (result.hasErrors()) {
		        return "fournisseur/ajouterFournisseur";
		    }
			if (fournisseurs != null) {
				if (fournisseurs.getIdFournisseur() != null) {
					fournisseurService.update(fournisseurs);
				} else {
					fournisseurService.save(fournisseurs);
				}
			}
			return "redirect:/fournisseur/";
		}

		// URL de redirection vers la modification
		@RequestMapping(value = "/modifier/{idFournisseur}")
		public String modifierFournisseur(Model model, @PathVariable Long idFournisseur) {
			if (idFournisseur != null) {
				Fournisseur fournisseurs= fournisseurService.getById(idFournisseur);
				if (fournisseurs != null) {
					model.addAttribute("fournisseur", fournisseurs);
				}
			}
			return "fournisseur/ajouterFournisseur";
		}

		// URL de redirection vers la Suppression
		@RequestMapping(value = "/supprimer/{idFournisseur}")
		public String supprimerFournisseur(Model model, @PathVariable Long idFournisseur) {
			if (idFournisseur!= null) {
				Fournisseur fournisseurs= fournisseurService.getById(idFournisseur);
				if (fournisseurs != null) {
					fournisseurService.remove(idFournisseur);
				}
			}
			return "redirect:/fournisseur/";
		}
		
		@RequestMapping(value = "/export/")
		private String exporterArticles(HttpServletResponse response) {
			exporter.exportDataToExcel(response, null, null);
			
			return "fournisseur/fournisseur";
		}

}
