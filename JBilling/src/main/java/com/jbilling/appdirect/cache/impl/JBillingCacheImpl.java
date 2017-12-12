package com.jbilling.appdirect.cache.impl;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Component;

import com.jbilling.appdirect.cache.JBillingCache;

/**
 * 
 * @author Shashank Pandey
 * Class for maintaining ideal price for every product id
 * and rule json for given deployment
 */
@Component
public class JBillingCacheImpl implements JBillingCache {

	private Map<String,Object> map = new ConcurrentHashMap<String, Object>();
	
	/**
	 * Adds an entry to cache
	 */
	@Override
	public void put(String key , Object value) {
		map.put(key, value);
	}

	/**
	 * Return entry from cache for a given key
	 */
	@Override
	public Object get(String key) {
		return map.get(key);
	}

	/**
	 * Clears entire cache
	 */
	@Override
	public void clear() {
		map.clear();
	}

	/**
	 * Removes specific entry from cache based on key provided			
	 */
	@Override
	public void removeKey(String key) {
		map.remove(key);
	}
	
	

}
