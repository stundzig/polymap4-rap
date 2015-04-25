/*
 * polymap.org
 * Copyright 2009-2013, Polymap GmbH. All rights reserved.
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
package org.polymap.rap.openlayers.base;

import org.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.eclipse.rap.rwt.widgets.WidgetUtil;

import org.polymap.core.runtime.config.Immutable;
import org.polymap.core.runtime.config.Mandatory;
import org.polymap.core.runtime.config.Property2;

import org.polymap.rap.openlayers.OpenLayersWidget;
import org.polymap.rap.openlayers.base.OpenLayersPropertyConcern.Unquoted;
import org.polymap.rap.openlayers.control.Control;
import org.polymap.rap.openlayers.interaction.DrawInteraction;
import org.polymap.rap.openlayers.layer.Layer;
import org.polymap.rap.openlayers.source.Source;
import org.polymap.rap.openlayers.util.Stringer;
import org.polymap.rap.openlayers.view.View;

/**
 * 
 * @see <a href="http://openlayers.org/en/master/apidoc/ol.Map.html">OpenLayers Doc</a>
 * @author <a href="http://www.polymap.de">Falko Bräutigam</a>
 */
public class OpenLayersMap extends OpenLayersObject {

	private final static Log log = LogFactory.getLog(OpenLayersMap.class);
//
//	/** Event: triggered after mouseover the map. */
//	public final static String EVENT_MOUSE_OVER = "mouseover";
//
//	/** Event: triggered after mouseout the map. */
//	public final static String EVENT_MOUSE_OUT = "mouseout";
//
//	/** Event: triggered after mousemove the map. */
//	public final static String EVENT_MOUSE_MOVE = "mousemove";
//
//	/**
//	 * triggered before a layer has been added. The event object will include a
//	 * layer property that references the layer to be added.
//	 **/
//	public final static String EVENT_PREADDLAYER = "preaddlayer";
//
//	/**
//	 * triggered after a layer has been added. The event object will include a
//	 * layer property that references the added layer.
//	 **/
//	public final static String EVENT_ADDLAYER = "addlayer";
//
//	/**
//	 * triggered after a layer has been removed. The event object will include a
//	 * layer property that references the removed layer.
//	 **/
//	public final static String EVENT_REMOVELAYER = "removelayer";
//
//	/**
//	 * triggered after a layer name change, order change, or visibility change
//	 * (due to resolution thresholds). Listeners will receive an event object
//	 * with layer and property properties. The layer property will be a reference
//	 * to the changed layer. The property property will be a key to the changed
//	 * property (name, visibility, or order).
//	 **/
//	public final static String EVENT_CHANGELAYER = "changelayer";
//
//	/** triggered after the start of a drag, pan, or zoom **/
//	public final static String EVENT_MOVESTART = "movestart";
//
//	/** triggered after each drag, pan, or zoom **/
//	public final static String EVENT_MOVE = "move";
//
//	/** triggered after a drag, pan, or zoom completes **/
//	public final static String EVENT_MOVEEND = "moveend";
//
//	/** triggered after a zoom completes **/
//	public final static String EVENT_ZOOMEND = "zoomend";
//
//	/** triggered after a marker has been added **/
//	public final static String EVENT_ADDMARKER = "addmarker";
//
//	/** triggered after a marker has been removed **/
//	public final static String EVENT_REMOVEMARKER = "removemarker";
//
//	/** triggered after markers have been cleared **/
//	public final static String EVENT_CLEARMARKERS = "clearmarkers";
//
//	/** triggered after the base layer changes **/
//	public final static String EVENT_CHANGEBASELAYER = "changebaselayer";
//
//  private ArrayList<Control> controls;

//  private LinkedHashMap<String, Layer> layers;

    // private int[] scales;


	private OpenLayersWidget               widget;
	
    @Mandatory
    @Immutable
    public Property2<OpenLayersMap,View>   view;


	public OpenLayersMap( OpenLayersWidget widget, View view ) {
	    super( "ol.Map" );
		this.widget = widget;
		this.view.set( view );
		widget.setMap( this );
		
//		create_with_widget(new Stringer("new ol.Map({ view: new ol.View({center: ol.proj.transform([37.41, 8.82], 'EPSG:4326', 'EPSG:3857'), zoom: 4}), target: this.createDiv('", WidgetUtil.getId(widget) + "')});").toString(), widget);
	
		JSONObject options = new JSONObject();
        options.put( "view", new Unquoted( view.getJSObjRef() ) );
        options.put( "target", new Unquoted( "this.createDiv('" + WidgetUtil.getId(widget) + "')" ) );
        create( new Stringer( "new ", jsClassname, "(", options.toString(), ");" ).toString() );

//		create(new Stringer("new ol.Map({view: " + view.getJSObjRef() + ", target: this.createDiv('", WidgetUtil.getId(widget) + "')});").toString());
//		callObjFunction("updateSize()");
	}


	public OpenLayersWidget widget() {
		return widget;
	}

	
	public void addLayer(Layer<? extends Source> layer) {
//		layer2add.setWidget(widget);
		execute("addLayer", layer);
	}

	public void removeLayer(Layer<? extends Source> layer) {
		execute("removeLayer", layer);
	}

//	/**
//	 * Move the given layer to the specified (zero-based) index in the layer
//	 * list, changing its z-index in the map display. Use map.getLayerIndex() to
//	 * find out the current index of a layer. Note that this cannot (or at least
//	 * should not) be effectively used to raise base layers above overlays.
//	 * 
//	 * @param layer
//	 * @param index
//	 */
//	public void setLayerIndex(Layer layer, int index) {
//		callObjFunction("setLayerIndex", layer, index);
//	}

	public void addControl(Control control) {
		control.setMap(this);
		execute("addControl", control);
	}
//
//	public void setProxy(String proxy) {
//		execute("OpenLayers.ProxyHost='" + proxy + "';");
//	}

	public void removeControl(Control control2rm) {
		control2rm.setMap(null);
		execute("removeControl", control2rm);
	}
	
	public void addInteraction(DrawInteraction di) {
		execute("addInteraction", di);
	}
	
	public void removeInteraction(DrawInteraction di) {
		execute("removeInteraction", di);
	}
	
//	/**
//	 * This property is what allows OpenLayers to know what scale things are
//	 * being rendered at, which is important for scale-based methods of zooming
//	 * and the Scale display control.
//	 * 
//	 * @param units
//	 *            The map units. Defaults to "degrees". Possible values are
//	 *            "degrees" (or "dd"), "m", "ft", "km", "mi", "inches".
//	 */
//	public void setUnits(String units) {
//		this.units = units;
//		setAttribute("units", units);
//	}
//
//	public void updateSize() {
//		execute(new Stringer("setTimeout( function() {", getJSObjRef(),
//				".updateSize();", "}, 500 );").toString());
//	}	
	
	public enum EVENT {
		layerGroup, size, target, view
	}
	
	/**
	 * The event contains the new center, resolution and rotation
	 * @param event
	 * @param listener
	 */
	public void addEventListener(EVENT event,
			OpenLayersEventListener listener) {
		addEventListener("change:" + event.name(), listener, null );
	}
	
	public void removeEventListener(EVENT event,
			OpenLayersEventListener listener) {
		removeEventListener("change:" + event.name(), listener);
	}

}
