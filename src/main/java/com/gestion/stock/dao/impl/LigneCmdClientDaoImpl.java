package com.gestion.stock.dao.impl;

import java.util.List;

import javax.persistence.Query;

import com.gestion.stock.dao.ILigneCmdClientDAO;
import com.gestion.stock.entites.LigneCmdClient;

public class LigneCmdClientDaoImpl extends GenericDaoImpl<LigneCmdClient> implements ILigneCmdClientDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<LigneCmdClient> getByIdCommande(Long idCommandeClient) {
		String queryString = "select lc from "+ LigneCmdClient.class.getSimpleName()+" lc where lc.commandeClient.idCommandeClient = :x";
		Query query = em.createQuery(queryString); 
		query.setParameter("x", idCommandeClient);
		return query.getResultList();
	}

}
