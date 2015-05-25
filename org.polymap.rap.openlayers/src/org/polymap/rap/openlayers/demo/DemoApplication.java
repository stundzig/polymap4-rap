package org.polymap.rap.openlayers.demo;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.client.WebClient;
import org.eclipse.rap.rwt.service.ResourceLoader;

public class DemoApplication
        implements ApplicationConfiguration {

    public void configure( Application application ) {
        // application.addStyleSheet("ol3",
        // "/theme/ol.css");

        Map<String,String> properties = new HashMap<String,String>();
        properties.put( WebClient.PAGE_TITLE, "Polymap RAP Openlayers Demo" );
        properties
                .put( WebClient.HEAD_HTML,
                        "<link rel='stylesheet' type='text/css' href='/ol_css/ol-3.5.0.css'>"
                                + "<link rel='stylesheet' href='/ol_css/bootstrap-3.3.4.min.css' type='text/css'>" );
        // properties.put(WebClient.THEME_ID, "ol3");
        application.addEntryPoint( "/ol", DemoEntryPoint.class, properties );
        application.addEntryPoint( "/ol2", DemoEntryPoint2.class, properties );
        application.addResource( "/demo/polygon-samples.geojson", resourceName -> {
            return load( "./demo/polygon-samples.geojson" );
        } );
//        application.addResource( "/js/ol.js", resourceName -> {
//            return load( "./resources/js/ol-3.5.0.js" );
//        } );
    }


    private InputStream load( String resourceName ) {
        return this.getClass().getClassLoader().getResourceAsStream( resourceName );
    }
}
