package org.polymap.rap.openlayers.layer;

import org.polymap.rap.openlayers.base.OpenLayersObject;


public class Base extends OpenLayersObject {

	/**
	 * Set the visibility flag for the layer and hide/show & redraw accordingly.
	 * Fire event unless otherwise specified.
	 */
	public void setVisibe(Boolean visible) {
		execute("setVisible", visible);
	}

	public void setOpacity(double newOpacity) {
		execute("setOpacity", newOpacity);
	}

	public void setMaxResolution(double resolution) {
		execute("setMaxResolution", resolution);
	}

	public void setMinResolution(double resolution) {
		execute("setMinResolution", resolution);
	}

}
