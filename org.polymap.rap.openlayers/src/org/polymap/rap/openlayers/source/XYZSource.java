/*
 * polymap.org Copyright (C) 2009-2015, Polymap GmbH. All rights reserved.
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
 * Layer source for tile data with URLs in a set XYZ format.
 * 
 * @see http://openlayers.org/en/master/apidoc/ol.source.XYZ.html
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 */
public abstract class XYZSource
        extends TileImageSource {

    public XYZSource( String jsClassname ) {
        super( jsClassname );
    }

}
