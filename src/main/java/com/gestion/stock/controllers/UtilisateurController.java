package com.gestion.stock.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.gestion.stock.entites.*;
import com.gestion.stock.services.*;

@Controller
@RequestMapping(value = "/utilisateur")
public class UtilisateurController {
	@Autowired
	private IUtilisateurService utilisateurService;
	@Autowired
	private IRoleService rolesService;

	@RequestMapping(value = "/")
	public String Utilisateur(Model model) {
		List<Utilisateur> users = utilisateurService.selectAll();
		if (users == null) {
			users = new ArrayList<Utilisateur>();
		} else {
			for (Utilisateur utilisateur : users) {
				List<Roles> roles = rolesService.getByIdRole(utilisateur.getIdUtilisateur());
				utilisateur.setRoles(roles);
			}
		}
		model.addAttribute("users", users);
		return "utilisateur/utilisateur";
	}

	// Redirection vers la page ajouter un nouveau Utilisateur.
	@RequestMapping(value = "/nouveau", method = RequestMethod.GET)
	public String ajouterUtilisateur(Model model) {
		Utilisateur user = new Utilisateur();
		List<Roles> roles = rolesService.selectAll();
		if (roles == null) {
			roles = new ArrayList<Roles>();
		}
		model.addAttribute("roles", roles);
		model.addAttribute("utilisateur", user);
		return "utilisateur/ajouterUtilisateur";
	}

	// Enregister/Modifier un nouvel Utilisateur
	@RequestMapping(value = "/enregistrer")
	public String enregistrerUtilisateur(Model model, @ModelAttribute("utilisateur") @Valid Utilisateur user,
			BindingResult result) {
		if (result.hasErrors()) {
			return "utilisateur/ajouterUtilisateur";
		}
		if (user != null) {
			if (user.getIdUtilisateur() != null) {
				utilisateurService.update(user);
				List<Roles> roles = rolesService.getByIdRole(user.getIdUtilisateur());
				for (Roles role : roles) {
					role.setRoleName(user.getRoleName());
					rolesService.update(role);
				}
			} else {
				utilisateurService.save(user);
				Roles roles = new Roles(user.getRoleName(), user);
				if (roles != null) {
					rolesService.save(roles);
				}
			}
		}
		return "redirect:/utilisateur/";
	}

	// URL de redirection vers la Suppression
	@RequestMapping(value = "/supprimer/{idUtilisateur}")
	public String supprimerUtilisateur(Model model, @PathVariable Long idUtilisateur) {
		if (idUtilisateur != null) {
			Utilisateur article = utilisateurService.getById(idUtilisateur);
			if (article != null) {
				utilisateurService.remove(idUtilisateur);
			}
		}
		return "redirect:/utilisateur/";
	}

	// URL de redirection vers la modification
	@RequestMapping(value = "/modifier/{idUtilisateur}")
	public String modifierUtilisateur(Model model, @PathVariable Long idUtilisateur) {
		if (idUtilisateur != null) {
			Utilisateur user = utilisateurService.getById(idUtilisateur);
			if (user != null) {
				model.addAttribute("utilisateur", user);
			}
			List<Roles> roles = rolesService.selectAll();
			if (roles == null) {
				roles = new ArrayList<Roles>();
			}
			model.addAttribute("roles", roles);
		}
		return "utilisateur/ajouterUtilisateur";
	}
}
