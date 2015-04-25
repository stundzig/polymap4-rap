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
package org.polymap.rap.openlayers.types;

import org.polymap.core.runtime.config.Mandatory;
import org.polymap.core.runtime.config.Property;

import org.polymap.rap.openlayers.base.Jsonable;

/**
 * An attribution for a layer source.
 * 
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.Attribution.html">OpenLayers Doc</a>
 * @author <a href="http://www.polymap.de">Falko Bräutigam</a>
 */
public class Attribution
        implements Jsonable {

    @Mandatory
    public Property<String>         html;


	public Attribution( String html ) {
		this.html.set( html );
	}

	@Override
    public Object toJson() {
        return "new ol.Attribution({ html: '" + html.get() + "'  })";
    }

}
