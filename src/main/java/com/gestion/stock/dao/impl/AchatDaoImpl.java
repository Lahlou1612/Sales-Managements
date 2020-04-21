package com.gestion.stock.dao.impl;

import java.util.List;

import javax.persistence.Query;

import com.gestion.stock.dao.IAchatDAO;
import com.gestion.stock.entites.Achat;

public class AchatDaoImpl extends GenericDaoImpl<Achat> implements IAchatDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<Achat> getByStatutAchat(String statut) {
		String queryString = "select lc from "+ Achat.class.getSimpleName()+" lc where lc.statut = :x";
		Query query = em.createQuery(queryString); 
		query.setParameter("x", statut);
		return query.getResultList();
	}

}
