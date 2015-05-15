package com.tooltwist.tddproject.manager;

import tooltwist.misc.TtConfig;

import com.dinaa.util.DinaaUtilUrls;
import com.dinaa.xpc.XDS;
import com.dinaa.xpc.XpcException;
import com.dinaa.xpc.XpcLogin;
import com.dinaa.xpc.XpcSecurity;
import com.google.gson.JsonObject;
import com.tooltwist.tddproject.exception.TDDProjectException;
import com.tooltwist.xdata.XD;
import com.tooltwist.xdata.XDException;

public class RatingManager {

	public RatingManager() {
	}
	
	public XD retrieveEvents() {
		XD result;
		try {
			XpcSecurity credentials = XpcLogin.login(DinaaUtilUrls.USER_TYPE,
					TtConfig.getInternalLoginName(),
					TtConfig.getInternalLoginPassword());
			JsonObject request = new JsonObject();
			request.addProperty("accessToken", "1234aabbccdd");
			result = XDS.call(credentials, "tddproject.events", "retrieve",
					new XD(request.toString()));
		} catch (XpcException e) {
			throw new TDDProjectException(
					"Failed to retrieve valid credentials.", e);
		} catch (XDException e) {
			throw new TDDProjectException(
					"Failed to invoke XDS service: tddprojects.events", e);
		}
		return result;
	}

}
