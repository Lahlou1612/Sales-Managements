package com.gestion.stock.dao.impl;

import java.util.List;

import javax.persistence.Query;

import com.gestion.stock.dao.IArticleDAO;
import com.gestion.stock.entites.Article;
import com.gestion.stock.entites.MvtStock;

public class ArticleDaoImpl extends GenericDaoImpl<Article> implements IArticleDAO{
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Article> selectedStockItems() {
		String queryString = "select distinct lc from "+ Article.class.getSimpleName()+" lc where lc.idArticle NOT IN "
						   + "(select lc from " +Article.class.getSimpleName()+" lc , "+MvtStock.class.getSimpleName()+" so where lc.idArticle = so.article.idArticle)";
		Query query = em.createQuery(queryString); 
		return query.getResultList();	
		}

}
