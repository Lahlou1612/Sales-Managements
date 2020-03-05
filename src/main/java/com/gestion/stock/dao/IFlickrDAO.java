package com.gestion.stock.dao;

import java.io.InputStream;

public interface IFlickrDAO {
	public String savePhoto(InputStream stream, String fileName) throws Exception;
}
