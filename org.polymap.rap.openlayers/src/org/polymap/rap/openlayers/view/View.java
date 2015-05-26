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
package org.polymap.rap.openlayers.view;

import org.polymap.core.runtime.config.Concern;
import org.polymap.core.runtime.config.Mandatory;
import org.polymap.core.runtime.config.Property2;
import org.polymap.rap.openlayers.OlWidget;
import org.polymap.rap.openlayers.base.OlEventListener;
import org.polymap.rap.openlayers.base.OlObject;
import org.polymap.rap.openlayers.base.OlProperty;
import org.polymap.rap.openlayers.base.OlPropertyConcern;
import org.polymap.rap.openlayers.types.Coordinate;
import org.polymap.rap.openlayers.types.Extent;
import org.polymap.rap.openlayers.types.Projection;

/**
 * An ol.View object represents a simple 2D view of the map. This is the object to
 * act upon to change the center, resolution, and rotation of the map.
 * 
 * @see http://openlayers.org/en/master/apidoc/ol.View.html
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 * @author <a href="http://www.polymap.de">Falko Br�utigam</a>
 */
public class View
        extends OlObject {

    public enum EVENT {
        center, resolution, rotation
    }

    // @Mandatory
    @Concern(OlPropertyConcern.class)
    public Property2<View,Projection> projection;

    @OlProperty("center")
    @Concern(OlPropertyConcern.class)
    public Property2<View,Coordinate> center;

    /**
     * The extent that constrains the {@link #center}, in other words, center cannot
     * be set outside this extent. Default is undefined.
     */
    @Concern(OlPropertyConcern.class)
    public Property2<View,Extent>     extent;

    @Concern(OlPropertyConcern.class)
    public Property2<View,Double>     resolution;

    @Concern(OlPropertyConcern.class)
    public Property2<View,Double>     minResolution;

    @Concern(OlPropertyConcern.class)
    public Property2<View,Double>     maxResolution;

    /**
     * Only used if resolution is not defined. Zoom level used to calculate the
     * initial resolution for the view.
     */
    @Concern(OlPropertyConcern.class)
    public Property2<View,Integer>    zoom;

    @Concern(OlPropertyConcern.class)
    public Property2<View,Integer>    minZoom;

    /**
     * The maximum zoom level used to determine the resolution constraint. It is used
     * together with {@link #maxZoom} (or {@link #maxResolution}) and zoomFactor.
     * Default is 28. Note that if {@link #minResolution} is also provided, it is
     * given precedence over maxZoom.
     */
    @Concern(OlPropertyConcern.class)
    public Property2<View,Integer>    maxZoom;


    /**
     * Constructs a new instance.
     *
     * @param initializers Initialize at least all {@link Mandatory} properties.
     */
    public View( OlWidget widget ) {
        super( widget, "ol.View" );
    }


    /**
     * The event contains the new center, resolution and rotation
     * 
     * @param event
     * @param listener
     */
    public void addEventListener( EVENT event, OlEventListener listener ) {
        addEventListener( "change:" + event.name(), listener, null );
    }


    public void removeEventListener( EVENT event, OlEventListener listener ) {
        removeEventListener( "change:" + event.name(), listener );
    }

    // public static class Params {
    // private Coordinate view;
    // private Double maxResolution;
    // private Double minResolution;
    // private Integer maxZoom;
    // private Integer minZoom;
    // private Projection projection;
    // private Double resolution;
    // private Double rotation;
    // private Integer zoom;
    //
    // private Params() {
    //
    // }
    // }

}
