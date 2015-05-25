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
package org.polymap.rap.openlayers.style;

import org.polymap.core.runtime.config.Concern;
import org.polymap.core.runtime.config.Property2;
import org.polymap.rap.openlayers.base.OlObject;
import org.polymap.rap.openlayers.base.OlPropertyConcern;
import org.polymap.rap.openlayers.types.Color;

/**
 * @see http://openlayers.org/en/master/apidoc/ol.style.Fill.html
 * 
 *      Set Fill style for vector features.
 * 
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 */
public class FillStyle
        extends OlObject {

    @Concern(OlPropertyConcern.class)
    public Property2<FillStyle,Color> color;


    public FillStyle() {
        super( "ol.style.Fill" );
    }
}
