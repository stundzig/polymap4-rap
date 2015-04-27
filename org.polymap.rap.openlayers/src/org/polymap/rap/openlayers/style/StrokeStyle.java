/*
 * polymap.org
 * Copyright (C) 2009-2015, Polymap GmbH. All rights reserved.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */
package org.polymap.rap.openlayers.style;

import java.util.Arrays;
import java.util.function.Consumer;

import org.polymap.core.runtime.config.Concern;
import org.polymap.core.runtime.config.Mandatory;
import org.polymap.core.runtime.config.Property2;

import org.polymap.rap.openlayers.base.OlObject;
import org.polymap.rap.openlayers.base.OlPropertyConcern;
import org.polymap.rap.openlayers.types.Color;

/**
 * Set stroke style for vector features. Note that the defaults given are the Canvas
 * defaults, which will be used if option is not defined. The get functions return
 * whatever was entered in the options; they will not return the default.
 * 
 * @see <a
 *      href="http://openlayers.org/en/master/apidoc/ol.style.Stroke.html">OpenLayers
 *      Doc</a>
 * @author <a href="http://www.polymap.de">Falko Bräutigam</a>
 */
public class StrokeStyle 
        extends OlObject {

    @Concern(OlPropertyConcern.class)
    public Property2<StrokeStyle,Float>     width;

    @Concern(OlPropertyConcern.class)
    public Property2<StrokeStyle,Color>     color;

    
    /**
     * Constructs a new instance.
     *
     * @param initializers Initialize at least all {@link Mandatory} properties.
     */
    public StrokeStyle( Consumer<StrokeStyle>... initializers ) {
        super( "ol.style.Stroke" );
        Arrays.asList( initializers ).forEach( initializer -> initializer.accept( this ) );
    }

}
