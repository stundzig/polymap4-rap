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
package org.polymap.rap.openlayers.interaction;

/**
 * Base class that calls user-defined functions on down, move and up events. This
 * class also manages "drag sequences".
 * 
 * When the handleDownEvent user function returns true a drag sequence is started.
 * During a drag sequence the handleDragEvent user function is called on move events.
 * The drag sequence ends when the handleUpEvent user function is called and returns
 * false.
 * 
 * @see http://openlayers.org/en/master/apidoc/ol.interaction.Pointer.html
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 */
public abstract class PointerInteraction
        extends Interaction {

    public PointerInteraction( String jsClassname ) {
        super( jsClassname );
    }
}
