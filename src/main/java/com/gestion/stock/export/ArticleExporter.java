package com.gestion.stock.export;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gestion.stock.entites.Article;
import com.gestion.stock.services.IArticleService;
import com.gestion.stock.utils.ApplicationConstants;
import com.mysql.jdbc.StringUtils;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Component("articleExporter")
public class ArticleExporter implements IFileExporter{
	
	@Autowired
	private IArticleService articleService;

	@Override
	public boolean exportDataToExcel(HttpServletResponse response, String fileName, String encodage) {
		if(StringUtils.isEmptyOrWhitespaceOnly(fileName))
		{
			fileName = ApplicationConstants.FILE_NAME;
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
			Label labelCode= new Label(0, 0, ApplicationConstants.CODE_ARTICLE);
			labelCode.setCellFeatures(new WritableCellFeatures());
			labelCode.getCellFeatures().setComment("");
			sheet.addCell(labelCode);
			
			Label labelDesignation= new Label(1, 0, ApplicationConstants.DESIGNATION);
			labelDesignation.setCellFeatures(new WritableCellFeatures());
			labelDesignation.getCellFeatures().setComment("");
			sheet.addCell(labelDesignation);
			
			Label labelPrixUnitaireHT= new Label(2, 0, ApplicationConstants.PRIX_UNITAIRE_HT);
			labelPrixUnitaireHT.setCellFeatures(new WritableCellFeatures());
			labelPrixUnitaireHT.getCellFeatures().setComment("");
			sheet.addCell(labelPrixUnitaireHT);
			
			Label labelPrixUnitaireTTC= new Label(3, 0, ApplicationConstants.PRIX_UNITAIRE_TTC);
			labelPrixUnitaireTTC.setCellFeatures(new WritableCellFeatures());
			labelPrixUnitaireTTC.getCellFeatures().setComment("");
			sheet.addCell(labelPrixUnitaireTTC);
			
			Label labelTVA= new Label(4, 0, ApplicationConstants.TVA);
			labelTVA.setCellFeatures(new WritableCellFeatures());
			labelTVA.getCellFeatures().setComment("");
			sheet.addCell(labelTVA);
			
			Label labelCategorie= new Label(5, 0, ApplicationConstants.CATEGORIE);
			labelCategorie.setCellFeatures(new WritableCellFeatures());
			labelCategorie.getCellFeatures().setComment("");
			sheet.addCell(labelCategorie);
			
			int currentRow=1;
			List<Article> articles = articleService.selectAll();
			if(articles != null && !articles.isEmpty()) {
				
				/*Writing in the sheet*/
				for (Article article : articles) {
					sheet.addCell(new Label(0,currentRow,article.getCodeArticle()));
					sheet.addCell(new Label(1,currentRow,article.getDesignation()));
					sheet.addCell(new Label(2,currentRow,article.getPrixUnitaireHT().toString()));
					sheet.addCell(new Label(3,currentRow,article.getPrixUnitaireTTC().toString()));
					sheet.addCell(new Label(4,currentRow,article.getTauxTva().toString()));
					sheet.addCell(new Label(5,currentRow,article.getCategory().getCode()));
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
