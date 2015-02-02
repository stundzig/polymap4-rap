/*
 * polymap.org
 * Copyright (C) 2009-2014, Polymap GmbH. All rights reserved.
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
package org.polymap.rap.openlayers.interaction;

import java.util.HashMap;
import java.util.Map;

import org.polymap.rap.openlayers.base.OpenLayersEventListener;
import org.polymap.rap.openlayers.base.OpenLayersObject;
import org.polymap.rap.openlayers.base.OpenLayersEventListener.PayLoad;
import org.polymap.rap.openlayers.source.VectorSource;
import org.polymap.rap.openlayers.types.GeometryType;

public class DrawInteraction extends OpenLayersObject {
	
	public enum EVENT {
	    active("change:active"), drawend("drawend"), drawstart("drawstart");

	    private String value;

	    private EVENT(String value) {
	        this.value = value;
	    }

	    public String getValue() {
	        return this.value;
	    }
	}
	
	public DrawInteraction(VectorSource source, GeometryType type) {
		create("new ol.interaction.Draw({source: "+ source.getJSObjRef() +", type: /** @type {ol.geom.GeometryType} */ '" + type.name() + "'})");
	}
	
	public void addEventListener(EVENT event, OpenLayersEventListener listener) {
		PayLoad payload = new PayLoad();
		if (event == EVENT.drawend) {
			payload.add("feature", "{}");
			payload.add("feature.type", "theEvent.feature.getGeometry().getType()");
			payload.add("feature.extent", "theEvent.feature.getGeometry().getExtent()");
			payload.add("feature.coordinates", "theEvent.feature.getGeometry().getCoordinates()");
		}
		addEventListener(event.getValue(), listener, payload);
	}

	public void removeEventListener(EVENT event,
			OpenLayersEventListener listener) {
		removeEventListener(event.getValue(), listener);
	}
}
