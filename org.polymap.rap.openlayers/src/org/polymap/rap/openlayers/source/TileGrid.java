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
import org.polymap.rap.openlayers.base.OlObject;
import org.polymap.rap.openlayers.base.OlPropertyConcern;
import org.polymap.rap.openlayers.types.Coordinate;
import org.polymap.rap.openlayers.types.Size;

/**
 * Base class for setting the grid pattern for sources accessing tiled-image servers.
 * 
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.tilegrid.TileGrid.html">
 *      OpenLayers Doc</a>
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 *
 */
public abstract class TileGrid
        extends OlObject {

    /**
     * The tile grid origin, i.e. where the x and y axes meet ([z, 0, 0]). Tile
     * coordinates increase left to right and upwards. If not specified, extent or
     * origins must be provided.
     */
    @Concern(OlPropertyConcern.class)
    public Config2<TileGrid,Coordinate>       origin;

    /**
     * Tile grid origins, i.e. where the x and y axes meet ([z, 0, 0]), for each zoom
     * level. If given, the array length should match the length of the resolutions
     * array, i.e. each resolution can have a different origin. Tile coordinates
     * increase left to right and upwards. If not specified, extent or origin must be
     * provided.
     */
    @Concern(OlPropertyConcern.class)
    public Config2<TileGrid,List<Coordinate>> origins;

    /**
     * Resolutions. The array index of each resolution needs to match the zoom level.
     * This means that even if a minZoom is configured, the resolutions array will
     * have a length of maxZoom + 1.
     */
    @Concern(OlPropertyConcern.class)
    public Config2<TileGrid,List<Double>>     resolutions;

    /**
     * Tile size. Default is [256, 256].
     */
    @Concern(OlPropertyConcern.class)
    public Config2<TileGrid,Size>             tileSize;

    /**
     * Tile sizes. If given, the array length should match the length of the
     * resolutions array, i.e. each resolution can have a different tile size.
     */
    @Concern(OlPropertyConcern.class)
    public Config2<TileGrid,List<Size>>       tileSizes;


    public TileGrid( String jsClassname ) {
        super( jsClassname );
    }
}
