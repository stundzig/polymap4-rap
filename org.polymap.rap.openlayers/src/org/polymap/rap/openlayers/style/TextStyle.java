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
import org.polymap.core.runtime.config.Config2;
import org.polymap.rap.openlayers.base.OlObject;
import org.polymap.rap.openlayers.base.OlPropertyConcern;

/**
 * Set text style for vector features.
 * 
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.style.Text.html">OpenLayers Doc</a>
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 */
public class TextStyle
        extends OlObject {

    public enum Align {
        center, end, left, right, start
    }


    public enum Baseline {
        alphabetic, bottom, hanging, ideographic, middle, top
    }

    /*
     * <label>Rotation: </label> <select id="points-rotation"> <option
     * value="0">0°</option> <option value="0.785398164">45°</option> <option
     * value="1.570796327">90°</option> </select> public enum Rotation { "0°",
     * "90°"alphabetic, bottom, hanging, ideographic, middle, top }
     */

    // TODO add all possible properties

    @Concern(OlPropertyConcern.class)
    public Config2<TextStyle,Align>       textAlign;

    @Concern(OlPropertyConcern.class)
    public Config2<TextStyle,Baseline>    textBaseline;

    @Concern(OlPropertyConcern.class)
    public Config2<TextStyle,StrokeStyle> stroke;

    @Concern(OlPropertyConcern.class)
    public Config2<TextStyle,Font>        font;

    @Concern(OlPropertyConcern.class)
    public Config2<TextStyle,String>      text;

    @Concern(OlPropertyConcern.class)
    public Config2<TextStyle,FillStyle>   fill;

    /**
     * Horizontal text offset in pixels. A positive will shift the text right.
     * Default is 0.
     */
    @Concern(OlPropertyConcern.class)
    public Config2<TextStyle,Double>      offsetX;

    /**
     * Vertical text offset in pixels. A positive will shift the text down. Default
     * is 0.
     */
    @Concern(OlPropertyConcern.class)
    public Config2<TextStyle,Double>      offsetY;

    /**
     * Rotation in radians (positive rotation clockwise). Default is 0.
     */
    @Concern(OlPropertyConcern.class)
    public Config2<TextStyle,Double>      rotation;


    public TextStyle() {
        super( "ol.style.Text" );
    }
}
