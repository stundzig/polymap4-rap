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

import org.polymap.core.runtime.config.Concern;
import org.polymap.core.runtime.config.Immutable;
import org.polymap.core.runtime.config.Property;
import org.polymap.rap.openlayers.base.OlEventListener;
import org.polymap.rap.openlayers.base.OlEventListener.PayLoad;
import org.polymap.rap.openlayers.base.OlPropertyConcern;
import org.polymap.rap.openlayers.geom.GeometryType;
import org.polymap.rap.openlayers.source.VectorSource;

/**
 * Interaction that allows drawing geometries.
 * 
 * @see http://openlayers.org/en/master/apidoc/ol.interaction.Draw.html
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 */
public class DrawInteraction
        extends Interaction {

    public enum EVENT {
        active("change:active"), drawend("drawend"), drawstart("drawstart");

        private String value;


        private EVENT( String value ) {
            this.value = value;
        }


        public String getValue() {
            return this.value;
        }
    }

    // TODO more properties
    
    @Immutable
    @Concern(OlPropertyConcern.class)
    public Property<VectorSource> source;

    @Immutable
    @Concern(OlPropertyConcern.class)
    public Property<GeometryType> type;


    public DrawInteraction( VectorSource source, GeometryType type ) {
        super( "ol.interaction.Draw" );
        this.source.set( source );
        this.type.set( type );
    }


    public void addEventListener( EVENT event, OlEventListener listener ) {
        PayLoad payload = new PayLoad();
        if (event == EVENT.drawend) {
            payload.add( "feature", "{}" );
            payload.add( "feature.type", "theEvent.feature.getGeometry().getType()" );
            payload.add( "feature.extent", "theEvent.feature.getGeometry().getExtent()" );
            payload.add( "feature.coordinates", "theEvent.feature.getGeometry().getCoordinates()" );
        }
        addEventListener( event.getValue(), listener, payload );
    }


    public void removeEventListener( EVENT event, OlEventListener listener ) {
        removeEventListener( event.getValue(), listener );
    }
}
