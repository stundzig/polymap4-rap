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
public class OpenLayersPlugin 
        extends Plugin {

	// The plug-in ID
	public static final String         PLUGIN_ID = "org.polymap.rap.openlayers";

	private static OpenLayersPlugin    plugin;

    private ServiceTracker             httpServiceTracker;

	
	public OpenLayersPlugin() {
	}


    public void start( final BundleContext context )
    throws Exception {
        super.start( context );

        // register HTTP resource
        httpServiceTracker = new ServiceTracker( context, HttpService.class.getName(), null ) {
            public Object addingService( ServiceReference reference ) {
                HttpService httpService = (HttpService)super.addingService( reference );                
                if (httpService != null) {
                    try {
                        httpService.registerResources( "/openlayers", "/openlayers", null );
                        httpService.registerResources( "/ol_js_addins", "/ol_js_addins", null );
                    }
                    catch (NamespaceException e) {
                        throw new RuntimeException( e );
                    }
                }
                return httpService;
            }
        };
        httpServiceTracker.open();

		plugin = this;
	}


    public void stop( BundleContext context )
    throws Exception {
        httpServiceTracker.close();
        httpServiceTracker = null;
        
        plugin = null;
        super.stop( context );
    }

    
	public static OpenLayersPlugin getDefault() {
		return plugin;
	}
	
}
