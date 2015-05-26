/*
 * polymap.org Copyright 2009-2015, Polymap GmbH. All rights reserved.
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
package org.polymap.rap.openlayers.types;

import org.polymap.core.runtime.config.Concern;
import org.polymap.core.runtime.config.Immutable;
import org.polymap.core.runtime.config.Property;
import org.polymap.rap.openlayers.OlWidget;
import org.polymap.rap.openlayers.base.OlObject;
import org.polymap.rap.openlayers.base.OlPropertyConcern;

/**
 * @see http://openlayers.org/en/master/apidoc/ol.proj.Projection.html
 * @author <a href="http://www.polymap.de">Falko Br�utigam</a>
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 */
public class Projection
        extends OlObject {

    /**
     * Projection units.
     */
    public enum Units {
        degrees, ft, m, pixels
    }

    @Immutable
    @Concern(OlPropertyConcern.class)
    public Property<String> code;

    @Immutable
    @Concern(OlPropertyConcern.class)
    public Property<Units>  units;


    public Projection( OlWidget widget, String code, Units units ) {
        super( widget, "ol.proj.Projection" );
        this.code.set( code );
        this.units.set( units );
        // create();
    }

}
