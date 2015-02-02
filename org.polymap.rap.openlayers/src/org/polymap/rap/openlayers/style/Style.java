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

import org.polymap.rap.openlayers.base.OpenLayersObject;
import org.polymap.rap.openlayers.util.JSonBuilder;

public class Style extends OpenLayersObject {

	public Style(Options options) {
		create("new ol.style.Style(" + (options != null ? options.asJson().toString() : "") + ")");
	}

	public static Options builder() {
		return new Options();
	}
	
	public static class Options {

		private StrokeStyle stroke;
		private ImageStyle image;
		private FillStyle fill;
		private TextStyle text;

		public Options withFill(FillStyle fill) {
			this.fill = fill;
			return this;
		}
		public Options withImage(ImageStyle image) {
			this.image = image;
			return this;
		}
		public Options withStroke(StrokeStyle stroke) {
			this.stroke = stroke;
			return this;
		}
		public Options withText(TextStyle text) {
			this.text = text;
			return this;
		}

		private JSonBuilder asJson() {
			JSonBuilder json = new JSonBuilder();
			if (fill != null) {
				json.add("fill", fill.getJSObjRef());
			}
			if (image != null) {
				json.add("image", image.getJSObjRef());
			}
			if (stroke != null) {
				json.add("stroke", stroke.getJSObjRef());
			}
			if (text != null) {
				json.add("text", text.getJSObjRef());
			}
			return json;
		}
		
		public Style build() {
			return new Style(this);
		}
	}
}
