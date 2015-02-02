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
import org.polymap.rap.openlayers.util.JSonBuilder;
import org.polymap.rap.openlayers.util.Stringer;


public class StrokeStyle extends OpenLayersObject {

	public StrokeStyle(Options options) {
		create("new ol.style.Stroke(" + options.asJson() + ")");
	}

	public static Options builder() {
		return new Options();
	}
	
	public static class Options {
		// TODO more options here http://openlayers.org/en/v3.1.1/apidoc/ol.style.Stroke.html?unstable=true
		private String color;
		private Double width;

		public Options withColor(String color) {
			this.color = color;
			return this;
		}

		public Options withColor(int red, int green, int blue, double alpha) {
			if (red < 0 || red > 255 || green < 0 || green > 255 ||blue < 0 || blue > 255 ||alpha < 0 || alpha > 1 ) {
				throw new IllegalStateException("colors must be between 0..255, and alpha between 0.0..1.0");
			}
			this.color = "rgba(" + red + "," + green + "," + blue + "," + alpha +")";
			return this;
		}

		public Options withWidth(Double width) {
			this.width = width;
			return this;
		}

		private JSonBuilder asJson() {
			JSonBuilder json = new JSonBuilder();
			if (color != null) {
				json.addQuoted("color", color);
			}
			if (width != null) {
				json.add("width", width);
			}
			return json;
		}
		
		public StrokeStyle build() {
			return new StrokeStyle(this);
		}
	}
}
