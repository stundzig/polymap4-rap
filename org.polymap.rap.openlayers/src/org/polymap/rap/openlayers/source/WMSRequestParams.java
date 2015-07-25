/*
 * polymap.org and individual contributors as indicated by the @authors tag.
 * Copyright (C) 2009-2015 
 * All rights reserved.
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

import org.polymap.core.runtime.config.Config2;
import org.polymap.core.runtime.config.ConfigurationFactory;
import org.polymap.core.runtime.config.DefaultString;
import org.polymap.core.runtime.config.Mandatory;
import org.polymap.rap.openlayers.base.Jsonable;
import org.polymap.rap.openlayers.base.OlProperty;
import org.polymap.rap.openlayers.base.OlPropertyConcern;

/**
 * WMS request parameters. At least a LAYERS param is required. STYLES is '' by
 * default. VERSION is 1.3.0 by default. WIDTH, HEIGHT, BBOX and CRS (SRS for WMS
 * version < 1.3.0) will be set dynamically.
 * 
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 *
 */
public class WMSRequestParams
        implements Jsonable {

    /**
     * Constructs a new instance.
     */
    public WMSRequestParams() {
        ConfigurationFactory.inject( this );
    }

    @Mandatory
    @OlProperty("LAYERS")
    public Config2<WMSRequestParams,String> layers;

    @DefaultString("")
    @OlProperty("STYLES")
    public Config2<WMSRequestParams,String> styles;

    @DefaultString("1.3.0")
    @OlProperty("VERSION")
    public Config2<WMSRequestParams,String> version;


    @Override
    public Object toJson() {
        return OlPropertyConcern.propertiesAsJson( this );
    }
}