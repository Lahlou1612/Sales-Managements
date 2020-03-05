package com.gestion.stock.dao;

import java.util.List;

public interface IGenericDAO<E> {
	
	public E save(E entity);

	public E update(E entity);

	public List<E> selectAll();

	public E getById(Long id);

	public void remove(Long id);

	public List<E> selectAll(String sortField, String sort);

	public E findOne(String paramName, Object paramValue);

	public E findOne(String[] paramNames, Object[] paramValues);

	public int findCountBy(String paramName, String paramValue);
}
