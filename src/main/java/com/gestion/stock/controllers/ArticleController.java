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
@RequestMapping(value = "/article")
public class ArticleController {
	
	@Autowired
	private IArticleService articleService;
	@Autowired
	private ICategoryService categoryService;

	// Redirection vers la page gestion des Articles.
	@RequestMapping(value = "/")
	public String Article(Model model) {
		List<Article> article = articleService.selectAll();
		if (article == null) {
			article = new ArrayList<Article>();
		}
		model.addAttribute("articles", article);
		return "article/article";
	}

	// Redirection vers la page ajouter un nouveau Article.
	@RequestMapping(value = "/nouveau", method = RequestMethod.GET)
	public String ajouterArticle(Model model) {
		Article article = new Article();
		List<Category> categories = categoryService.selectAll();
		if(categories == null)
		{
			categories = new ArrayList<Category>();
		}
		model.addAttribute("categories", categories);
		model.addAttribute("article", article);
		return "article/ajouterArticle";
	}

	// Enregister/Modifier un nouvel Article
	@RequestMapping(value = "/enregistrer")
	public String enregistrerArticle(Model model, @ModelAttribute("article") @Valid Article article, BindingResult result) {
		if (result.hasErrors()) {
	        return "article/ajouterArticle";
	    }
		if (article != null) {
			if (article.getIdArticle() != null) {
				articleService.update(article);
			} else {
				articleService.save(article);
			}
		}
		return "redirect:/article/";
	}

	// URL de redirection vers la modification
	@RequestMapping(value = "/modifier/{idArticle}")
	public String modifierArticle(Model model, @PathVariable Long idArticle) {
		if (idArticle != null) {
			Article article = articleService.getById(idArticle);
			if (article != null) {
				model.addAttribute("article", article);
			}
			List<Category> categories = categoryService.selectAll();
			if(categories == null)
			{
				categories = new ArrayList<Category>();
			}
			model.addAttribute("categories", categories);
		}
		return "article/ajouterArticle";
	}

	// URL de redirection vers la Suppression
	@RequestMapping(value = "/supprimer/{idArticle}")
	public String supprimerArticle(Model model, @PathVariable Long idArticle) {
		if (idArticle != null) {
			Article article = articleService.getById(idArticle);
			if (article != null) {
				articleService.remove(idArticle);
			}
		}
		return "redirect:/article/";
	}
}
