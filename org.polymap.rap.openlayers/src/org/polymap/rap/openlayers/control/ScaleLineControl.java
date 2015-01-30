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

import org.polymap.rap.openlayers.util.Stringer;

/**
 * @see http://openlayers.org/en/v3.1.1/apidoc/ol.control.ScaleLine.html
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
		Stringer c = new Stringer("new ol.control.ScaleLine({");
		boolean commaBefore = false;
		if (cssName != null) {
			c.add("className : '", cssName, "'");
			commaBefore = true;
		}
		if (targetWidget != null) {
			if (commaBefore) {
				c.add(", ");
			}
			c.add("target : '", targetWidget, "'");
			commaBefore = true;
		}
		if (minWidth != null) {
			if (commaBefore) {
				c.add(", ");
			}
			c.add("minWidth : '", minWidth, "'");
			commaBefore = true;
		}
		if (units != null) {
			if (commaBefore) {
				c.add(", ");
			}
			c.add("units : '", units.name(), "'");
		}
 		c.add("});");
		super.create(c.toString());
	}
}
