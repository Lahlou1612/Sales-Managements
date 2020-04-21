package com.gestion.stock.dao.impl;

import java.util.List;

import javax.persistence.Query;

import com.gestion.stock.dao.ILigneCmdFournisseurDAO;
import com.gestion.stock.entites.LigneCmdFournisseur;

public class LigneCmdFournisseurDaoImpl extends GenericDaoImpl<LigneCmdFournisseur> implements ILigneCmdFournisseurDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<LigneCmdFournisseur> getByIdCommande(Long idCommandeFournisseur) {
		String queryString = "select lc from "+ LigneCmdFournisseur.class.getSimpleName()+" lc where lc.commandeFournisseur.idCommandeFournisseur = :x";
		Query query = em.createQuery(queryString); 
		query.setParameter("x", idCommandeFournisseur);
		return query.getResultList();
	}

}
