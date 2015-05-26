package org.polymap.rap.demo;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.client.WebClient;

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
        application.addEntryPoint( "/demo", DemoEntryPoint.class, properties );
        application.addEntryPoint( "/demo2", DemoEntryPoint2.class, properties );
        
        application.addResource( "/polygon-samples.geojson", resourceName -> {
            return load( "./resources/polygon-samples.geojson" );
        } );
//        application.addResource( "/js/ol.js", resourceName -> {
//            return load( "./resources/js/ol-3.5.0.js" );
//        } );
    }


    private InputStream load( String resourceName ) {
        return this.getClass().getClassLoader().getResourceAsStream( resourceName );
    }
}
