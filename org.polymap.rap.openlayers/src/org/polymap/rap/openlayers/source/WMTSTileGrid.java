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

/**
 * Set the grid pattern for sources accessing WMTS tiled-image servers.
 * 
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.tilegrid.WMTS.html">
 *      OpenLayers Doc</a>
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 *
 */
public class WMTSTileGrid
        extends TileGrid {

    public WMTSTileGrid() {
        super( "ol.tilegrid.WMTS" );
    }
}
