package com.gestion.stock.dao;

import java.util.List;

import com.gestion.stock.entites.MvtStock;

public interface IMvtStockDAO extends IGenericDAO<MvtStock>{
	public List<MvtStock> getByIdArticle(Long idArticle);
	public MvtStock getByIdArticleObjet(Long idArticle);

}
