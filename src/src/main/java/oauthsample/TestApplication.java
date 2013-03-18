package oauthsample;

import oauthsampe.proxy.MyProxyApplication;
import oauthsample.oauth.MyOauthServer;
import oauthsample.protect.MyProtectedApplication;

import org.restlet.Component;
import org.restlet.data.Protocol;

/**
 * @author esvmart
 *
 */
public class TestApplication {
    
    public static void main(String[] args) throws Exception{
        Component c = new Component();
        c.getServers().add(Protocol.HTTP, 9090);
        c.getClients().add(Protocol.HTTP);
        c.getClients().add(Protocol.HTTPS);
        c.getClients().add(Protocol.RIAP);
        c.getClients().add(Protocol.CLAP);
        //map applications:
        c.getDefaultHost().attach("/oauth", new MyOauthServer());
        c.getDefaultHost().attach("/proxy", new MyProxyApplication());
        c.getDefaultHost().attach("/protect", new MyProtectedApplication());
        c.start();
        
    }

}
