package com.gestion.stock.services;

import java.util.List;

import com.gestion.stock.entites.Roles;

public interface IRoleService {
	public Roles save(Roles entity);

	public Roles update(Roles entity);

	public List<Roles> selectAll();

	public Roles getById(Long id);

	public void remove(Long id);

	public List<Roles> selectAll(String sortField, String sort);

	public Roles findOne(String paramName, Object paramValue);

	public Roles findOne(String[] paramNames, Object[] paramValues);

	public int findCountBy(String paramName, String paramValue);
	
	public List<Roles> getByIdRole(Long idUtilisateur);

}
