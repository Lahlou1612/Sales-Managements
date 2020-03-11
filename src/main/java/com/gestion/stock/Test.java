package com.gestion.stock;



import java.util.List;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.concurrent.ExecutionException;

import javax.swing.Icon;
import javax.swing.JOptionPane;

import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import com.flickr4java.flickr.FlickrException;
import com.gestion.stock.dao.IClientDAO;
import com.gestion.stock.dao.IGenericDAO;
import com.gestion.stock.dao.impl.*;
import com.gestion.stock.entites.Client;
import com.gestion.stock.services.IClientService;

public class Test {

	@Autowired
	public static IClientService dao;

	
	
	public static void main(String[] args) throws IOException, SAXException, FlickrException, InterruptedException, ExecutionException {
		//FlickrDaoImpl flickrDAO = new FlickrDaoImpl();
		List<Client> clients = dao.selectAll();
		
		/*if(clients.size() !=0)
		{
			
		for(int i =0; i<clients.size();i++)
		{
			System.out.println(clients.get(i).getNom());
		}}
		//flickrDAO.auth();
		/*try {
			InputStream inputStream = new FileInputStream("C:\\Users\\lahlo\\Desktop\\Kaoutar DCIM\\WhatsApp Images\\IMG-20190212-WA0011.jpg");
			String url = flickrDAO.savePhoto(inputStream, "MyImage");
			System.out.println(url);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	*/
		
		
	}
}
