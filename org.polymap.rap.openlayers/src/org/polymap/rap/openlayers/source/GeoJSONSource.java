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

import org.polymap.rap.openlayers.types.Attribution;
import org.polymap.rap.openlayers.util.JSonBuilder;

public class GeoJSONSource extends StaticVectorSource {

	@Override
	protected String getJSClass() {
		return "ol.source.GeoJSON";
	}

	public GeoJSONSource(GeoJSONSourceOptions options) {
		super(options);
	}

	public static GeoJSONSourceOptions builder() {
		return new GeoJSONSourceOptions();
	}

	public static class GeoJSONSourceOptions extends VectorSourceOptions {
		private List<String> urls = new ArrayList<String>();
		private String defaultProjectionLike;
		private String text;

		@Override
		public GeoJSONSourceOptions withAttribution(Attribution attribution) {
			super.withAttribution(attribution);
			return this;
		}

		@Override
		public GeoJSONSourceOptions withAttribution(String html) {
			super.withAttribution(html);
			return this;
		}

		@Override
		public GeoJSONSourceOptions withLogo(String logo) {
			super.withLogo(logo);
			return this;
		}

		@Override
		public GeoJSONSourceOptions withProjection(String projection) {
			super.withProjection(projection);
			return this;
		}

		public GeoJSONSourceOptions withUrl(String url) {
			urls.add(url);
			return this;
		}

		public GeoJSONSourceOptions withDefaultProjection(String projection) {
			this.defaultProjectionLike = projection;
			return this;
		}

		public GeoJSONSourceOptions withText(String text) {
			this.text = text;
			return this;
		}

		@Override
		public JSonBuilder asJson() {
			JSonBuilder json = super.asJson();
			if (text != null) {
				json.addQuoted("text", text);
			}
			if (defaultProjectionLike != null) {
				json.addQuoted("defaultProjection", defaultProjectionLike);
			}
			if (!urls.isEmpty()) {
				if (urls.size() == 1) {
					json.addQuoted("url", urls.get(0));
				} else {
					json.addQuoted("urls", urls);
				}
			}
			return json;
		}

		public GeoJSONSource build() {
			return new GeoJSONSource(this);
		}
	}
}
