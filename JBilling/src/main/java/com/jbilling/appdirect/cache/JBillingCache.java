package com.jbilling.appdirect.cache;

public interface JBillingCache {
	
	public void put(String key , Object value);
	public Object get(String key);
	public void clear();
	public void removeKey(String key);
}
