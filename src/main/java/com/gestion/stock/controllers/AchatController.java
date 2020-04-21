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

import com.gestion.stock.entites.Achat;
import com.gestion.stock.entites.LigneAchat;
import com.gestion.stock.entites.MvtStock;
import com.gestion.stock.export.IFileExporter;
import com.gestion.stock.services.IAchatService;
import com.gestion.stock.services.ILigneAchatService;
import com.gestion.stock.services.IMvtStockService;

@Controller
@RequestMapping("/achat")
public class AchatController {
	@Autowired
	private IAchatService achatService;
	@Autowired
	private ILigneAchatService achatLigneService;
	@Autowired
	private IMvtStockService stockService;
	@Autowired
	@Qualifier("achatExporter")
	private IFileExporter exporter;
	@RequestMapping("/")
	public String CommandeFournisseurHome(Model model) {
		List<Achat> achats = achatService.getByStatutAchat("En cours");
		if (achats == null) {
			achats = new ArrayList<Achat>();
		} else {
			List<LigneAchat> list = null;
			for (Achat achat : achats) {
				list = achatLigneService.getByIdAchat(achat.getIdAchat());
				achat.setLigneAchats(list);
			}
		}
		model.addAttribute("achats", achats);
		return "achat/achat";
	}	
	
	@RequestMapping("/listeAchatValider")
	public String achatValiderList(Model model) {
		List<Achat> achatsValider = achatService.getByStatutAchat("Achat Validé");
		List<Achat> achatsRejeter = achatService.getByStatutAchat("Achat Rejeté");
		if (achatsValider == null) {
			achatsValider = new ArrayList<Achat>();
		} 
		if (achatsRejeter == null) {
			achatsRejeter = new ArrayList<Achat>();
		} 
		model.addAttribute("achatsValider", achatsValider);
		model.addAttribute("achatsRejeter", achatsRejeter);
		return "achat/listeachat";
	}	
	
	@RequestMapping(value = "/getStock" , produces = "application/json")
	@ResponseBody
	public List<MvtStock> getStockByIdArticle(final Long idAchat) {
		List<MvtStock> liststocks=new ArrayList<MvtStock>();
		if(idAchat == null) { return null; }
		List<LigneAchat> listAchats = achatLigneService.getByIdAchat(idAchat);
		for (LigneAchat ligneAchat : listAchats) {
			 liststocks.add(stockService.getByIdArticleObjet(ligneAchat.getArticle().getIdArticle()));
		}
		return liststocks;
	}
	
	@RequestMapping(value = "/achatValider" , produces = "application/json")
	@ResponseBody
	public String validerAchat(final Long idAchat) {
		if(idAchat == null) { return null; }
		List<LigneAchat> lignesAchat = achatLigneService.getByIdAchat(idAchat);
		Achat achats= achatService.getById(idAchat);
		if(lignesAchat == null) { lignesAchat = new ArrayList<LigneAchat>(); }
		List<MvtStock> liststocks;
		for (LigneAchat ligneAchat : lignesAchat) {
			liststocks = stockService.getByIdArticle(ligneAchat.getArticle().getIdArticle());
			if(!liststocks.isEmpty() && liststocks != null) {
				for (MvtStock mvtStock : liststocks) {
					mvtStock.setDateMvt(new Date());
					mvtStock.setTypeMvt("Achat");
					mvtStock.setQuantite(mvtStock.getQuantite().add(ligneAchat.getQuantite()));
					stockService.update(mvtStock);
				}
				achats.setStatut("Achat Validé");
				achatService.update(achats);
			}
		}
		return "redirect:/achat/";
	}
	
	@RequestMapping(value = "/achatRejeter" , produces = "application/json")
	@ResponseBody
	public String rejeterAchat(final Long idAchat) {
		if(idAchat == null) { return null; }
		List<LigneAchat> lignesAchat = achatLigneService.getByIdAchat(idAchat);
		Achat achats= achatService.getById(idAchat);
		if(lignesAchat == null) { lignesAchat = new ArrayList<LigneAchat>(); }
		else {
			achats.setStatut("Achat Rejeté");
			achatService.update(achats);
		}
		return "redirect:/achat/";
	}
	
	@RequestMapping(value = "/export/")
	private String exporterArticles(HttpServletResponse response) {
		exporter.exportDataToExcel(response, null, null);
		
		return "achat/achat";
	}

}
