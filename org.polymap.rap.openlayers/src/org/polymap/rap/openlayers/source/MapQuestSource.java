/*
 * polymap.org Copyright (C) 2009-2015, Polymap GmbH. All rights reserved.
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
package org.polymap.rap.openlayers.source;

import org.polymap.core.runtime.config.Concern;
import org.polymap.core.runtime.config.Immutable;
import org.polymap.core.runtime.config.Property;

import org.polymap.rap.openlayers.base.OlProperty;
import org.polymap.rap.openlayers.base.OlPropertyConcern;

/**
 * Layer source for the MapQuest tile server.
 * 
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.source.MapQuest.html">OpenLayers Doc</a>
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 * @author <a href="http://www.polymap.de">Falko Bräutigam</a>
 */
public class MapQuestSource
        extends XYZSource {

    public enum Type {
        osm, sat, hyb
    }

    // TODO properties

    @Immutable
    @OlProperty("layer")
    @Concern(OlPropertyConcern.class)
    public Property<Type> type;


    public MapQuestSource( Type type ) {
        super( "ol.source.MapQuest" );
        this.type.set( type );
    }

}
