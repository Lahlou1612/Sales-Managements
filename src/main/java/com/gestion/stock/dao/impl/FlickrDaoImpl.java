package com.gestion.stock.dao.impl;

import java.io.InputStream;
import org.scribe.model.Token;
import javax.swing.JOptionPane;

import org.scribe.model.Verifier;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.gestion.stock.dao.IFlickrDAO;


public class FlickrDaoImpl implements IFlickrDAO {

	private Flickr flickr;
	private UploadMetaData uploadMetaDate = new UploadMetaData();
	private String apiKey = "54f4ee74a1f8ef20a95f8988912b691e";
	private String sharedSecret = "723b0ec32009ce12";
	

	private void connect() {
		flickr = new Flickr(apiKey, sharedSecret, new REST());
		Auth auth = new Auth();
		auth.setPermission(Permission.READ);
		auth.setToken("72157678731273846-45f68ca35c4b3c2");
		auth.setTokenSecret("6342826986448e45");
		RequestContext requestContext = RequestContext.getRequestContext();
		requestContext.setAuth(auth);
		flickr.setAuth(auth);
	}

	@Override
	public String savePhoto(InputStream photo, String title) throws Exception {
		connect();
		uploadMetaDate.setTitle(title);
		String photoId = flickr.getUploader().upload(photo, uploadMetaDate);
		return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
	}

	public void auth() {
		
		flickr = new Flickr(apiKey, sharedSecret, new REST());
		AuthInterface authInterface = flickr.getAuthInterface();
		Token token = authInterface.getRequestToken();
		System.out.println("Token : " + token);
		String url = authInterface.getAuthorizationUrl(token, Permission.DELETE);
		System.out.println("Follow this URL to autorise yourself on Flickr !");
		System.out.println(url);
		System.out.println(">>");
		String tokenKey = JOptionPane.showInputDialog(null);
		Token requestToken = authInterface.getAccessToken(token, new Verifier(tokenKey));
		System.out.println("Authentification succes");
		Auth auth = null;
		try {
			auth = authInterface.checkToken(requestToken);
		} catch (FlickrException e) {
			e.printStackTrace();
		}
		System.out.println("Token : "+ requestToken.getToken());
		System.out.println("Secret : "+ requestToken.getSecret());
		System.out.println("nsid : "+ auth.getUser().getId());
		System.out.println("Real Name : " + auth.getUser().getRealName());
		System.out.println("User Name : "+ auth.getUser().getUsername());
		System.out.println("Permission : "+auth.getPermission().getType());
	}

}
