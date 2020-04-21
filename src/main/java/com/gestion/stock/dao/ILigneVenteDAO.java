package com.gestion.stock.dao;

import java.util.List;

import com.gestion.stock.entites.LigneVente;

public interface ILigneVenteDAO extends IGenericDAO<LigneVente>{
	public List<LigneVente> getByIdVente(Long idVente);
}
