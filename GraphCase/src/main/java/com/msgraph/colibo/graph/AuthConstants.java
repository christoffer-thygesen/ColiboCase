package com.msgraph.colibo.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthConstants {
	public static class Tenants {
		public static final String Common = "common";
		public static final String Organizations = "organizations";
		public static final String Consumers = "consumers";
	}

	public static final String BEARER = "Bearer ";
	public static final String TOKEN_ENDPOINT = "/oauth2/v2.0/token";
	public static final String AUTHORIZATION_HEADER = "Authorization";
	
	public static final String TENANT = "<TENANT ID>";
	public static final String CLIENT_ID = "<CLIENT ID>";
	public static final String CLIENT_SECRET = "<CLIENT SECRET";
	public static final List<String> SCOPES = new ArrayList<String>(Arrays.asList("https://graph.microsoft.com/.default"));
}
