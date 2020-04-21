package com.gestion.stock.dao;

import java.util.List;

import com.gestion.stock.entites.Roles;

public interface IRoleDAO extends IGenericDAO<Roles>{
	public List<Roles> getByIdRole(Long idUtilisateur);
//	public Roles getByIdUtilisateur(Long idUtilisateur);

}
