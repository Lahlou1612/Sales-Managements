package com.gestion.stock.dao.impl;

import java.util.List;

import javax.persistence.Query;

import com.gestion.stock.dao.IRoleDAO;
import com.gestion.stock.entites.Roles;

public class RoleDAOImpl extends GenericDaoImpl<Roles> implements IRoleDAO{
	@SuppressWarnings("unchecked")
	@Override
	public List<Roles> getByIdRole(Long idUtilisateur) {
		String queryString = "select lc from "+ Roles.class.getSimpleName()+" lc where lc.utilisateur.idUtilisateur = :x";
		Query query = em.createQuery(queryString); 
		query.setParameter("x", idUtilisateur);
		return query.getResultList();
	}

//	@Override
//	public Roles getByIdUtilisateur(Long idUtilisateur) {
//		String queryString = "select lc from "+ Roles.class.getSimpleName()+" lc where lc.utilisateur.idUtilisateur = :x";
//		Query query = em.createQuery(queryString); 
//		query.setParameter("x", idUtilisateur);
//		Roles role = query.getResultList();
//		return 
//	}

}
