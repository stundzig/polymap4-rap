/*
 * polymap.org Copyright 2009, Polymap GmbH, and individual contributors as indicated
 * by the @authors tag.
 * 
 * This is free software; you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this software; if not, write to the Free Software Foundation, Inc., 51
 * Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF site:
 * http://www.fsf.org.
 */

package org.polymap.rap.openlayers.geom;

import java.util.Arrays;
import java.util.List;

import org.polymap.core.runtime.config.Concern;
import org.polymap.core.runtime.config.Immutable;
import org.polymap.core.runtime.config.Mandatory;
import org.polymap.core.runtime.config.Config2;

import org.polymap.rap.openlayers.base.OlPropertyConcern;
import org.polymap.rap.openlayers.types.Coordinate;

/**
 * Polygon Geometry.
 * 
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.geom.Polygon.html">OpenLayers Doc</a>
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 */
public class PolygonGeometry
        extends SimpleGeometry {

    @Immutable
    @Mandatory
    @Concern(OlPropertyConcern.class)
    Config2<SimpleGeometry,List<Coordinate>> coordinates;


    public PolygonGeometry( Coordinate... coordinates ) {
        this( Arrays.asList( coordinates ) );
    }


    public PolygonGeometry( List<Coordinate> coordinates ) {
        super( "ol.geom.Polygon" );
        this.coordinates.set( coordinates );
    }


    @Override
    protected void create() {
        // XXX warum das geschachtelte array? wenn das nicht ist, dann kommen wir ganz ohne diese methode aus
        super.create( jsClassname, "[" + OlPropertyConcern.propertyAsJson( coordinates.get() ) + "]" );
        
//        Stringer command = new Stringer( "new ", jsClassname, "([[" );
//        boolean afterFirst = false;
//        for (Coordinate coordinate : coordinates.get()) {
//            if (afterFirst) {
//                command.add( ", " );
//            }
//            command.add( coordinate.toJson() );
//            afterFirst = true;
//        }
//        command.add( "]])" );
//
//        super.create( command.toString() );
    }
}
