package com.gestion.stock.services.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gestion.stock.dao.ILigneCmdClientDAO;
import com.gestion.stock.entites.LigneCmdClient;
import com.gestion.stock.services.ILigneCmdClientService;

@Transactional
public class LigneCmdClientServiceImpl implements ILigneCmdClientService {

	private ILigneCmdClientDAO dao;

	public void setDao(ILigneCmdClientDAO dao) {
		this.dao = dao;
	}

	@Override
	public LigneCmdClient save(LigneCmdClient entity) {
		return dao.save(entity);
	}

	@Override
	public LigneCmdClient update(LigneCmdClient entity) {
		return dao.update(entity);
	}

	@Override
	public List<LigneCmdClient> selectAll() {
		return dao.selectAll();
	}

	@Override
	public LigneCmdClient getById(Long id) {
		return dao.getById(id);
	}

	@Override
	public void remove(Long id) {
		dao.remove(id);
	}

	@Override
	public List<LigneCmdClient> selectAll(String sortField, String sort) {
		return dao.selectAll(sortField, sort);
	}

	@Override
	public LigneCmdClient findOne(String paramName, Object paramValue) {
		return dao.findOne(paramName, paramValue);
	}

	@Override
	public LigneCmdClient findOne(String[] paramNames, Object[] paramValues) {
		return dao.findOne(paramNames, paramValues);
	}

	@Override
	public int findCountBy(String paramName, String paramValue) {
		return dao.findCountBy(paramName, paramValue);
	}

}
