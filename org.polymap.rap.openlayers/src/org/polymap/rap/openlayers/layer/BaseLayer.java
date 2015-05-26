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
package org.polymap.rap.openlayers.layer;

import org.polymap.core.runtime.config.Check;
import org.polymap.core.runtime.config.Concern;
import org.polymap.core.runtime.config.NumberRangeValidator;
import org.polymap.core.runtime.config.Property2;
import org.polymap.rap.openlayers.OlWidget;
import org.polymap.rap.openlayers.base.OlObject;
import org.polymap.rap.openlayers.base.OlPropertyConcern;
import org.polymap.rap.openlayers.types.Extent;

/**
 * Abstract base class; normally only used for creating subclasses and not
 * instantiated in apps.
 * 
 * @see http://openlayers.org/en/master/apidoc/ol.layer.Base.html
 * @author <a href="http://www.polymap.de">Falko Br�utigam</a>
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 */
public class BaseLayer
        extends OlObject {

    @Check(value = NumberRangeValidator.class, args = { "0", "1" })
    @Concern(OlPropertyConcern.class)
    public Property2<BaseLayer,Float>   opacity;

    /**
     * The visibility flag for the layer and hide/show & redraw accordingly. Fire
     * event unless otherwise specified.
     */
    @Concern(OlPropertyConcern.class)
    public Property2<BaseLayer,Boolean> visible;

    /**
     * The minimum resolution (inclusive) at which this layer will be visible.
     */
    @Concern(OlPropertyConcern.class)
    public Property2<BaseLayer,Float>   minResolution;

    /**
     * The maximun resolution (exclusive) below which this layer will be visible.
     */
    @Concern(OlPropertyConcern.class)
    public Property2<BaseLayer,Float>   maxResolution;

    /**
     * The bounding extent for layer rendering. The layer will not be rendered
     * outside of this extent.
     */
    @Concern(OlPropertyConcern.class)
    public Property2<BaseLayer,Extent>  extent;


    public BaseLayer( OlWidget widget, String jsClassname ) {
        super( widget, jsClassname );
    }

}
