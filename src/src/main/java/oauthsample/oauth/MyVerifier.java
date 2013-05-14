package oauthsample.oauth;

import org.restlet.security.SecretVerifier;

/**
 * @author Martin Svensson
 * 
 */
public class MyVerifier extends SecretVerifier {

    @Override
    public int verify(String identifier, char[] secret) {
	if ("bob".equals(identifier) && compare("alice".toCharArray(), secret)) {
	    return RESULT_VALID;
	} else if ("alice".equals(identifier) && compare("bob".toCharArray(), secret)) {
	    return RESULT_VALID;
	}
	return RESULT_INVALID;
    }

}
