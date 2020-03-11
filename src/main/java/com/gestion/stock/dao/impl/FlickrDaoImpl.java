package com.gestion.stock.dao.impl;

import java.io.IOException;
import java.io.InputStream;

import java.util.Scanner;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.github.scribejava.apis.FlickrApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuth1Token;
import com.github.scribejava.core.model.OAuth2Authorization;
import com.github.scribejava.core.oauth.OAuth10aService;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;

import org.scribe.model.Token;
import org.scribe.model.Verifier;

import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import com.flickr4java.flickr.RequestContext;
import com.flickr4java.flickr.auth.Auth;
import com.flickr4java.flickr.auth.AuthInterface;
import com.flickr4java.flickr.auth.Permission;
import com.flickr4java.flickr.people.User;
import com.flickr4java.flickr.uploader.UploadMetaData;
import com.flickr4java.flickr.uploader.Uploader;
import com.flickr4java.flickr.util.*;
import com.flickr4java.flickr.util.FileAuthStore;
import com.gestion.stock.dao.IFlickrDAO;
import com.flickr4java.flickr.util.AuthStore;
import com.flickr4java.flickr.Flickr;
import com.flickr4java.flickr.FlickrException;
import com.flickr4java.flickr.REST;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import com.github.scribejava.apis.*;
import com.github.scribejava.core.builder.*;
import com.github.scribejava.core.model.OAuth1AccessToken;
import com.github.scribejava.core.model.OAuth1RequestToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.oauth.OAuth10aService;
import com.github.scribejava.core.oauth.*;

public class FlickrDaoImpl implements IFlickrDAO {

	private static Flickr flickr;
	private UploadMetaData uploadMetaDate = new UploadMetaData();
	private static String apiKey = "49874a858af03ab83c24c98f109b09e9";
	private static String sharedSecret = "1b6894e4990c708f";

	public void connect() {
		Flickr.debugRequest = false;
		Flickr.debugStream = false;
		REST reste = new REST ();
		OAuth10aService service = new ServiceBuilder(apiKey).apiSecret(sharedSecret)
				.build(FlickrApi.instance(FlickrApi.FlickrPerm.READ));
		
		flickr = new Flickr(apiKey, sharedSecret, reste);
		Auth auth = new Auth();
		auth.setPermission(Permission.DELETE);
		auth.setToken("72157713422944117-9154ccdd9fa532b4");
		//auth.setUser(new User().set);
		auth.setTokenSecret("88e824b8d50a9f73");
		RequestContext requestContext = RequestContext.getRequestContext();
		requestContext.setAuth(auth);
		flickr.setAuth(auth);
		
	}

	@Override
	public String savePhoto(InputStream photo, String title) throws FlickrException {
		connect();
		uploadMetaDate.setTitle(title);
		String photoId = flickr.getUploader().upload(photo, uploadMetaDate);
		return flickr.getPhotosInterface().getPhoto(photoId).getMedium640Url();
	}

	public Flickr auth() throws InterruptedException, ExecutionException, IOException, FlickrException {
		Flickr flickr = new Flickr(apiKey, sharedSecret, new REST());
		OAuth10aService service = new ServiceBuilder(apiKey).apiSecret(sharedSecret)
				.build(FlickrApi.instance(FlickrApi.FlickrPerm.READ));
		final Scanner in = new Scanner(System.in);
		final OAuth1RequestToken requestToken = service.getRequestToken();
		final String authUrl = service.getAuthorizationUrl(requestToken);
		System.out.println(authUrl);
		System.out.println("Paste the verifier code here:");
		System.out.print(">>");
		final String oauthVerifier = in.nextLine();
		final OAuth1AccessToken accessToken = service.getAccessToken(requestToken, oauthVerifier);
		Auth aut = flickr.getAuthInterface().checkToken(accessToken);
		System.out.println("Token : " + requestToken.getToken());
		System.out.println("Secret : " + requestToken.getTokenSecret());
		System.out.println("nsid : " + aut.getUser().getId());
		System.out.println("Real Name : " + aut.getUser().getRealName());
		System.out.println("User Name : " + aut.getUser().getUsername());
		System.out.println("Permission : " + aut.getPermission().getType());
		return flickr;
	}
}