package oauthsample.oauth;

import java.util.HashMap;
import java.util.Map;

import org.restlet.Request;

public class SamlStore {

	private static Map<String, Request> map = new HashMap<String, Request>();

	public static void put(String id, Request request) {
		System.out.println("put id" + id + ": " + request);
		map.put(id, request);
	}

	public static Request get(String id) {
		System.out.println("get id: " + id);
		return map.get(id);
	}

}
