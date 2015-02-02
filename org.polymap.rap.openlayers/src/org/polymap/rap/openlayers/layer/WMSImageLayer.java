package org.polymap.rap.openlayers.layer;

import org.polymap.rap.openlayers.source.ImageWMSSource;

/**
 * only a short hand for ImageLayer + ImageWMSSource
 * 
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 *
 */
public class WMSImageLayer extends ImageLayer {

	public WMSImageLayer(String url, String layers, String crossOrigin) {
		super(new ImageWMSSource(url, layers, crossOrigin));
	}
}
