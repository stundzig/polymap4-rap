/*
 * polymap.org
 * Copyright (C) 2009-2015 Polymap GmbH. All rights reserved.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */
package org.polymap.rap.demo;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.rap.rwt.application.Application;
import org.eclipse.rap.rwt.application.ApplicationConfiguration;
import org.eclipse.rap.rwt.client.WebClient;

/**
 * 
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 *
 */
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
