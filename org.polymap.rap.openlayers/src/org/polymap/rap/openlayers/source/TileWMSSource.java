/*
 * polymap.org and individual contributors as indicated by the @authors tag.
 * Copyright (C) 2009-2015 
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
import org.polymap.core.runtime.config.Config;
import org.polymap.core.runtime.config.Config2;
import org.polymap.core.runtime.config.ConfigurationException;
import org.polymap.core.runtime.config.DefaultPropertyConcern;
import org.polymap.core.runtime.config.DefaultString;
import org.polymap.core.runtime.config.Immutable;
import org.polymap.core.runtime.config.Mandatory;

import org.polymap.rap.openlayers.base.OlPropertyConcern;
import org.polymap.rap.openlayers.types.Attribution;

/**
 * Layer source for tile data from WMS servers.
 * 
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.source.TileWMS.html">
 *      OpenLayers Doc</a>
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 *
 */
public class TileWMSSource
        extends TileImageSource {

    /**
     * Optional attributions for the source. If provided, these will be used instead
     * of any attribution data advertised by the server. If not provided, any
     * attributions advertised by the server will be used.
     */
    @Concern(OlPropertyConcern.class)
    public Config2<TileWMSSource,List<Attribution>> attributions;

    /**
     * WMS request parameters. At least a LAYERS param is required. STYLES is '' by
     * default. VERSION is 1.3.0 by default. WIDTH, HEIGHT, BBOX and CRS (SRS for WMS
     * version < 1.3.0) will be set dynamically.
     */
    @Mandatory
    @Immutable
    // @Concern( OlPropertyConcern.class )
    public Config2<TileWMSSource,WMSRequestParams>  params;

    /**
     * 
     * The crossOrigin attribute for loaded images. Note that you must provide a
     * crossOrigin value if you are using the WebGL renderer or if you want to access
     * pixel data with the Canvas renderer. See
     * https://developer.mozilla.org/en-US/docs/Web/HTML/CORS_enabled_image for more
     * detail.
     */
    @Concern(OlPropertyConcern.class)
    @DefaultString("Anonymous")
    public Config2<TileWMSSource,String>           crossOrigin;

    /**
     * undefined The size in pixels of the gutter around image tiles to ignore. By
     * setting this property to a non-zero value, images will be requested that are
     * wider and taller than the tile size by a value of 2 x gutter. Defaults to
     * zero. Using a non-zero value allows artifacts of rendering at tile edges to be
     * ignored. If you control the WMS service it is recommended to address
     * "artifacts at tile edges" issues by properly configuring the WMS service. For
     * example, MapServer has a tile_map_edge_buffer configuration parameter for
     * this. See http://mapserver.org/output/tile_mode.html.
     */
    @Concern(OlPropertyConcern.class)
    public Config2<TileWMSSource,Float>            gutter;

    /**
     * Logo.
     */
    @Concern(OlPropertyConcern.class)
    public Config2<TileWMSSource,String>            logo;

    /**
     * Tile grid. Base this on the resolutions, tilesize and extent supported by the
     * server. If this is not defined, a default grid will be used: if there is a
     * projection extent, the grid will be based on that; if not, a grid based on a
     * global extent with origin at 0,0 will be used.
     */
    @Concern(OlPropertyConcern.class)
    public Config2<TileWMSSource,TileGrid>          tileGrid;

    /**
     * WMS service url.
     */
    @Concern(OlPropertyConcern.class)
    @Concern(CheckUrlsConcern.class)  
    public Config2<TileWMSSource,String>            url;

    /**
     * WMS service urls. Use this instead of url when the WMS supports multiple urls
     * for GetMap requests.
     */
    @Concern(OlPropertyConcern.class)
    @Concern(CheckUrlsConcern.class)  
    public Config2<TileWMSSource,List<String>>      urls;


    public TileWMSSource() {
        super( "ol.source.TileWMS" );
    }
    
    
    /**
     * Check mutual exclusion of {@link TileWMSSource#url} and
     * {@link TileWMSSource#urls}.
     */
    public static class CheckUrlsConcern
            extends DefaultPropertyConcern {

        /**
         * Check that *at least* one is set.
         */
        @Override
        public Object doGet( Object obj, Config prop, Object value ) {
            if (value == null) {
                TileWMSSource source = (TileWMSSource)obj;
                if (prop.info().getName().equals( "url" ) && !source.urls.isPresent()) {
                    throw new ConfigurationException( "TileWMSSOurce: set at least one config property: 'url' or 'urls'" );
                }
                if (prop.info().getName().equals( "urls" ) && !source.url.isPresent()) {
                    throw new ConfigurationException( "TileWMSSOurce: set at least one config property: 'url' or 'urls'" );
                }
            }
            return value;
        }

        /**
         * Check that *just* one is set.
         */
        @Override
        public Object doSet( Object obj, Config prop, Object value ) {
            TileWMSSource source = (TileWMSSource)obj;
            if (prop.info().getName().equals( "url" ) && source.urls.isPresent()) {
                throw new ConfigurationException( "TileWMSSOurce: set config property 'url' OR 'urls'" );
            }
            if (prop.info().getName().equals( "urls" ) && source.url.isPresent()) {
                throw new ConfigurationException( "TileWMSSOurce: set config property 'url' OR 'urls'" );
            }
            return value;
        }
        
    }
    
}
