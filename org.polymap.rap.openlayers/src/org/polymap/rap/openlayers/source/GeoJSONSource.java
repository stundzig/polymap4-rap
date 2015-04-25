/*
 * polymap.org
 * Copyright (C) 2009-2014, Polymap GmbH. All rights reserved.
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
package org.polymap.rap.openlayers.source;

import java.util.Arrays;
import java.util.function.Consumer;

import org.polymap.core.runtime.config.Mandatory;

/**
 * Server-rendered images that are available for arbitrary extents and resolutions.
 * 
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.source.GeoJSON.html">OpenLayers Doc</a>
 * @author <a href="http://www.polymap.de">Falko Bräutigam</a>
 */
public class GeoJSONSource 
        extends StaticVectorSource {

    /**
     * Static vector source in GeoJSON format
     *
     * @param initializers Initialize at least all {@link Mandatory} properties.
     */
    public GeoJSONSource( Consumer<GeoJSONSource>... initializers ) {
        super( "ol.source.GeoJSON" );
        Arrays.asList( initializers ).forEach( initializer -> initializer.accept( this ) );
    }

}
