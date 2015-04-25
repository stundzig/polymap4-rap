/*
 * polymap.org
 * Copyright 2009-2015, Polymap GmbH. All rights reserved.
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
package org.polymap.rap.openlayers.layer;

import org.polymap.rap.openlayers.base.OpenLayersObject;


public class Base
        extends OpenLayersObject {

	public Base( String jsClassname ) {
        super( jsClassname );
    }

    /**
	 * Set the visibility flag for the layer and hide/show & redraw accordingly.
	 * Fire event unless otherwise specified.
	 */
	public void setVisibe(Boolean visible) {
		execute("setVisible", visible);
	}

	public void setOpacity(double newOpacity) {
		execute("setOpacity", newOpacity);
	}

	public void setMaxResolution(double resolution) {
		execute("setMaxResolution", resolution);
	}

	public void setMinResolution(double resolution) {
		execute("setMinResolution", resolution);
	}

}
