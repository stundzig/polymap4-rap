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
package org.polymap.rap.openlayers.control;

import org.polymap.rap.openlayers.OlWidget;
import org.polymap.rap.openlayers.base.OlMap;
import org.polymap.rap.openlayers.base.OlObject;

/**
 * A control is a visible widget with a DOM element in a fixed position on the
 * screen. They can involve user input (buttons), or be informational only; the
 * position is determined using CSS. By default these are placed in the container
 * with CSS class name ol-overlaycontainer-stopevent, but can use any outside DOM
 * element.
 * 
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.View.html">OpenLayers
 *      Doc</a>
 * @author <a href="http://www.polymap.de">Falko Bräutigam</a>
 */
public abstract class Control
        extends OlObject {

    //
    // @Mandatory
    // @Immutable
    // public Property2<Control,OlMap> map;

    private OlMap map;


    public Control( OlWidget widget, String jsClassname ) {
        super( widget, jsClassname );
        setMap(widget.map());
    }


    private void setMap( OlMap map ) {
        this.map = map;
        if (map != null) {
            execute( "setMap", map );
        }
    }

}
