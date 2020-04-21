package com.gestion.stock.export;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestion.stock.entites.Fournisseur;
import com.gestion.stock.services.IFournisseurService;
import com.gestion.stock.utils.ApplicationConstants;
import com.mysql.jdbc.StringUtils;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Component("fournisseurExporter")
public class FournisseurExporter implements IFileExporter{
	@Autowired
	private IFournisseurService fournisseurService;

	@Override
	public boolean exportDataToExcel(HttpServletResponse response, String fileName, String encodage) {
		if(StringUtils.isEmptyOrWhitespaceOnly(fileName))
		{
			fileName = ApplicationConstants.FILE_NAME_FOURNISSEUR;
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
			Label labelCode= new Label(0, 0, ApplicationConstants.ID_FOURNISSEUR);
			labelCode.setCellFeatures(new WritableCellFeatures());
			labelCode.getCellFeatures().setComment("");
			sheet.addCell(labelCode);
			
			Label labelDesignation= new Label(1, 0, ApplicationConstants.NOM_FOURNISSEUR);
			labelDesignation.setCellFeatures(new WritableCellFeatures());
			labelDesignation.getCellFeatures().setComment("");
			sheet.addCell(labelDesignation);
			
			Label labelPrixUnitaireHT= new Label(2, 0, ApplicationConstants.PRENOM_FOURNISSEUR);
			labelPrixUnitaireHT.setCellFeatures(new WritableCellFeatures());
			labelPrixUnitaireHT.getCellFeatures().setComment("");
			sheet.addCell(labelPrixUnitaireHT);
			
			Label labelPrixUnitaireTTC= new Label(3, 0, ApplicationConstants.ADRESSE);
			labelPrixUnitaireTTC.setCellFeatures(new WritableCellFeatures());
			labelPrixUnitaireTTC.getCellFeatures().setComment("");
			sheet.addCell(labelPrixUnitaireTTC);
			
			Label labelTVA= new Label(4, 0, ApplicationConstants.MAIL);
			labelTVA.setCellFeatures(new WritableCellFeatures());
			labelTVA.getCellFeatures().setComment("");
			sheet.addCell(labelTVA);
			
			int currentRow=1;
			List<Fournisseur> fournisseurs = fournisseurService.selectAll();
			if(fournisseurs != null && !fournisseurs.isEmpty()) {
				
				/*Writing in the sheet*/
				for (Fournisseur fournisseur : fournisseurs) {
					sheet.addCell(new Label(0,currentRow,fournisseur.getIdFournisseur().toString()));
					sheet.addCell(new Label(1,currentRow,fournisseur.getNom()));
					sheet.addCell(new Label(2,currentRow,fournisseur.getPrenom()));
					sheet.addCell(new Label(3,currentRow,fournisseur.getAdresse()));
					sheet.addCell(new Label(4,currentRow,fournisseur.getMail()));
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
