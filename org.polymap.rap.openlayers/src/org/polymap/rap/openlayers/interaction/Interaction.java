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

import org.polymap.rap.openlayers.base.OlObject;

/**
 * 
 * Abstract base class; normally only used for creating subclasses and not
 * instantiated in apps. User actions that change the state of the map. Some are
 * similar to controls, but are not associated with a DOM element. For example,
 * ol.interaction.KeyboardZoom is functionally the same as ol.control.Zoom, but
 * triggered by a keyboard event not a button element event. Although interactions do
 * not have a DOM element, some of them do render vectors and so are visible on the
 * screen.
 * 
 * @see http://openlayers.org/en/master/apidoc/ol.interaction.Interaction.html
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 */
public abstract class Interaction
        extends OlObject {

    public Interaction( String jsClassname ) {
        super( jsClassname );
    }
}
