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
package org.polymap.rap.openlayers.format;

import org.polymap.rap.openlayers.base.OlObject;

/**
 * Abstract base class; normally only used for creating subclasses and not
 * instantiated in apps. Base class for feature formats. {ol.format.Feature}
 * subclasses provide the ability to decode and encode ol.Feature objects from a
 * variety of commonly used geospatial file formats. See the documentation for each
 * format for more details.
 * 
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.format.Feature.html">OpenLayers Doc</a>
 * @author <a href="http://mapzone.io">Steffen Stundzig</a>
 */
public abstract class FeatureFormat
        extends OlObject {

    public FeatureFormat( String jsClassname ) {
        super( jsClassname );
    }
}
