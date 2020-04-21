package com.gestion.stock.dao.impl;

import java.util.List;

import javax.persistence.Query;

import com.gestion.stock.dao.IMvtStockDAO;
import com.gestion.stock.entites.MvtStock;

public class MvtStockDaoImpl extends GenericDaoImpl<MvtStock> implements IMvtStockDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<MvtStock> getByIdArticle(Long idArticle) {
		String queryString = "select lc from "+ MvtStock.class.getSimpleName()+" lc where lc.article.idArticle = :x";
		Query query = em.createQuery(queryString); 
		query.setParameter("x", idArticle);
		return query.getResultList();	
	}

	@Override
	public MvtStock getByIdArticleObjet(Long idArticle) {
		String queryString = "select lc from "+ MvtStock.class.getSimpleName()+" lc where lc.article.idArticle = :x";
		Query query = em.createQuery(queryString); 
		query.setParameter("x", idArticle);
		@SuppressWarnings("unchecked")
		List<MvtStock> stock = query.getResultList();
		MvtStock stockArticle = null;
		for (MvtStock mvtStock : stock) {
			 stockArticle=new MvtStock(mvtStock.getDateMvt(), mvtStock.getQuantite(), mvtStock.getTypeMvt(), mvtStock.getArticle());
		}
		return stockArticle;
	}

}
