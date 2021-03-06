/**
 * © TheCompany QA 2019. All rights reserved.
 * CONFIDENTIAL AND PROPRIETARY INFORMATION OF TheCompany.
 */
package com.thecompany.qa.lib.common;

import com.thecompany.qa.lib.apiHelpers.APIHeaderRequestHelper;
import com.thecompany.qa.lib.authentication.OauthAuthentication;
import com.thecompany.qa.lib.connection.ConnectionManager;
import com.thecompany.qa.lib.connection.ConnectionResponse;
import org.apache.log4j.Logger;

public class MBaseAPIHelper {
	ConnectionManager conManager = null;
	private static OauthAuthentication oauthAuthentication;

	final static Logger logger = Logger.getRootLogger();

	public ConnectionResponse create(String microservice, String environment, String endpoint, String payload, APIHeaderRequestHelper apiRequestHeaders){
		initConManager();
		return conManager.post(getURI(microservice, environment, endpoint), payload, apiRequestHeaders);
	}

	public void delete(String microservice, String environment, String endpoint, APIHeaderRequestHelper apiRequestHeaders){
		initConManager();
		conManager.delete(getURI(microservice, environment, endpoint), apiRequestHeaders);
	}
	
	public ConnectionResponse update(String microservice, String environment, String endpoint, String payload,
			APIHeaderRequestHelper apiRequestHeaders) {
		initConManager();
		return conManager.put(getURI(microservice, environment, endpoint), payload, apiRequestHeaders);
	}

	public ConnectionResponse patch(String microservice, String environment, String endpoint, String payload,
									 APIHeaderRequestHelper apiRequestHeaders) {
		initConManager();
		return conManager.patch(getURI(microservice, environment, endpoint), payload, apiRequestHeaders);
	}

	public ConnectionResponse retrieve(String microservice, String environment, String endpoint, APIHeaderRequestHelper apiRequestHeaders){
		initConManager();
		return conManager.get(getURI(microservice, environment, endpoint), apiRequestHeaders);
	}

	public ConnectionResponse retrieve(String microservice, String environment, String endpoint, String payload, APIHeaderRequestHelper apiRequestHeaders){
		initConManager();
		return conManager.get(getURI(microservice, environment, endpoint), payload, apiRequestHeaders);
	}

	public void authenticateUsingOauth(String microservice, String environment, String endpoint, APIHeaderRequestHelper apiRequestHeaders){
		initConManager();
		oauthAuthentication = new OauthAuthentication();
		//conManager.initOauthAccessToken(getURI(microservice, environment, endpoint), apiRequestHeaders);
		oauthAuthentication.initOauthAccessToken(getURI(microservice, environment, endpoint), apiRequestHeaders);
	}

	public void authenticateUsingBasicAuth(String microservice, String environment, String endpoint, APIHeaderRequestHelper apiRequestHeaders){
		initConManager();
		oauthAuthentication = new OauthAuthentication();
		//conManager.initOauthAccessToken(getURI(microservice, environment, endpoint), apiRequestHeaders);
		oauthAuthentication.initOauthAccessToken(getURI(microservice, environment, endpoint), apiRequestHeaders);
		// THIS DOES NOT LOOK RIGHT - Why OAuth since method is for Basic?
	}

	private void initConManager(){
		conManager = conManager == null ? ConnectionManager.getInstance() : conManager;
	}

	private String getURI(String microservice, String environment, String endpoint) {
		//String URI = "https://" + microservice + environment + endpoint;
		String URI = "http://" + environment + microservice + endpoint;
		return URI;
	}
}