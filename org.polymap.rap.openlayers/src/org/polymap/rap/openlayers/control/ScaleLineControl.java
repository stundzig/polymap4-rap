/*
 * polymap.org
 * Copyright 2009, Polymap GmbH, and individual contributors as indicated
 * by the @authors tag.
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
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *
 */

package org.polymap.rap.openlayers.control;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.rap.json.JsonObject;
import org.polymap.rap.openlayers.base.OpenLayersEventListener;
import org.polymap.rap.openlayers.view.View.EVENT;

/**
 * @see http://openlayers.org/en/master/apidoc/ol.control.ScaleLine.html
 * 
 * @author stundzig
 *
 */
public class ScaleLineControl extends Control {

	public enum Units {
		degrees, imperial, nautical, metric, us
	}

	public ScaleLineControl(String cssName, String targetWidget, Units units,
			Double minWidth) {
		JsonObject attributes = new JsonObject();
		if (cssName != null) {
			attributes.add("className", cssName);
		}
		if (targetWidget != null) {
			attributes.add("target", targetWidget);
		}
		if (minWidth != null) {
			attributes.add("minWidth", minWidth);
		}
		if (units != null) {
			attributes.add("units", units.name());
		}
		create("new ol.control.ScaleLine(" + attributes + ")");
	}

	public enum EVENT {
		units
	}

	public void addEventListener(EVENT event, OpenLayersEventListener listener) {
		Map<String, String> props = new HashMap<String, String>();
		addEventListener(event.name(), listener, props);
	}

	public void removeEventListener(EVENT event,
			OpenLayersEventListener listener) {
		removeEventListener(event.name(), listener);
	}
}
