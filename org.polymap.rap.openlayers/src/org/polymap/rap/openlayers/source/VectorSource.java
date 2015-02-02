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

import java.util.HashMap;
import java.util.Map;

import org.polymap.rap.openlayers.base.OpenLayersEventListener;

public abstract class VectorSource extends Source {

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
		 * Triggered when a feature is removed from the source. See source.clear() for exceptions.
		 */
		removefeature;
	}
//
//	public VectorSource() {
//		create("new ol.source.Vector()");
//	}

	public void addEventListener(EVENT event, OpenLayersEventListener listener) {
//		Map<String, String> props = new HashMap<String, String>();
//		if (event == EVENT.addfeature || event == EVENT.removefeature) {
//			props.put("feature", "event.feature");
//		}
		addEventListener(event.name(), listener, null);
	}

	public void removeEventListener(EVENT event,
			OpenLayersEventListener listener) {
		removeEventListener(event.name(), listener);
	}
	
	public class VectorSourceOptions {
		 
	}
}
