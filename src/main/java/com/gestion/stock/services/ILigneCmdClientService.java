package com.gestion.stock.services;

import java.util.List;

import com.gestion.stock.entites.LigneCmdClient;

public interface ILigneCmdClientService {
	public LigneCmdClient save(LigneCmdClient entity);

	public LigneCmdClient update(LigneCmdClient entity);

	public List<LigneCmdClient> selectAll();

	public LigneCmdClient getById(Long id);

	public void remove(Long id);

	public List<LigneCmdClient> selectAll(String sortField, String sort);

	public LigneCmdClient findOne(String paramName, Object paramValue);

	public LigneCmdClient findOne(String[] paramNames, Object[] paramValues);

	public int findCountBy(String paramName, String paramValue);
}
