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

package org.polymap.rap.openlayers.control;

import org.polymap.core.runtime.config.Immutable;
import org.polymap.core.runtime.config.Mandatory;
import org.polymap.core.runtime.config.Property2;
import org.polymap.rap.openlayers.base.OlEventListener;
import org.polymap.rap.openlayers.base.OlMap;

/**
 * @see http://openlayers.org/en/master/apidoc/ol.control.ScaleLine.html
 * 
 * @author stundzig
 *
 */
public class ScaleLineControl
        extends Control {

    public enum Units {
        degrees, imperial, nautical, metric, us
    }

//    @Mandatory
    @Immutable
    public Property2<OlMap,String> className;

//    @Mandatory
    @Immutable
    public Property2<OlMap,String> target;

//    @Mandatory
    @Immutable
    public Property2<OlMap,Units>  units;

//    @Mandatory
    @Immutable
    public Property2<OlMap,Double> minWidth;


    public ScaleLineControl( String cssName, String targetWidget, Units units, Double minWidth ) {
        super( "ol.control.ScaleLine" );
        this.className.set( cssName );
        this.target.set( targetWidget );
        this.units.set( units );
        this.minWidth.set( minWidth );
    }


    public enum EVENT {
        units
    }


    public void addEventListener( EVENT event, OlEventListener listener ) {
        addEventListener( "change:" + event.name(), listener, null );
    }


    public void removeEventListener( EVENT event, OlEventListener listener ) {
        removeEventListener( "change:" + event.name(), listener );
    }
}
