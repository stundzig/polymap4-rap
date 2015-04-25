/*
 * polymap.org Copyright 2015, Polymap GmbH. All rights reserved.
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
package org.polymap.rap.openlayers.types;

import org.json.JSONArray;

import org.polymap.core.runtime.config.Check;
import org.polymap.core.runtime.config.NumberRangeValidator;
import org.polymap.core.runtime.config.Property2;

import org.polymap.rap.openlayers.base.Jsonable;

/**
 * A color represented as a short array [red, green, blue, alpha]. red, green, and
 * blue should be integers in the range 0..255 inclusive. alpha should be a float in
 * the range 0..1 inclusive.
 * 
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.html#Color">OpenLayers
 *      Doc</a>
 * @author <a href="http://www.polymap.de">Falko Bräutigam</a>
 */
public class Color
        implements Jsonable {

    @Check(value=NumberRangeValidator.class, args={"0","255"})
    public Property2<Color,Integer> red;

    @Check(value=NumberRangeValidator.class, args={"0","255"})
    public Property2<Color,Integer>        green;

    @Check(value=NumberRangeValidator.class, args={"0","255"})
    public Property2<Color,Integer>        blue;

    @Check(value=NumberRangeValidator.class, args={"0","1"})
    public Property2<Color,Float>          alpha;


    public Color( int r, int g, int b ) {
        this( r, g, b, 1 );
    }

    public Color( int r, int g, int b, float a ) {
        this.red.set( r );
        this.green.set( g );
        this.blue.set( b );
        this.alpha.set( a );
    }

    @Override
    public Object toJson() {
        return new JSONArray().put( red.get() ).put( green.get() ).put( blue.get() ).put( alpha.get() );
    }

}
