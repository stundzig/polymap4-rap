/*
 * polymap.org
 * Copyright 2009-2015, Polymap GmbH. All rights reserved.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */
package org.polymap.rap.openlayers;

import org.eclipse.core.runtime.Plugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;
import org.osgi.service.http.NamespaceException;
import org.osgi.util.tracker.ServiceTracker;

/**
 * The activator class controls the plug-in life cycle
 */
public class OlPlugin 
        extends Plugin {

	// The plug-in ID
	public static final String         PLUGIN_ID = "org.polymap.rap.openlayers";

	private static OlPlugin            plugin;

	
	public static OlPlugin instance() {
    	return plugin;
    }


	// instance *******************************************
	
//	private ServiceTracker             httpServiceTracker;


    public void start( final BundleContext context ) throws Exception {
        super.start( context );

//        // register HTTP resource
//        httpServiceTracker = new ServiceTracker( context, HttpService.class.getName(), null ) {
//            public Object addingService( ServiceReference reference ) {
//                HttpService httpService = (HttpService)super.addingService( reference );                
//                if (httpService != null) {
//                    try {
//                        httpService.registerResources( "/openlayers", "/openlayers", null );
//                        httpService.registerResources( "/ol_js_addins", "/ol_js_addins", null );
//                    }
//                    catch (NamespaceException e) {
//                        throw new RuntimeException( e );
//                    }
//                }
//                return httpService;
//            }
//        };
//        httpServiceTracker.open();

		plugin = this;
	}


    public void stop( BundleContext context ) throws Exception {
//        httpServiceTracker.close();
//        httpServiceTracker = null;
        
        plugin = null;
        super.stop( context );
    }
	
}
