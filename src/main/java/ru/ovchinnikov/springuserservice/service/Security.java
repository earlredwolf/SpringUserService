package ru.ovchinnikov.springuserservice.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class Security {

	final HashMap<String,String> logins = new HashMap<String, String>();

	public Security() {
		logins.put("adsfadsf","app1");
		logins.put("sdfsdfsd","app2");
	}

	public Boolean check(String key) {
		return logins.containsKey(key);
	}

}
