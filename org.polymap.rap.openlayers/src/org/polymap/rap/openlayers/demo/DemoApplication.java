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
        properties.put( WebClient.PAGE_TITLE, "RAP Openlayers Demo" );
        properties
                .put( WebClient.HEAD_HTML,
                        "<link rel='stylesheet' type='text/css' href='http://openlayers.org/en/master/css/ol.css'>"
                                + "<link rel='stylesheet' href='http://openlayers.org/en/master/resources/bootstrap/css/bootstrap.min.css' type='text/css'>"
                                + "<link rel='stylesheet' href='http://openlayers.org/en/master/resources/layout.css' type='text/css'>"
                                + "<link rel='stylesheet' href='http://openlayers.org/en/master/resources/bootstrap/css/bootstrap-responsive.min.css' type='text/css'>" );
        // properties.put(WebClient.THEME_ID, "ol3");
        application.addEntryPoint( "/ol", DemoEntryPoint.class, properties );
        application.addEntryPoint( "/ol2", DemoEntryPoint2.class, properties );
        application.addResource( "/demo/polygon-samples.geojson", new ResourceLoader() {

            @Override
            public InputStream getResourceAsStream( String resourceName ) throws IOException {
                return this.getClass().getClassLoader()
                        .getResourceAsStream( "./demo/polygon-samples.geojson" );
            }
        } );
    }
}
