package com.tooltwist.tddproject.widget;

import java.util.Properties;

import tooltwist.ecommerce.AutomaticUrlParametersMode;
import tooltwist.ecommerce.RoutingUIM;
import tooltwist.wbd.CodeInserter;
import tooltwist.wbd.CodeInserterList;
import tooltwist.wbd.GenericMustacheWidget;
import tooltwist.wbd.JavascriptCodeInserter;
import tooltwist.wbd.StylesheetCodeInserter;
import tooltwist.wbd.WbdException;
import tooltwist.wbd.WbdGenerator;
import tooltwist.wbd.WbdGenerator.GenerationMode;
import tooltwist.wbd.WbdSession;
import tooltwist.wbd.WbdWidget;

import com.dinaa.ui.UimData;

public class RatingWidget extends GenericMustacheWidget {
	
	@Override
	protected void init(WbdWidget instance) throws WbdException
	{
		super.init(instance);
	}
	
	@Override
	public Properties getPropertiesForViewHelper(WbdGenerator generator, WbdWidget instance, UimData ud) throws WbdException {
		Properties properties = new Properties();
		properties.setProperty("navpointId", WbdSession.getNavpointId(ud.getCredentials()));
		properties.setProperty("navpointUrl", RoutingUIM.navpointUrl(ud.getCredentials(), WbdSession.getNavpointId(ud.getCredentials()), AutomaticUrlParametersMode.NO_AUTOMATIC_URL_PARAMETERS));
		
		return properties;
	}
	
	public void getCodeInserters(WbdGenerator generator, WbdWidget instance, UimData ud, CodeInserterList codeInserterList) throws WbdException {
		// The normal code inserters are defined here
		super.getCodeInserters(generator, instance, ud, codeInserterList);

		// Other code inserters are defined here
		GenerationMode mode = generator.getMode();
		if (mode == GenerationMode.DESIGN) {
			CodeInserter[] arr = {};
			codeInserterList.add(arr);
		} else if (mode == GenerationMode.PRODUCTION || generator.getMode() == GenerationMode.CONTROLLER) {
			CodeInserter[] arr = {
				new StylesheetCodeInserter(generator, instance, "cssHeader.css"), 
				new JavascriptCodeInserter(generator, instance, "jsHeader.js"),
			};
			codeInserterList.add(arr);
		}
	}
}
