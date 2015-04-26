/*
 * polymap.org Copyright (C) 2009-2014, Polymap GmbH. All rights reserved.
 * 
 * This is free software; you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 */
package org.polymap.rap.openlayers.source;

import java.text.Format;

import org.polymap.core.runtime.config.Concern;
import org.polymap.core.runtime.config.Mandatory;
import org.polymap.core.runtime.config.Property2;

import org.polymap.rap.openlayers.base.OpenLayersEventListener;
import org.polymap.rap.openlayers.base.OpenLayersPropertyConcern;
import org.polymap.rap.openlayers.types.Attribution;
import org.polymap.rap.openlayers.types.Projection;

/**
 * Provides a source of features for vector layers.
 * 
 * @see <a
 *      href="http://openlayers.org/en/master/apidoc/ol.source.Vector.html">OpenLayers
 *      Doc</a>
 * @author <a href="http://www.polymap.de">Falko Bräutigam</a>
 */
public abstract class VectorSource
        extends Source {

    public enum EVENT {
        /**
         * (ol.source.VectorEvent) - Triggered when a feature is added to the source.
         */
        addfeature,
        /**
         * Triggered when the state of the source changes.
         */
        change,
        /**
         * Triggered when a feature is updated.
         */
        changefeature,
        /**
         * Triggered when the clear method is called on the source.
         */
        clear,
        /**
         * Triggered when a feature is removed from the source. See source.clear()
         * for exceptions.
         */
        removefeature;
    }

    @Concern(OpenLayersPropertyConcern.class)
    public Property2<VectorSource,Attribution> attribution;

    @Concern(OpenLayersPropertyConcern.class)
    public Property2<VectorSource,String>      logo;

    @Concern(OpenLayersPropertyConcern.class)
    public Property2<VectorSource,Projection>  projection;

    /**
     * Experimental: The feature format used by the XHR feature loader when url is
     * set. Required if {@link #url} is set, otherwise ignored. Default is undefined.
     */
    public Property2<VectorSource,Format>      format;

    /**
     * Experimental: Setting this option instructs the source to use an XHR loader
     * (see ol.featureloader.xhr) and an ol.loadingstrategy.all for a one-off
     * download of all features from that URL. Requires format to be set as well.
     */
    @Concern(OpenLayersPropertyConcern.class)
    public Property2<VectorSource,String>       url;


    /**
     * Constructs a new instance.
     *
     * @param initializers Initialize at least all {@link Mandatory} properties.
     */
    public VectorSource( String jsClassname ) {
        super( jsClassname );
    }


    public void addEventListener( EVENT event, OpenLayersEventListener listener ) {
        // Map<String, String> props = new HashMap<String, String>();
        // if (event == EVENT.addfeature || event == EVENT.removefeature) {
        // props.put("feature", "event.feature");
        // }
        addEventListener( event.name(), listener, null );
    }


    public void removeEventListener( EVENT event, OpenLayersEventListener listener ) {
        removeEventListener( event.name(), listener );
    }

}
