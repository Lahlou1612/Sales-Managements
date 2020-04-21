package com.gestion.stock.dao;

import java.util.List;

import com.gestion.stock.entites.Vente;

public interface IVenteDAO extends IGenericDAO<Vente>{
	public List<Vente> getByStatutVente(String statut);

}
