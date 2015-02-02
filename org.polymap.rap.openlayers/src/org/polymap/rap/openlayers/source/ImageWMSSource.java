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

/**
 * http://openlayers.org/en/master/apidoc/ol.source.ImageWMS.html
 * 
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 *
 */
public class ImageWMSSource extends ImageSource {

	public ImageWMSSource(String url, String layers, String crossOrigin) {
//		if (crossOrigin == null || crossOrigin.isEmpty()) {
//			crossOrigin = "Anonymous";
//		}
		create("new ol.source.ImageWMS({url: '" + url + "'" + (crossOrigin != null ? ",crossOrigin:'" +crossOrigin+"," : "") + ", params : { LAYERS : '" + layers + "'}})");
	}
}
