package com.gestion.stock.dao;

import java.util.List;

import com.gestion.stock.entites.Article;

public interface IArticleDAO extends IGenericDAO<Article>{
	public List<Article> selectedStockItems();
}
