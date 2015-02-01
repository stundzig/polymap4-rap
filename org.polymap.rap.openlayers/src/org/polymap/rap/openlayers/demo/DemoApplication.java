package org.polymap.rap.openlayers.demo;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.client.WebClient;

public class DemoApplication implements ApplicationConfiguration {

	public void configure(Application application) {
//		application.addStyleSheet("ol3",
//				"/theme/ol.css");

		Map<String, String> properties = new HashMap<String, String>();
		properties.put(WebClient.PAGE_TITLE, "Hello Openlayers");
		properties.put(WebClient.HEAD_HTML, "<link rel='stylesheet' type='text/css' href='http://openlayers.org/en/master/css/ol.css'>");
//		properties.put(WebClient.THEME_ID, "ol3");
		application.addEntryPoint("/ol", DemoEntryPoint.class, properties);
	}
}
