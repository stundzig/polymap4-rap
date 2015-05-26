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
package org.polymap.rap.openlayers.format;

/**
 * Feature format for reading and writing data in the GeoJSON format.
 * 
 * @see http://openlayers.org/en/master/apidoc/ol.format.GeoJSON.html
 * @author <a href="http://mapzone.io">Steffen Stundzig</a>
 */
public class GeoJSONFormat
        extends FeatureFormat {

    // TODO add properties
    /*
     * defaultDataProjection ol.proj.ProjectionLike Default data projection.
     * geometryName string | undefined Geometry name to use when creating features.
     */

    public GeoJSONFormat() {
        super( "ol.format.GeoJSON" );
    }
}
