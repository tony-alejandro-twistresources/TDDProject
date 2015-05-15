package com.tooltwist.tddproject.requesthandler;

import java.io.IOException;

import javax.servlet.ServletException;

import com.dinaa.DinaaException;
import com.dinaa.ui.UimHelper;

import tooltwist.wbd.WbdRequestHandler;

public class RatingRequestHandler extends WbdRequestHandler {

	@Override
	public boolean handler(UimHelper uh, String widgetId, String method)
			throws DinaaException, ServletException, IOException {

		return false;
	}

}
