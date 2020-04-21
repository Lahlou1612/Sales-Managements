package com.gestion.stock.services.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gestion.stock.dao.ILigneAchatDAO;
import com.gestion.stock.entites.LigneAchat;
import com.gestion.stock.services.ILigneAchatService;
@Transactional
public class LigneAchatServiceImpl implements ILigneAchatService{

	private ILigneAchatDAO dao;
	
	public void setDao(ILigneAchatDAO dao) {
		this.dao = dao;
	}

	@Override
	public LigneAchat save(LigneAchat entity) {
		return dao.save(entity);
	}

	@Override
	public LigneAchat update(LigneAchat entity) {
		return dao.update(entity);
	}

	@Override
	public List<LigneAchat> selectAll() {
		return dao.selectAll();
	}

	@Override
	public LigneAchat getById(Long id) {
		return dao.getById(id);
	}

	@Override
	public void remove(Long id) {
		dao.remove(id);
	}

	@Override
	public List<LigneAchat> selectAll(String sortField, String sort) {
		return dao.selectAll(sortField, sort);
	}

	@Override
	public LigneAchat findOne(String paramName, Object paramValue) {
		return dao.findOne(paramName, paramValue);
	}

	@Override
	public LigneAchat findOne(String[] paramNames, Object[] paramValues) {
		return dao.findOne(paramNames, paramValues);
	}

	@Override
	public int findCountBy(String paramName, String paramValue) {
		return dao.findCountBy(paramName, paramValue);
	}

	@Override
	public List<LigneAchat> getByIdAchat(Long idAchat) {
		return dao.getByIdAchat(idAchat);
	}
}
