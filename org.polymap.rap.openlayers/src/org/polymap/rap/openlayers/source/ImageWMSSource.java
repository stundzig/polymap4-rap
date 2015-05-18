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
package org.polymap.rap.openlayers.source;

import java.util.Arrays;
import java.util.function.Consumer;

import org.polymap.core.runtime.config.Concern;
import org.polymap.core.runtime.config.ConfigurationFactory;
import org.polymap.core.runtime.config.DefaultString;
import org.polymap.core.runtime.config.Immutable;
import org.polymap.core.runtime.config.Mandatory;
import org.polymap.core.runtime.config.Property2;

import org.polymap.rap.openlayers.base.Jsonable;
import org.polymap.rap.openlayers.base.OlProperty;
import org.polymap.rap.openlayers.base.OlPropertyConcern;

/**
 * Source for WMS servers providing single, untiled images.
 *
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.source.ImageWMS.html">OpenLayers Doc</a>
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 */
public class ImageWMSSource
        extends ImageSource {

    /**
     * 
     */
    public static class RequestParams
            implements Jsonable {
        
        /**
         * Constructs a new instance.
         *
         * @param initializers Initialize at least all {@link Mandatory} properties.
         */
        public RequestParams( Consumer<RequestParams>... initializers ) {
            ConfigurationFactory.inject( this );
            Arrays.asList( initializers ).forEach( initializer -> initializer.accept( this ) );
        }

        @Mandatory
        @OlProperty("LAYERS")
        public Property2<RequestParams,String>  layers;
        
        @DefaultString("")
        @OlProperty("STYLES")
        public Property2<RequestParams,String>  styles;
        
        @DefaultString("1.3.0")
        @OlProperty( "VERSION" )
        public Property2<RequestParams,String>  version;

        @Override
        public Object toJson() {
            return OlPropertyConcern.propertiesAsJson( this );
        }
    }
    
    @Mandatory
    @Immutable
    @Concern(OlPropertyConcern.class)
    public Property2<ImageWMSSource,String>     url;

    @Concern(OlPropertyConcern.class)
    public Property2<ImageWMSSource,String>     crossOrigin;

    @Mandatory
    @Immutable
//    @Concern( OlPropertyConcern.class )
    public Property2<ImageWMSSource,RequestParams> params;

    
    /**
     * Constructs a new instance.
     *
     * @param initializers Initialize at least all {@link Mandatory} properties.
     */
	public ImageWMSSource( Consumer<ImageWMSSource>... initializers ) {
	    super( "ol.source.ImageWMS" );
        Arrays.asList( initializers ).forEach( initializer -> initializer.accept( this ) );

//      create("new ol.source.ImageWMS({url: '" + url + "'" + (crossOrigin != null ? ",crossOrigin:'" +crossOrigin+"," : "") + ", params : { LAYERS : '" + layers + "'}})");
	}

}
