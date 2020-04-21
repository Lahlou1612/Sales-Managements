package com.gestion.stock.dao.impl;

import java.util.List;

import javax.persistence.Query;

import com.gestion.stock.dao.IVenteDAO;
import com.gestion.stock.entites.Vente;

public class VenteDaoImpl extends GenericDaoImpl<Vente> implements IVenteDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Vente> getByStatutVente(String statut) {
		String queryString = "select lc from "+ Vente.class.getSimpleName()+" lc where lc.statut = :x";
		Query query = em.createQuery(queryString); 
		query.setParameter("x", statut);
		return query.getResultList();
	}

}
