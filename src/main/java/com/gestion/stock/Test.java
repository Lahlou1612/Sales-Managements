package com.gestion.stock;



import java.util.List;
import java.io.IOException;

import java.util.concurrent.ExecutionException;


import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import com.flickr4java.flickr.FlickrException;

import com.gestion.stock.entites.Client;
import com.gestion.stock.entites.Utilisateur;
import com.gestion.stock.services.IClientService;
import com.gestion.stock.services.IUtilisateurService;

public class Test {

	@Autowired
	public static IClientService dao;

	@Autowired
	public static IUtilisateurService service;
	
	public static void main(String[] args) throws IOException, SAXException, FlickrException, InterruptedException, ExecutionException {
		//FlickrDaoImpl flickrDAO = new FlickrDaoImpl();
//		List<Client> clients = dao.selectAll();
		List<Client> clients= dao.selectAll();
		for (Client client : clients) {
//			if(utilisateur.getIdUtilisateur().equals(e)) {
			System.out.println(client.getNom());
			System.out.println(client.getPrenom());
		}
		Long e=new Long(1);
		System.out.println(e);
		List<Utilisateur> list = service.selectAll();
		for (Utilisateur utilisateur : list) {
//			if(utilisateur.getIdUtilisateur().equals(e)) {
			System.out.println(utilisateur.getIdUtilisateur());
			System.out.println(utilisateur.getNom());
			System.out.println(utilisateur.getPrenom());
			System.out.println(utilisateur.getMail());
			System.out.println(utilisateur.getMotDePasse());
		}
		
		//System.out.println(utilisateur.getRolesName());
		
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
