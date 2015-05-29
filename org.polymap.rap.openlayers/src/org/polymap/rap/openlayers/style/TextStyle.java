/*
 * polymap.org Copyright (C) 2009-2015 Polymap GmbH and individual contributors as
 * indicated by the @authors tag. All rights reserved.
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
import org.polymap.core.runtime.config.Immutable;
import org.polymap.core.runtime.config.Mandatory;
import org.polymap.core.runtime.config.Property2;
import org.polymap.rap.openlayers.base.OlObject;
import org.polymap.rap.openlayers.base.OlPropertyConcern;

/**
 * Set text style for vector features.
 * 
 * @see http://openlayers.org/en/master/apidoc/ol.style.Text.html
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 */
public class TextStyle
        extends OlObject {

    // TODO add all possible properties

    @Concern(OlPropertyConcern.class)
    public Property2<CircleStyle,StrokeStyle> stroke;

    @Concern(OlPropertyConcern.class)
    public Property2<CircleStyle,String>      font;

    @Concern(OlPropertyConcern.class)
    public Property2<CircleStyle,FillStyle>   fill;


    public TextStyle() {
        super( "ol.style.Text" );
    }
}
