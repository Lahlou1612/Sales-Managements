package com.gestion.stock.export;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestion.stock.entites.Achat;
import com.gestion.stock.services.IAchatService;
import com.gestion.stock.utils.ApplicationConstants;
import com.mysql.jdbc.StringUtils;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Component("achatExporter")
public class AchatExporter  implements IFileExporter{
	@Autowired
	private IAchatService achatService;

	@Override
	public boolean exportDataToExcel(HttpServletResponse response, String fileName, String encodage) {
		if(StringUtils.isEmptyOrWhitespaceOnly(fileName))
		{
			fileName = ApplicationConstants.FILE_NAME_ACHAT;
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
			Label labelCode= new Label(0, 0, ApplicationConstants.ID_ACHAT);
			labelCode.setCellFeatures(new WritableCellFeatures());
			labelCode.getCellFeatures().setComment("");
			sheet.addCell(labelCode);
			
			Label labelDesignation= new Label(1, 0, ApplicationConstants.CODE_ACHAT);
			labelDesignation.setCellFeatures(new WritableCellFeatures());
			labelDesignation.getCellFeatures().setComment("");
			sheet.addCell(labelDesignation);
			
			Label labelPrixUnitaireHT= new Label(2, 0, ApplicationConstants.DATE_ACHAT);
			labelPrixUnitaireHT.setCellFeatures(new WritableCellFeatures());
			labelPrixUnitaireHT.getCellFeatures().setComment("");
			sheet.addCell(labelPrixUnitaireHT);
			
			Label labelPrixUnitaireTTC= new Label(3, 0, ApplicationConstants.NOM_FOURNISSEUR);
			labelPrixUnitaireTTC.setCellFeatures(new WritableCellFeatures());
			labelPrixUnitaireTTC.getCellFeatures().setComment("");
			sheet.addCell(labelPrixUnitaireTTC);
			
			Label labelTVA= new Label(4, 0, ApplicationConstants.ID_COMMANDE_CLIENT);
			labelTVA.setCellFeatures(new WritableCellFeatures());
			labelTVA.getCellFeatures().setComment("");
			sheet.addCell(labelTVA);
			
			Label labelCategorie= new Label(5, 0, ApplicationConstants.STATUT);
			labelCategorie.setCellFeatures(new WritableCellFeatures());
			labelCategorie.getCellFeatures().setComment("");
			sheet.addCell(labelCategorie);
			
			Label labelMontant= new Label(6, 0, ApplicationConstants.MONTANT_GLOBAL);
			labelMontant.setCellFeatures(new WritableCellFeatures());
			labelMontant.getCellFeatures().setComment("");
			sheet.addCell(labelMontant);
			
			int currentRow=1;
			List<Achat> achats = achatService.selectAll();
			if(achats != null && !achats.isEmpty()) {
				
				/*Writing in the sheet*/
				for (Achat achat : achats) {
					sheet.addCell(new Label(0,currentRow,achat.getIdAchat().toString()));
					sheet.addCell(new Label(1,currentRow,achat.getCode()));
					sheet.addCell(new Label(2,currentRow,achat.getDateAchat().toString()));
					sheet.addCell(new Label(3,currentRow,achat.getNomFournisseur()));
					sheet.addCell(new Label(4,currentRow,achat.getIdCommande().toString()));
					sheet.addCell(new Label(5,currentRow,achat.getStatut()));
					sheet.addCell(new Label(6,currentRow,achat.getMontantGlobal().toString()));
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
				sheet.setColumnView( 5, cellView);
				sheet.setColumnView( 6, cellView);
				
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
