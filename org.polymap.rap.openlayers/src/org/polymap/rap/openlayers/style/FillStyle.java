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
package org.polymap.rap.openlayers.style;

import org.polymap.core.runtime.config.Concern;
import org.polymap.core.runtime.config.Property2;
import org.polymap.rap.openlayers.base.OlObject;
import org.polymap.rap.openlayers.base.OlPropertyConcern;
import org.polymap.rap.openlayers.types.Color;

/**
 * Set Fill style for vector features.
 * 
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.style.Fill.html">OpenLayers Doc</a>
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
