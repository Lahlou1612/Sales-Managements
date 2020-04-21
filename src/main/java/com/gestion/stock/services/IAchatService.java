package com.gestion.stock.services;

import java.util.List;

import com.gestion.stock.entites.Achat;

public interface IAchatService {
	public Achat save(Achat entity);

	public Achat update(Achat entity);

	public List<Achat> selectAll();

	public Achat getById(Long id);

	public void remove(Long id);

	public List<Achat> selectAll(String sortField, String sort);

	public Achat findOne(String paramName, Object paramValue);

	public Achat findOne(String[] paramNames, Object[] paramValues);

	public int findCountBy(String paramName, String paramValue);
	
	public List<Achat> getByStatutAchat(String statut);
}
