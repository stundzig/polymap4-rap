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
import org.polymap.core.runtime.config.Property2;
import org.polymap.rap.openlayers.OlWidget;
import org.polymap.rap.openlayers.base.OlEventListener;
import org.polymap.rap.openlayers.base.OlMap;

/**
 * A control displaying rough x-axis distances, calculated for the center of the
 * viewport. No scale line will be shown when the x-axis distance cannot be
 * calculated in the view projection (e.g. at or beyond the poles in EPSG:4326). By
 * default the scale line will show in the bottom left portion of the map, but this
 * can be changed by using the css selector .ol-scale-line.
 * 
 * @see http://openlayers.org/en/master/apidoc/ol.control.ScaleLine.html
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 */
public class ScaleLineControl
        extends Control {

    public enum Units {
        degrees, imperial, nautical, metric, us
    }

    @Immutable
    public Property2<OlMap,String> className;

    @Immutable
    public Property2<OlMap,String> target;

    @Immutable
    public Property2<OlMap,Units>  units;

    @Immutable
    public Property2<OlMap,Double> minWidth;


    public ScaleLineControl( OlWidget widget, String cssName, Double minWidth ) {
        super( widget, "ol.control.ScaleLine" );
        this.className.set( cssName );
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
