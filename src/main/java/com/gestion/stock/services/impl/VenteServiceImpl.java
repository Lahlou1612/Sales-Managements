package com.gestion.stock.services.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.gestion.stock.dao.IVenteDAO;
import com.gestion.stock.entites.Vente;
import com.gestion.stock.services.IVenteService;

@Transactional
public class VenteServiceImpl implements IVenteService{

	private IVenteDAO dao;
	
	public void setDao(IVenteDAO dao) {
		this.dao = dao;
	}

	@Override
	public Vente save(Vente entity) {
		return dao.save(entity);
	}

	@Override
	public Vente update(Vente entity) {
		return dao.update(entity);
	}

	@Override
	public List<Vente> selectAll() {
		return dao.selectAll();
	}

	@Override
	public Vente getById(Long id) {
		return dao.getById(id);
	}

	@Override
	public void remove(Long id) {
		dao.remove(id);
	}

	@Override
	public List<Vente> selectAll(String sortField, String sort) {
		return dao.selectAll(sortField, sort);
	}

	@Override
	public Vente findOne(String paramName, Object paramValue) {
		return dao.findOne(paramName, paramValue);
	}

	@Override
	public Vente findOne(String[] paramNames, Object[] paramValues) {
		return dao.findOne(paramNames, paramValues);
	}

	@Override
	public int findCountBy(String paramName, String paramValue) {
		return dao.findCountBy(paramName, paramValue);
	}

	@Override
	public List<Vente> getByStatutVente(String statut) {
		return dao.getByStatutVente(statut);
	}

}
