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
package org.polymap.rap.openlayers.layer;

import org.polymap.core.runtime.config.Mandatory;
import org.polymap.rap.openlayers.OlWidget;
import org.polymap.rap.openlayers.source.ImageSource;

/**
 * Server-rendered images that are available for arbitrary extents and resolutions.
 * 
 * @see <a
 *      href="http://openlayers.org/en/master/apidoc/ol.layer.Image.html">OpenLayers
 *      Doc</a>
 * @author <a href="http://www.polymap.de">Falko Br�utigam</a>
 */
public class ImageLayer
        extends Layer<ImageSource> {

    /**
     * Constructs a new instance.
     */
    public ImageLayer( OlWidget widget ) {
        super( widget, "ol.layer.Image" );
    }
}
