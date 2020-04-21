package com.gestion.stock.services.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gestion.stock.dao.IRoleDAO;
import com.gestion.stock.entites.Roles;
import com.gestion.stock.services.IRoleService;

@Transactional
public class RoleServiceImpl implements IRoleService {
	private IRoleDAO dao;

	public void setDao(IRoleDAO dao) {
		this.dao = dao;
	}

	@Override
	public Roles save(Roles entity) {
		return dao.save(entity);
	}

	@Override
	public Roles update(Roles entity) {
		return dao.update(entity);
	}

	@Override
	public List<Roles> selectAll() {
		return dao.selectAll();
	}

	@Override
	public Roles getById(Long id) {
		return dao.getById(id);
	}
	

	@Override
	public List<Roles> getByIdRole(Long idUtilisateur) {
		return dao.getByIdRole(idUtilisateur);
	}

	@Override
	public void remove(Long id) {
		dao.remove(id);
	}

	@Override
	public List<Roles> selectAll(String sortField, String sort) {
		return dao.selectAll(sortField, sort);
	}

	@Override
	public Roles findOne(String paramName, Object paramValue) {
		return dao.findOne(paramName, paramValue);
	}

	@Override
	public Roles findOne(String[] paramNames, Object[] paramValues) {
		return dao.findOne(paramNames, paramValues);
	}

	@Override
	public int findCountBy(String paramName, String paramValue) {
		return dao.findCountBy(paramName, paramValue);
	}
}
