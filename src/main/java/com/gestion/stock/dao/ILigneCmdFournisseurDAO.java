package com.gestion.stock.dao;

import java.util.List;

import com.gestion.stock.entites.LigneCmdFournisseur;

public interface ILigneCmdFournisseurDAO extends IGenericDAO<LigneCmdFournisseur>{
	public List<LigneCmdFournisseur> getByIdCommande(Long idCommandeFournisseur);

}
