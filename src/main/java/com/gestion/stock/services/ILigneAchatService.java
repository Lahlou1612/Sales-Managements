package com.gestion.stock.services;

import java.util.List;

import com.gestion.stock.entites.LigneAchat;

public interface ILigneAchatService {
	public LigneAchat save(LigneAchat entity);

	public LigneAchat update(LigneAchat entity);

	public List<LigneAchat> selectAll();

	public LigneAchat getById(Long id);

	public void remove(Long id);

	public List<LigneAchat> selectAll(String sortField, String sort);

	public LigneAchat findOne(String paramName, Object paramValue);

	public LigneAchat findOne(String[] paramNames, Object[] paramValues);

	public int findCountBy(String paramName, String paramValue);
	
	public List<LigneAchat> getByIdAchat(Long idAchat);
}
