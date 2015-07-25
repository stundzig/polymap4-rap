/*
 * Copyright (C) 2009-2015 
 * 
 * polymap.org and individual contributors as indicated by the @authors tag.
 * 
 * All rights reserved.
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

import java.util.List;

import org.polymap.core.runtime.config.Concern;
import org.polymap.core.runtime.config.Config2;
import org.polymap.core.runtime.config.DefaultString;
import org.polymap.core.runtime.config.Mandatory;
import org.polymap.rap.openlayers.base.OlPropertyConcern;
import org.polymap.rap.openlayers.types.Attribution;

/**
 * Layer source for tile data in TileJSON format.
 * 
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.source.TileJSON.html">
 *      OpenLayers Doc</a>
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 *
 */
public class TileJsonSource
        extends TileImageSource {

    /**
     * Optional attributions for the source. If provided, these will be used instead
     * of any attribution data advertised by the server. If not provided, any
     * attributions advertised by the server will be used.
     */
    @Concern(OlPropertyConcern.class)
    public Config2<VectorSource,List<Attribution>> attributions;

    /**
     * URL to the TileJSON file.
     */
    @Concern(OlPropertyConcern.class)
    @Mandatory
    public Config2<TileJsonSource,String>          url;

    /**
     * The crossOrigin attribute for loaded images. Note that you must provide a
     * crossOrigin value if you are using the WebGL renderer or if you want to access
     * pixel data with the Canvas renderer. See
     * https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_enabled_image for more
     * detail.
     */
    @DefaultString("Anonymous")
    @Concern(OlPropertyConcern.class)
    public Config2<TileJsonSource,String>          crossOrigin;


    public TileJsonSource() {
        super( "ol.source.TileJSON" );
    }
}
