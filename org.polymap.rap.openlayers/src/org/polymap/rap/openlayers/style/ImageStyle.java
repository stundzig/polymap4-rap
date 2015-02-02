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
package org.polymap.rap.openlayers.style;

import org.eclipse.rap.json.JsonObject;
import org.polymap.rap.openlayers.base.OpenLayersObject;

public class ImageStyle extends OpenLayersObject {

	public ImageStyle(StrokeOptions options) {
		create("new ol.style.Stroke(" + options.asJson() + ")");
	}

	public static class StrokeOptions {
		private String color;
		private Double width;

		public StrokeOptions withColor(String color) {
			this.color = color;
			return this;
		}

		public StrokeOptions withUrl(Double width) {
			this.width = width;
			return this;
		}

		public JsonObject asJson() {
			JsonObject json = new JsonObject();
			if (color != null) {
				json.add("color", color);
			}
			if (width != null) {
				json.add("width", width);
			}
			return json;
		}
	}
}
