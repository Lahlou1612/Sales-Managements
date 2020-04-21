package com.gestion.stock.dao;

import java.util.List;

import com.gestion.stock.entites.LigneAchat;

public interface ILigneAchatDAO extends IGenericDAO<LigneAchat>{
	public List<LigneAchat> getByIdAchat(Long idAchat);

}
