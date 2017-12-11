package com.jbilling.appdirect.cache.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.jbilling.appdirect.cache.JBillingCache;

@Component
public class JBillingCacheImpl implements JBillingCache {

	private Map<String,Object> map = new ConcurrentHashMap<String, Object>();
	
	@Override
	public void put(String key , Object value) {
		map.put(key, value);
	}

	@Override
	public Object get(String key) {
		return map.get(key);
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public void removeKey(String key) {
		map.remove(key);
	}
	
	

}
