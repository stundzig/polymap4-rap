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

package org.polymap.rap.openlayers.layer;

import org.polymap.rap.openlayers.source.VectorSource;
import org.polymap.rap.openlayers.style.Style;
import org.polymap.rap.openlayers.util.JSonBuilder;

public class VectorLayer extends Layer<VectorSource> {

	public VectorLayer() {
		this(null);
	}

	public VectorLayer(Options options) {
		create("new ol.layer.Vector("
				+ (options != null ? options.asJson() : "") + ")");
	}

	public static Options builder() {
		return new Options();
	}

	public static class Options {
		// TODO add more options
		// http://openlayers.org/en/v3.1.1/apidoc/ol.layer.Vector.html?unstable=true
		private VectorSource source;
		private Style style;

		public Options withSource(VectorSource source) {
			this.source = source;
			return this;
		}

		public Options withStyle(Style style) {
			this.style = style;
			return this;
		}

		public JSonBuilder asJson() {
			JSonBuilder json = new JSonBuilder();
			if (style != null) {
				json.add("style", style.getJSObjRef());
			}
			if (source != null) {
				json.add("source", source.getJSObjRef());
			}
			return json;
		}

		public VectorLayer build() {
			return new VectorLayer(this);
		}
	}
}
