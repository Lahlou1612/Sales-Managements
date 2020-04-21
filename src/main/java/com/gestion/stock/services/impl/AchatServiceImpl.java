package com.gestion.stock.services.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gestion.stock.dao.IAchatDAO;
import com.gestion.stock.entites.Achat;
import com.gestion.stock.services.IAchatService;
@Transactional
public class AchatServiceImpl implements IAchatService{

	private IAchatDAO dao;
	 
	public void setDao(IAchatDAO dao) {
		this.dao = dao;
	}
	
	@Override
	public Achat save(Achat entity) {
		return this.dao.save(entity);
	}

	@Override
	public Achat update(Achat entity) {
		return this.dao.update(entity);
	}

	@Override
	public List<Achat> selectAll() {
		return dao.selectAll();
	}

	@Override
	public Achat getById(Long id) {
		return dao.getById(id);
	}

	@Override
	public void remove(Long id) {
		dao.remove(id);
	}

	@Override
	public List<Achat> selectAll(String sortField, String sort) {
		return dao.selectAll(sortField, sort);
	}

	@Override
	public Achat findOne(String paramName, Object paramValue) {
		return dao.findOne(paramName, paramValue);
	}

	@Override
	public Achat findOne(String[] paramNames, Object[] paramValues) {
		return dao.findOne(paramNames, paramValues);
	}

	@Override
	public int findCountBy(String paramName, String paramValue) {
		return dao.findCountBy(paramName, paramValue);
	}

	@Override
	public List<Achat> getByStatutAchat(String statut) {
		return dao.getByStatutAchat(statut);
	}

}
