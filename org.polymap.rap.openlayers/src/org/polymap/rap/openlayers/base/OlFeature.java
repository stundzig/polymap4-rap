/*
 * polymap.org Copyright (C) 2009-2015 Polymap GmbH. All rights reserved.
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
package org.polymap.rap.openlayers.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.polymap.core.runtime.config.Concern;
import org.polymap.core.runtime.config.Immutable;
import org.polymap.core.runtime.config.Property2;
import org.polymap.rap.openlayers.geom.Geometry;
import org.polymap.rap.openlayers.style.Style;
import org.polymap.rap.openlayers.types.Coordinate;

/**
 * 
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.Feature.html">OpenLayers Doc</a>
 * 
 * @author <a href="http://mapzone.io">Steffen Stundzig</a>
 */
public class OlFeature
        extends OlObject {

    private final static Log               log = LogFactory.getLog( OlFeature.class );

    // @Mandatory
    // @Immutable
    @Concern(OlPropertyConcern.class)
    public Property2<OlFeature,Geometry>   geometry;

    @Concern(OlPropertyConcern.class)
    public Property2<OlFeature,Coordinate> labelPoint;

    @Immutable
    @Concern(OlPropertyConcern.class)
    @OlSetter("setStyle")
    public Property2<OlFeature,Style>      style;

    @Immutable
    @Concern(OlPropertyConcern.class)
    // defaults to "geometry"
    public Property2<OlFeature,String>     geometryName;

    // @Mandatory
    // @Immutable
    @Concern(OlPropertyConcern.class)
    public Property2<OlFeature,String>     name;

    @Immutable
    @Concern(OlPropertyConcern.class)
    public Property2<OlFeature,String>     id;


    public OlFeature() {
        this( null );
    }


    public OlFeature( String geometryName ) {
        super( "ol.Feature" );
        this.geometryName.set( geometryName == null ? "geometry" : geometryName );
    }
//
//
//    // TODO cannot be set via property
//    public void setStyle( Style style ) {
//        execute("setStyle", style);
//    }
}
