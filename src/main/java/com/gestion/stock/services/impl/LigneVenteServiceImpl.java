package com.gestion.stock.services.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gestion.stock.dao.ILigneVenteDAO;
import com.gestion.stock.entites.LigneVente;
import com.gestion.stock.services.ILigneVenteService;

@Transactional
public class LigneVenteServiceImpl implements ILigneVenteService {

	private ILigneVenteDAO dao;

	public void setDao(ILigneVenteDAO dao) {
		this.dao = dao;
	}

	@Override
	public LigneVente save(LigneVente entity) {
		return dao.save(entity);
	}

	@Override
	public LigneVente update(LigneVente entity) {
		return dao.update(entity);
	}

	@Override
	public List<LigneVente> selectAll() {
		return dao.selectAll();
	}

	@Override
	public LigneVente getById(Long id) {
		return dao.getById(id);
	}

	@Override
	public void remove(Long id) {
		dao.remove(id);
	}

	@Override
	public List<LigneVente> selectAll(String sortField, String sort) {
		return dao.selectAll(sortField, sort);
	}

	@Override
	public LigneVente findOne(String paramName, Object paramValue) {
		return dao.findOne(paramName, paramValue);
	}

	@Override
	public LigneVente findOne(String[] paramNames, Object[] paramValues) {
		return dao.findOne(paramNames, paramValues);
	}

	@Override
	public int findCountBy(String paramName, String paramValue) {
		return dao.findCountBy(paramName, paramValue);
	}

	@Override
	public List<LigneVente> getByIdVente(Long idVente) {
		return dao.getByIdVente(idVente);
	}

}
