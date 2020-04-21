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
import com.gestion.stock.services.ICategoryService;

@Controller
@RequestMapping(value = "/category")
public class CategoryController {

	@Autowired
	private ICategoryService categoryService;
	@Autowired
	@Qualifier("categoryExporter")
	private IFileExporter exporter;

	// Redirection vers la page gestion des Categories.
	@RequestMapping(value = "/")
	public String Category(Model model) {
		List<Category> categorie = categoryService.selectAll();
		if (categorie == null) {
			categorie = new ArrayList<Category>();
		}
		model.addAttribute("categorie", categorie);
		return "category/category";
	}

	// Redirection vers la page ajouter un nouveau Client.
	@RequestMapping(value = "/nouveau", method = RequestMethod.GET)
	public String ajouterCategory(Model model) {
		Category categorie = new Category();
		model.addAttribute("category", categorie);
		return "category/ajouterCategory";
	}

	// Enregister/Modifier un nouvel Client
	@RequestMapping(value = "/enregistrer")
	public String enregistrerCategory(Model model, @ModelAttribute("category") @Valid Category categorie,
			BindingResult result) {
		if (result.hasErrors()) {
			return "category/ajouterCategory";
		}
		if (categorie != null) {
			if (categorie.getIdCategory() != null) {
				categoryService.update(categorie);
			} else {
				categoryService.save(categorie);
			}
		}
		return "redirect:/category/";
	}

	// URL de redirection vers la modification
	@RequestMapping(value = "/modifier/{idCategory}")
	public String modifierCategory(Model model, @PathVariable Long idCategory) {
		if (idCategory != null) {
			Category categorie = categoryService.getById(idCategory);
			if (categorie != null) {
				model.addAttribute("category", categorie);
			}
		}
		return "category/ajouterCategory";
	}

	// URL de redirection vers la Suppression
	@RequestMapping(value = "/supprimer/{idCategory}")
	public String supprimerCategory(Model model, @PathVariable Long idCategory) {
		if (idCategory != null) {
			Category categorie = categoryService.getById(idCategory);
			if (categorie != null) {
				categoryService.remove(idCategory);
			}
		}
		return "redirect:/category/";
	}

	@RequestMapping(value = "/export/")
	private String exporterArticles(HttpServletResponse response) {
		exporter.exportDataToExcel(response, null, null);
		return "category/category";
	}
}
