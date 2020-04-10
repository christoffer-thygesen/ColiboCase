package com.msgraph.colibo.GraphCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.microsoft.graph.models.extensions.IGraphServiceClient;
import com.microsoft.graph.models.extensions.User;
import com.microsoft.graph.requests.extensions.GraphServiceClient;
import com.microsoft.graph.requests.extensions.IUserCollectionPage;
import com.microsoft.graph.requests.extensions.IUserCollectionRequestBuilder;
import com.msgraph.colibo.graph.AuthConstants;
import com.msgraph.colibo.graph.ClientCredentialProvider;
import com.msgraph.colibo.graph.GraphPerson;
import com.msgraph.colibo.graph.NationalCloud;
import com.msgraph.colibo.model.ColiboPerson;
import com.msgraph.colibo.xml.Data;
import com.msgraph.colibo.xml.Person;
import com.msgraph.colibo.xml.XmlReader;

public class App {

	public static void main(String[] args) {
		// XML data
		Data data = null;
		// Azure AD data
		List<GraphPerson> graphPersonList = new ArrayList<GraphPerson>();
		// "database"
		List<ColiboPerson> coliboPersonList = new ArrayList<ColiboPerson>();

		// Extract xml data
		XmlReader reader = new XmlReader();
		// xml-fil ligger i projekt. Burde være http-request.
		data = reader.ReadXmlFile("personaleList.xml");

		/**
		 * ClientCredentialProvider lib
		 * https://github.com/microsoftgraph/msgraph-sdk-java-auth 
		 * Indtast TENANT, CLIENT_ID, CLIENT_SECRET i AuthConstants
		 */
		ClientCredentialProvider ccp = new ClientCredentialProvider(AuthConstants.CLIENT_ID, AuthConstants.SCOPES,
				AuthConstants.CLIENT_SECRET, AuthConstants.TENANT, NationalCloud.Global);

		// Azure AD client
		IGraphServiceClient graphClient = GraphServiceClient.builder().authenticationProvider(ccp).buildClient();

		IUserCollectionPage page = graphClient.users().buildRequest().get();

		// extract Azure data
		GraphPerson tmp = null;
		while (page != null) {
			for (User user : page.getCurrentPage()) {
				tmp = new GraphPerson();
				tmp.setMobile(user.mobilePhone);
				tmp.setName(user.givenName + " " + user.surname);
				graphPersonList.add(tmp);
			}
			IUserCollectionRequestBuilder builder = page.getNextPage();
			if (builder == null) {
				break;
			}
			page = builder.buildRequest().get();
		}

		// Integrate lønssystem
		ColiboPerson cTmp = null;
		for (Person p : data.getPersons().getPerson()) {
			cTmp = new ColiboPerson();
			cTmp.setName(p.getName());
			cTmp.setAddress(p.getAddress());
			cTmp.setCity(p.getCity());
			List<String> email = cTmp.getEmail();
			email.add(p.getEmail());
			cTmp.setEmail(email);
			List<String> mobile = cTmp.getMobile();
			mobile.add(p.getMobile());
			cTmp.setMobile(mobile);
			cTmp.setEmployeeId(p.getNumber());
			cTmp.setTitle(p.getTitle());
			coliboPersonList.add(cTmp);
		}

		// Integrate Azure AD
		for (GraphPerson gp : graphPersonList) {
			ColiboPerson cp = containsName(coliboPersonList, gp.getName());
			List<String> mobile = null;
			if (cp != null) {
				coliboPersonList.remove(cp);
				mobile = cp.getMobile();
				mobile.add(gp.getMobile());
				coliboPersonList.add(cp);
			} else {
				cp = new ColiboPerson();
				cp.setName(gp.getName());
				mobile = cp.getMobile();
				mobile.add(gp.getMobile());
				cp.setMobile(mobile);
				coliboPersonList.add(cp);
			}
		}
	}

	public static ColiboPerson containsName(final List<ColiboPerson> list, final String name) {
		Optional<ColiboPerson> findFirst = list.stream().filter(o -> o.getName().equals(name)).findFirst();
		if (findFirst.isPresent()) {
			return findFirst.get();
		}
		return null;
	}
}
