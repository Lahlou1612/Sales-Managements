package com.gestion.stock.dao;

import java.util.List;

import com.gestion.stock.entites.Achat;

public interface IAchatDAO extends IGenericDAO<Achat>{
	public List<Achat> getByStatutAchat(String statut);

}
