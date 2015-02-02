package org.polymap.rap.openlayers.view;

import java.util.HashMap;
import java.util.Map;

import org.polymap.rap.openlayers.base.OpenLayersEventListener;
import org.polymap.rap.openlayers.base.OpenLayersObject;
import org.polymap.rap.openlayers.types.Coordinate;
import org.polymap.rap.openlayers.types.Projection;

/**
 * http://openlayers.org/en/master/apidoc/ol.View.html
 * 
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 *
 */
public class View extends OpenLayersObject {

	public View() {
		create("new ol.View({ center: [-8161939, 6095025], zoom: 8})");
	}

	public void rotate(Double rotation, Coordinate anchor) {

	}

	public enum EVENT {
		center, resolution, rotation
	}

	/**
	 * The event contains the new center, resolution and rotation
	 * 
	 * @param event
	 * @param listener
	 */
	public void addEventListener(EVENT event, OpenLayersEventListener listener) {
		addEventListener("change:" + event.name(), listener, null);
	}

	public void removeEventListener(EVENT event,
			OpenLayersEventListener listener) {
		removeEventListener("change:" + event.name(), listener);
	}
//
//	public static Params create() {
//		return new Params();
//	}
//
//	public static class Params {
//		private Coordinate view;
//		private Double maxResolution;
//		private Double minResolution;
//		private Integer maxZoom;
//		private Integer minZoom;
//		private Projection projection;
//		private Double resolution;
//		private Double rotation;
//		private Integer zoom;
//
//		private Params() {
//
//		}
//	}

}
