package com.gestion.stock.services;

import java.util.List;

import com.gestion.stock.entites.CommandeFournisseur;

public interface ICommandeFournisseurService {
	public CommandeFournisseur save(CommandeFournisseur entity);

	public CommandeFournisseur update(CommandeFournisseur entity);

	public List<CommandeFournisseur> selectAll();

	public CommandeFournisseur getById(Long id);

	public void remove(Long id);

	public List<CommandeFournisseur> selectAll(String sortField, String sort);

	public CommandeFournisseur findOne(String paramName, Object paramValue);

	public CommandeFournisseur findOne(String[] paramNames, Object[] paramValues);

	public int findCountBy(String paramName, String paramValue);
}
