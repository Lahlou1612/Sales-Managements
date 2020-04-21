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
import com.gestion.stock.entites.Article;
import com.gestion.stock.entites.MvtStock;
import com.gestion.stock.export.IFileExporter;
import com.gestion.stock.services.IArticleService;
import com.gestion.stock.services.IMvtStockService;

@Controller
@RequestMapping(value = "/stock")
public class StockController {
	@Autowired
	private IArticleService articleService;
	@Autowired
	private IMvtStockService stockService;
	@Autowired
	@Qualifier("stockExporter")
	private IFileExporter exporter;
	// Redirection vers la page gestion des Articles.
	@RequestMapping(value = "/")
	public String Stock(Model model) {
		List<MvtStock> stock = stockService.selectAll();
		if (stock == null) {
			stock = new ArrayList<MvtStock>();
		}
		model.addAttribute("stocks", stock);
		return "stock/stock";
	}

	// Redirection vers la page ajouter un nouveau Article.
	@RequestMapping(value = "/nouveau", method = RequestMethod.GET)
	public String ajouterStock(Model model) {
		MvtStock stock = new MvtStock();
		List<Article> articles = articleService.selectedStockItems();
		if (articles == null) {
			articles = new ArrayList<Article>();
		}
		model.addAttribute("articles", articles);
		model.addAttribute("stock", stock);
		return "stock/ajouterStock";
	}

	// Enregister/Modifier un nouvel Article
	@RequestMapping(value = "/enregistrer")
	public String enregistrerStock(Model model, @ModelAttribute("stock") @Valid MvtStock stock, BindingResult result) {
		if (result.hasErrors()) {
			return "stock/ajouterStock";
		}
		MvtStock stocks = stockService.getByIdArticleObjet(stock.getArticle().getIdArticle());
		if (stock != null) {
			if (stock.getIdMvtStock() != null) {
				stocks.setQuantite(stocks.getQuantite().add(stocks.getQuantite()));
				stockService.update(stock);
			} else if (stocks != null) {
				stock.setIdMvtStock(stock.getIdMvtStock());
				stock.setQuantite(stock.getQuantite().add(stocks.getQuantite()));
				stockService.update(stocks);
			} else {
				stockService.save(stock);
			}
		}
		return "redirect:/stock/";
	}

	// URL de redirection vers la modification
	@RequestMapping(value = "/modifier/{idMvtStock}")
	public String modifierStock(Model model, @PathVariable Long idMvtStock) {
		if (idMvtStock != null) {
			MvtStock stock = stockService.getById(idMvtStock);
			if (stock != null) {
				model.addAttribute("stock", stock);
			}
			Article article = articleService.getById(stock.getArticle().getIdArticle());
			if (article == null) {
				article = new Article();
			}
			List<Article> articles = new ArrayList<Article>();
			articles.add(article);
			model.addAttribute("articles", articles);
		}
		return "stock/ajouterStock";
	}

	// URL de redirection vers la Suppression
	@RequestMapping(value = "/supprimer/{idMvtStock}")
	public String supprimerStock(Model model, @PathVariable Long idMvtStock) {
		if (idMvtStock != null) {
			MvtStock stock = stockService.getById(idMvtStock);
			if (stock != null) {
				stockService.remove(idMvtStock);
			}
		}
		return "redirect:/stock/";
	}

	@RequestMapping(value = "/export/")
	private String exporterArticles(HttpServletResponse response) {
		exporter.exportDataToExcel(response, null, null);

		return "stock/stock";
	}

}
