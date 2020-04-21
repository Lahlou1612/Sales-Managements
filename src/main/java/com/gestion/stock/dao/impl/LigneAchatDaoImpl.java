package com.gestion.stock.dao.impl;

import java.util.List;

import javax.persistence.Query;

import com.gestion.stock.dao.ILigneAchatDAO;
import com.gestion.stock.entites.LigneAchat;

public class LigneAchatDaoImpl extends GenericDaoImpl<LigneAchat> implements ILigneAchatDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<LigneAchat> getByIdAchat(Long idAchat) {
		String queryString = "select lc from "+ LigneAchat.class.getSimpleName()+" lc where lc.achat.idAchat = :x";
		Query query = em.createQuery(queryString); 
		query.setParameter("x", idAchat);
		return query.getResultList();	
	}

}
