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


/**
 * 
 * Abstract base class; only used for creating subclasses; do not instantiate in
 * apps, as cannot be rendered.
 * 
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.geom.SimpleGeometry.html">OpenLayers Doc</a>
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 */
public abstract class SimpleGeometry
        extends Geometry {

    // @Immutable
    // @Concern(OlPropertyConcern.class)
    // Property2<SimpleGeometry,GeometryLayout> layout;
    // TODO must be set as parameter in the ctor and not as json property

    public SimpleGeometry( String jsClassname ) {
        super( jsClassname );
    }
}
