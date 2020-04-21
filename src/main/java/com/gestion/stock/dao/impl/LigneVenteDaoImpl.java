package com.gestion.stock.dao.impl;

import java.util.List;

import javax.persistence.Query;

import com.gestion.stock.dao.ILigneVenteDAO;
import com.gestion.stock.entites.LigneVente;

public class LigneVenteDaoImpl extends GenericDaoImpl<LigneVente> implements ILigneVenteDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<LigneVente> getByIdVente(Long idVente) {
		String queryString = "select lc from "+ LigneVente.class.getSimpleName()+" lc where lc.vente.idVente = :x";
		Query query = em.createQuery(queryString); 
		query.setParameter("x", idVente);
		return query.getResultList();	
		}

}
