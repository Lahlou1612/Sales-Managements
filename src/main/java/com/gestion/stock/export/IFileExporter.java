package com.gestion.stock.export;

import javax.servlet.http.HttpServletResponse;

public interface IFileExporter {

	boolean exportDataToExcel(HttpServletResponse response, String fileName, String encodage);
	boolean importDataFromExcel();
}
