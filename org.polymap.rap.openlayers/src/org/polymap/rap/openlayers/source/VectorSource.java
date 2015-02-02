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
package org.polymap.rap.openlayers.source;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.rap.json.JsonArray;
import org.polymap.rap.openlayers.base.OpenLayersEventListener;
import org.polymap.rap.openlayers.source.GeoJSONSource.GeoJSONSourceOptions;
import org.polymap.rap.openlayers.types.Attribution;
import org.polymap.rap.openlayers.util.JSonBuilder;

public class VectorSource extends Source {

	public enum EVENT {
		/**
		 * (ol.source.VectorEvent) - Triggered when a feature is added to the
		 * source.
		 */
		addfeature,
		/**
		 * Triggered when the state of the source changes.
		 */
		change,
		/**
		 * Triggered when a feature is updated.
		 */
		changefeature,
		/**
		 * Triggered when the clear method is called on the source.
		 */
		clear,
		/**
		 * Triggered when a feature is removed from the source. See
		 * source.clear() for exceptions.
		 */
		removefeature;
	}

	protected String getJSClass() {
		return "ol.source.Vector";
	}

	public VectorSource(VectorSourceOptions options) {
		create("new " + getJSClass() + "(" + options.asJson().toString() + ")");
	}

	public void addEventListener(EVENT event, OpenLayersEventListener listener) {
		// Map<String, String> props = new HashMap<String, String>();
		// if (event == EVENT.addfeature || event == EVENT.removefeature) {
		// props.put("feature", "event.feature");
		// }
		addEventListener(event.name(), listener, null);
	}

	public void removeEventListener(EVENT event,
			OpenLayersEventListener listener) {
		removeEventListener(event.name(), listener);
	}

	public static VectorSourceOptions builder() {
		return new VectorSourceOptions();
	}

	public static class VectorSourceOptions {

		private List<String> attributions = new ArrayList<String>();/*-{ this.attributions=attributions; }-*/;
		private String logo; /*-{  this.logo=logo; }-*/;

		/**
		 * Destination projection. If provided, features will be transformed to
		 * this projection. If not provided, features will not be transformed
		 *
		 * @param projection
		 */
		private String projectionLike; /*-{ this.projection=projection;  }-*/

		public VectorSourceOptions withAttribution(String html) {
			return withAttribution(new Attribution(html));
		}

		public VectorSourceOptions withAttribution(Attribution attribution) {
			attributions.add(attribution.asJson());
			return this;
		}

		public VectorSourceOptions withLogo(String logo) {
			this.logo = logo;
			return this;
		}

		public VectorSourceOptions withProjection(String projection) {
			this.projectionLike = projection;
			return this;
		}

		public JSonBuilder asJson() {
			JSonBuilder json = new JSonBuilder();
			if (logo != null) {
				json.addQuoted("logo", logo);
			}
			if (projectionLike != null) {
				json.addQuoted("projection", projectionLike);
			}
			if (!attributions.isEmpty()) {
				json.add("attributions", attributions);
			}
			return json;
		}

		public VectorSource build() {
			return new VectorSource(this);
		}
	}
}
