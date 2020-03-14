package com.gestion.stock.dao;

import java.util.List;

import com.gestion.stock.entites.LigneCmdClient;

public interface ILigneCmdClientDAO extends IGenericDAO<LigneCmdClient>{
	public List<LigneCmdClient> getByIdCommande(Long idCommandeClient);
}
