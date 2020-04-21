package com.gestion.stock.export;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestion.stock.entites.Client;
import com.gestion.stock.services.IClientService;
import com.gestion.stock.utils.ApplicationConstants;
import com.mysql.jdbc.StringUtils;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Component("clientExporter")
public class ClientExporter implements IFileExporter{
	@Autowired
	private IClientService clientService;

	@Override
	public boolean exportDataToExcel(HttpServletResponse response, String fileName, String encodage) {
		if(StringUtils.isEmptyOrWhitespaceOnly(fileName))
		{
			fileName = ApplicationConstants.FILE_NAME_CLIENT;
		}
		if(StringUtils.isEmptyOrWhitespaceOnly(encodage)) {
			encodage = ApplicationConstants.DEFAULT_ENCODAGE;
		}
		response.setContentType(ApplicationConstants.EXCEL_CONTENT_TYPE);
		response.setHeader(ApplicationConstants.CONTENT_DISPOSITION, "attachment; filename ="+fileName+".xls");
		WorkbookSettings workbookSettings = new WorkbookSettings();
		workbookSettings.setEncoding(encodage);
		try {
			WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream(), workbookSettings);
			WritableSheet sheet = workbook.createSheet(fileName, 0);
			
			/*Sheet Header*/
			Label labelID= new Label(0, 0, ApplicationConstants.ID_CLIENT);
			labelID.setCellFeatures(new WritableCellFeatures());
			labelID.getCellFeatures().setComment("");
			sheet.addCell(labelID);
			
			Label labelNom= new Label(1, 0, ApplicationConstants.NOM_CLIENT);
			labelNom.setCellFeatures(new WritableCellFeatures());
			labelNom.getCellFeatures().setComment("");
			sheet.addCell(labelNom);
			
			Label labelPrenom= new Label(2, 0, ApplicationConstants.PRENOM_CLIENT);
			labelPrenom.setCellFeatures(new WritableCellFeatures());
			labelPrenom.getCellFeatures().setComment("");
			sheet.addCell(labelPrenom);
			
			Label labelAdresse= new Label(3, 0, ApplicationConstants.ADRESSE);
			labelAdresse.setCellFeatures(new WritableCellFeatures());
			labelAdresse.getCellFeatures().setComment("");
			sheet.addCell(labelAdresse);
			
			Label labelMail= new Label(4, 0, ApplicationConstants.MAIL);
			labelMail.setCellFeatures(new WritableCellFeatures());
			labelMail.getCellFeatures().setComment("");
			sheet.addCell(labelMail);
			
			
			int currentRow=1;
			List<Client> clients = clientService.selectAll();
			if(clients != null && !clients.isEmpty()) {
				
				/*Writing in the sheet*/
				for (Client client : clients) {
					sheet.addCell(new Label(0,currentRow,client.getIdClient().toString()));
					sheet.addCell(new Label(1,currentRow,client.getNom()));
					sheet.addCell(new Label(2,currentRow,client.getPrenom()));
					sheet.addCell(new Label(3,currentRow,client.getAdresse()));
					sheet.addCell(new Label(4,currentRow,client.getMail()));
					currentRow++;
				}
				CellView cellView = new CellView();
				cellView.setAutosize(true);
				//cellView.setSize(500);
				sheet.setColumnView( 0, cellView);
				sheet.setColumnView( 1, cellView);
				sheet.setColumnView( 2, cellView);
				sheet.setColumnView( 3, cellView);
				sheet.setColumnView( 4, cellView);
				
				/*Write to excel sheet*/
				workbook.write();
				
				/*Cloosing the woorkBook*/
				workbook.close();
				
				response.getOutputStream().flush();
				response.getOutputStream().close();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean importDataFromExcel() {
		return false;
	}
}
