package com.gestion.stock.services;

import java.util.List;

import com.gestion.stock.entites.Vente;

public interface IVenteService {
	public Vente save(Vente entity);

	public Vente update(Vente entity);

	public List<Vente> selectAll();

	public Vente getById(Long id);

	public void remove(Long id);

	public List<Vente> selectAll(String sortField, String sort);

	public Vente findOne(String paramName, Object paramValue);

	public Vente findOne(String[] paramNames, Object[] paramValues);

	public int findCountBy(String paramName, String paramValue);
}
