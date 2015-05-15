package com.tooltwist.tddproject.viewhelper;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import tooltwist.wbd.ViewHelper;

import com.dinaa.data.XData;
import com.dinaa.ui.UimData;

public class RatingViewHelper extends ViewHelper{
	private Map<String, Object> scopes;

	public RatingViewHelper(Properties properties) {
		super(properties);
		scopes = new HashMap<String, Object>();
	}
	
	@Override
	public XData preFetch(UimData ud) throws Exception {
		return super.preFetch(ud);
	}
	
	public Map<String, Object> getScopes() {
		return scopes;
	}

}
