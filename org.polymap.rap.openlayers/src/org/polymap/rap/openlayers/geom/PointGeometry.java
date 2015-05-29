/*
 * polymap.org Copyright (C) 2009-2015 Polymap GmbH and individual contributors as indicated
 * by the @authors tag. All rights reserved.
 * 
 * This is free software; you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation; either version 2.1 of the License, or (at your option) any later
 * version.
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 */
package org.polymap.rap.openlayers.geom;

import org.polymap.core.runtime.config.Property2;
import org.polymap.rap.openlayers.types.Coordinate;
import org.polymap.rap.openlayers.util.Stringer;

/**
 * Point Geometry.
 * 
 * @see http://openlayers.org/en/master/apidoc/ol.geom.Point.html
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 */
public class PointGeometry
        extends SimpleGeometry {

//    @Concern(OlPropertyConcern.class)
    Property2<SimpleGeometry,Coordinate> coordinates;

    public PointGeometry(Coordinate coordinate) {
        super( "ol.geom.Point" );
        this.coordinates.set(coordinate);
    }
    
    @Override
    protected void create() {
        super.create( new Stringer( "new ", jsClassname, "(", coordinates.get().toJson(), ")" ).toString() );
    }
}
