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

import org.polymap.core.runtime.config.ConfigurationFactory;
import org.polymap.core.runtime.config.Config2;
import org.polymap.rap.openlayers.base.Jsonable;

/**
 * A Font for TextStyles based on a weight, a size in px and a font family.
 * 
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.style.Text.html">OpenLayers Doc</a>
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 */
public class Font
        implements Jsonable {

    public enum Family {
        Arial("Arial"), CourierNew("Courier New"), QuattrocentoSans("Quattrocento Sans"), Verdana(
                "Verdana");

        private String value;


        private Family( String value ) {
            this.value = value;
        }


        public String value() {
            return this.value;
        }
    }


    public enum Weight {
        bold, normal
    }

    public Config2<Font,Integer> size;

    public Config2<Font,Family>  family;

    public Config2<Font,Weight>  weight;


    public Font() {
        ConfigurationFactory.inject( this );
    }


    @Override
    public Object toJson() {
        return "'" + (weight.get() == null ? Weight.normal : weight.get()) + " "
                + (size.get() == null ? "10" : size.get()) + "px "
                + (family.get() == null ? Family.Arial.value : family.get().value) + "'";
    }
}
