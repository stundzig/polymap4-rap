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

import org.polymap.core.runtime.config.Concern;
import org.polymap.core.runtime.config.Property2;
import org.polymap.rap.openlayers.base.OlPropertyConcern;

/**
 * Layer source for tile data in TileJSON format.
 * 
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.source.TileJSON.html">OpenLayers Doc</a>
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 *
 */
public class TileJsonSource
        extends TileImageSource {

    @Concern(OlPropertyConcern.class)
    public Property2<TileJsonSource,String> url;

    @Concern(OlPropertyConcern.class)
    public Property2<TileJsonSource,String> crossOrigin;


    // TODO properties

    public TileJsonSource( String url, String crossOrigin ) {
        super( "ol.source.TileJSON" );
        if (crossOrigin == null || crossOrigin.isEmpty()) {
            crossOrigin = "Anonymous";
        }
        this.crossOrigin.set( crossOrigin );
        this.url.set( url );
    }
}
