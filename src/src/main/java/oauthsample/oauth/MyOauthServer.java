package oauthsample.oauth;


import java.io.FileReader;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.data.ChallengeScheme;
import org.restlet.data.Parameter;
import org.restlet.ext.oauth.AccessTokenServerResource;
import org.restlet.ext.oauth.AuthPageServerResource;
import org.restlet.ext.oauth.AuthorizationServerResource;
import org.restlet.ext.oauth.ClientStore;
import org.restlet.ext.oauth.ClientStoreFactory;
import org.restlet.ext.oauth.HttpOAuthHelper;
import org.restlet.ext.oauth.ValidationServerResource;
import org.restlet.resource.Directory;
import org.restlet.routing.Router;
import org.restlet.security.Authenticator;
import org.restlet.security.ChallengeAuthenticator;
import org.restlet.util.Series;

/**
 * @author Martin Svensson
 *
 */
public class MyOauthServer extends Application {

  @Override
    public synchronized Restlet createInboundRoot() {
		PropertiesConfiguration configuration = getConfiguration();

		// Engine.setLogLevel(Level.FINE);
		Router root = new Router(getContext());

		Authenticator authenticator = null;

		if (configuration.getString("authentication","basic").equals("saml")) {
			authenticator = new SamlAuthenticator(getContext());
		} else {
			// Basic HTTP Authentication
			ChallengeAuthenticator ca = new ChallengeAuthenticator(getContext(), ChallengeScheme.HTTP_BASIC, "OAuth Test Server");
			ca.setVerifier(new MyVerifier());
			authenticator=ca;
		}
	
	// both authenticators
	authenticator.setNext(AuthorizationServerResource.class);
//	authenticator.getContext().getAttributes().put("_token_server_max_time_sec", 100000L);
//	authenticator.getContext().getAttributes().put("_token_server_time_sec", 100000L);
//	Series<Parameter> parameters = authenticator.getContext().getParameters();
//	parameters.add("authPage", "https://localhost/cs/status.html");

	root.attach("/authorize", authenticator);

	root.attach("/access_token", AccessTokenServerResource.class);
	root.attach("/validate", ValidationServerResource.class);
	root.attach(HttpOAuthHelper.getAuthPage(getContext()), AuthPageServerResource.class);

	// Set Template for AuthPage:
	HttpOAuthHelper.setAuthPageTemplate("authorize.html", getContext());
	// Dont ask for approval if previously approved
	HttpOAuthHelper.setAuthSkipApproved(true, getContext());

	// Attach Image Directory for our login.html page
	final Directory imgs = new Directory(getContext(), "clap:///img/");
	root.attach("/img", imgs);
	getContext().getLogger().info("done");

	// Finally create a test client:
	ClientStore clientStore = ClientStoreFactory.getInstance();
	
	String host = configuration.getString("hostname", "localhost");
	clientStore.createClient("1234567890", "secret1", "https://" + host + "/cs/status.html");
	clientStore.createClient("1336", "secret1", "https://" + host + "/cs/status2.html");
	clientStore.createClient("1337", "secret1", "http://" + host + "/statusnet-oauth2/oauth2login.html");

	return root;
  }

private PropertiesConfiguration getConfiguration() {
	PropertiesConfiguration configuration=null;
	try {
		configuration = new PropertiesConfiguration("oauth2.properties");
	} catch (ConfigurationException e) {
		e.printStackTrace();
	}
	return configuration;
}
      
}
