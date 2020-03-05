package com.gestion.stock.services;

import java.util.List;

import com.gestion.stock.entites.LigneCmdFournisseur;

public interface ILigneCmdFournisseurService {
	public LigneCmdFournisseur save(LigneCmdFournisseur entity);

	public LigneCmdFournisseur update(LigneCmdFournisseur entity);

	public List<LigneCmdFournisseur> selectAll();

	public LigneCmdFournisseur getById(Long id);

	public void remove(Long id);

	public List<LigneCmdFournisseur> selectAll(String sortField, String sort);

	public LigneCmdFournisseur findOne(String paramName, Object paramValue);

	public LigneCmdFournisseur findOne(String[] paramNames, Object[] paramValues);

	public int findCountBy(String paramName, String paramValue);
}
