package com.gestion.stock.services;

import java.util.List;

import com.gestion.stock.entites.Utilisateur;

public interface IUtilisateurService {
	public Utilisateur save(Utilisateur entity);

	public Utilisateur update(Utilisateur entity);

	public List<Utilisateur> selectAll();

	public Utilisateur getById(Long id);

	public void remove(Long id);

	public List<Utilisateur> selectAll(String sortField, String sort);

	public Utilisateur findOne(String paramName, Object paramValue);

	public Utilisateur findOne(String[] paramNames, Object[] paramValues);

	public int findCountBy(String paramName, String paramValue);
}
