//package com.gestion.stock.controllers;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import com.gestion.stock.entites.*;
//import com.gestion.stock.services.*;
//
//
//@Controller
//@RequestMapping(value = "/utilisateur")
//public class UtilisateurController {
//
//	@Autowired
//	private IUtilisateurService utilisateurService;
//	@Autowired
//	private ICategoryService categoryService;
//
//	// Redirection vers la page gestion des Utilisateurs.
//	@RequestMapping(value = "/")
//	public String Utilisateur(Model model) {
//		List<Utilisateur> article = articleService.selectAll();
//		if (article == null) {
//			article = new ArrayList<Utilisateur>();
//		}
//		model.addAttribute("articles", article);
//		return "article/article";
//	}
//
//	// Redirection vers la page ajouter un nouveau Utilisateur.
//	@RequestMapping(value = "/nouveau", method = RequestMethod.GET)
//	public String ajouterUtilisateur(Model model) {
//		Utilisateur article = new Utilisateur();
//		List<Category> categories = categoryService.selectAll();
//		if(categories == null)
//		{
//			categories = new ArrayList<Category>();
//		}
//		model.addAttribute("categories", categories);
//		model.addAttribute("article", article);
//		return "article/ajouterUtilisateur";
//	}
//
//	// Enregister/Modifier un nouvel Utilisateur
//	@RequestMapping(value = "/enregistrer")
//	public String enregistrerUtilisateur(Model model, @ModelAttribute("article") @Valid Utilisateur article, BindingResult result) {
//		if (result.hasErrors()) {
//	        return "article/ajouterUtilisateur";
//	    }
//		if (article != null) {
//			if (article.getIdUtilisateur() != null) {
//				articleService.update(article);
//			} else {
//				articleService.save(article);
//			}
//		}
//		return "redirect:/article/";
//	}
//
//	// URL de redirection vers la modification
//	@RequestMapping(value = "/modifier/{idUtilisateur}")
//	public String modifierUtilisateur(Model model, @PathVariable Long idUtilisateur) {
//		if (idUtilisateur != null) {
//			Utilisateur article = articleService.getById(idUtilisateur);
//			if (article != null) {
//				model.addAttribute("article", article);
//			}
//			List<Category> categories = categoryService.selectAll();
//			if(categories == null)
//			{
//				categories = new ArrayList<Category>();
//			}
//			model.addAttribute("categories", categories);
//		}
//		return "article/ajouterUtilisateur";
//	}
//
//	// URL de redirection vers la Suppression
//	@RequestMapping(value = "/supprimer/{idUtilisateur}")
//	public String supprimerUtilisateur(Model model, @PathVariable Long idUtilisateur) {
//		if (idUtilisateur != null) {
//			Utilisateur article = articleService.getById(idUtilisateur);
//			if (article != null) {
//				articleService.remove(idUtilisateur);
//			}
//		}
//		return "redirect:/article/";
//	}
//
//}
