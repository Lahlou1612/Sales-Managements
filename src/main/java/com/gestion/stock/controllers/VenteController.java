package com.gestion.stock.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gestion.stock.entites.LigneVente;
import com.gestion.stock.entites.MvtStock;
import com.gestion.stock.entites.Vente;
import com.gestion.stock.export.IFileExporter;
import com.gestion.stock.services.ILigneVenteService;
import com.gestion.stock.services.IMvtStockService;
import com.gestion.stock.services.IVenteService;

@Controller
@RequestMapping("/vente")
public class VenteController {
	@Autowired
	private IVenteService venteService;
	@Autowired
	private ILigneVenteService venteLigneService;
	@Autowired
	private IMvtStockService stockService;
	@Autowired
	@Qualifier("venteExporter")
	private IFileExporter exporter;

	@RequestMapping("/")
	public String CommandeClientHome(Model model) {
		List<Vente> ventes = venteService.getByStatutVente("En cours");
		if (ventes == null) {
			ventes = new ArrayList<Vente>();
		} else {
			List<LigneVente> list = null;
			for (Vente vente : ventes) {
				list = venteLigneService.getByIdVente(vente.getIdVente());
				vente.setLigneVentes(list);
			}
		}
		model.addAttribute("ventes", ventes);
		return "vente/vente";
	}

	@RequestMapping("/listeVenteValider")
	public String venteValiderList(Model model) {
		List<Vente> ventesValider = venteService.getByStatutVente("Vente Validée");
		List<Vente> ventesRejeter = venteService.getByStatutVente("Vente Rejetée");
		if (ventesValider == null) {
			ventesValider = new ArrayList<Vente>();
		}
		if (ventesRejeter == null) {
			ventesRejeter = new ArrayList<Vente>();
		}
		model.addAttribute("ventesValider", ventesValider);
		model.addAttribute("ventesRejeter", ventesRejeter);
		return "vente/listevente";
	}

	@RequestMapping(value = "/getStock", produces = "application/json")
	@ResponseBody
	public List<MvtStock> getStockByIdArticle(final Long idVente) {
		List<MvtStock> liststocks = new ArrayList<MvtStock>();
		if (idVente == null) {
			return null;
		}
		List<LigneVente> listVentes = venteLigneService.getByIdVente(idVente);
		for (LigneVente ligneVente : listVentes) {
			liststocks.add(stockService.getByIdArticleObjet(ligneVente.getArticle().getIdArticle()));
		}
		return liststocks;
	}

	@RequestMapping(value = "/venteValider", produces = "application/json")
	@ResponseBody
	public String validerVente(final Long idVente) {
		if (idVente == null) {
			return null;
		}
		List<LigneVente> lignesVente = venteLigneService.getByIdVente(idVente);
		Vente ventes = venteService.getById(idVente);
		if (lignesVente == null) {
			lignesVente = new ArrayList<LigneVente>();
		}
		List<MvtStock> liststocks;
		for (LigneVente ligneVente : lignesVente) {
			liststocks = stockService.getByIdArticle(ligneVente.getArticle().getIdArticle());
			if (!liststocks.isEmpty() && liststocks != null) {
				for (MvtStock mvtStock : liststocks) {
					mvtStock.setDateMvt(new Date());
					mvtStock.setTypeMvt("Vente");
					mvtStock.setQuantite(mvtStock.getQuantite().subtract(ligneVente.getQuantite()));
					stockService.update(mvtStock);
				}
				ventes.setStatut("Vente Validée");
				venteService.update(ventes);
			}
		}
		return "redirect:/vente/";
	}

	@RequestMapping(value = "/venteRejeter", produces = "application/json")
	@ResponseBody
	public String rejeterVente(final Long idVente) {
		if (idVente == null) {
			return null;
		}
		List<LigneVente> lignesVente = venteLigneService.getByIdVente(idVente);
		Vente ventes = venteService.getById(idVente);
		if (lignesVente == null) {
			lignesVente = new ArrayList<LigneVente>();
		} else {
			ventes.setStatut("Vente Rejetée");
			venteService.update(ventes);
		}
//		List<MvtStock> liststocks;
//		for (LigneVente ligneVente : lignesVente) {
//			liststocks = stockService.getByIdArticle(ligneVente.getArticle().getIdArticle());
//			if(!liststocks.isEmpty() && liststocks != null) {
//				for (MvtStock mvtStock : liststocks) {
//					mvtStock.setDateMvt(new Date());
//					mvtStock.setTypeMvt("Vente");
//					mvtStock.setQuantite(mvtStock.getQuantite().add(ligneVente.getQuantite()));
//					stockService.update(mvtStock);
//				}
		// }
		// }
		return "redirect:/vente/";
	}

	@RequestMapping(value = "/export/")
	private String exporterArticles(HttpServletResponse response) {
		exporter.exportDataToExcel(response, null, null);

		return "vente/vente";
	}
}
