package com.tooltwist.tddproject.xds;

import java.io.IOException;

import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;

import com.dinaa.xpc.XDService;
import com.dinaa.xpc.XpcSecurity;
import com.tooltwist.xdata.XD;
import com.tooltwist.xdata.XDException;

public class RatingXDS implements XDService {

	public static final String EVENTS_URL = "http://www.myapi.com/events";

	@Override
	public void init(XD config) {
	}

	@Override
	public XD service(XpcSecurity credentials, XD input) throws XDException {
		String json = null;
		try {
			json = Request.Post(EVENTS_URL)
					.addHeader("accept", "application/json")
					.bodyString(input.getString(), ContentType.APPLICATION_JSON)
					.connectTimeout(3000).socketTimeout(3000).execute()
					.returnContent().asString();
		} catch (IOException e) {
			throw new XDException(e);
		}

		XD xd = new XD(json);
		xd.getSelector();
		return xd;
	}
}
