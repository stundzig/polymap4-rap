package org.polymap.rap.openlayers.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.rap.json.JsonObject;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.polymap.rap.openlayers.OpenLayersWidget;
import org.polymap.rap.openlayers.base.OpenLayersEventListener;
import org.polymap.rap.openlayers.base.OpenLayersObject;
import org.polymap.rap.openlayers.base_types.Bounds;
import org.polymap.rap.openlayers.base_types.OpenLayersMap;
import org.polymap.rap.openlayers.base_types.Projection;
import org.polymap.rap.openlayers.controls.LayerSwitcherControl;
import org.polymap.rap.openlayers.controls.MousePositionControl;
import org.polymap.rap.openlayers.controls.NavigationControl;
import org.polymap.rap.openlayers.controls.PanZoomBarControl;
import org.polymap.rap.openlayers.controls.ScaleLineControl;
import org.polymap.rap.openlayers.layers.WMSLayer;

public class DemoEntryPoint extends AbstractEntryPoint {

	private final static Log log = LogFactory.getLog(DemoEntryPoint.class);
	private OpenLayersWidget olwidget1;
	private OpenLayersMap map1;
	private OpenLayersWidget olwidget2;
	private OpenLayersMap map2;

	@Override
	protected void createContents(Composite parent) {
		parent.setLayout(new FillLayout());
		// Composite mainComposite = new Composite(parent, SWT.NONE);
		// mainComposite.setLayout(new RowLayout());

		Composite left = new Composite(parent, SWT.BORDER);
		Composite right = new Composite(parent, SWT.BORDER);
		Composite buttons = new Composite(parent, SWT.BORDER);

		createMap(left);
		createMap2(right);
		createButtons(buttons);
	}

	private void createButtons(Composite parent) {
		parent.setLayout(new FillLayout());
		 Button button = new Button(parent, SWT.PUSH);
		 button.setText("World");
		 button.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				map2.addControl(new ScaleLineControl());
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
		 });
	}
	private void createMap(Composite parent) {
		parent.setLayout(new FillLayout());
		// // Button checkbox = new Button(parent, SWT.CHECK);
		// // checkbox.setText("Hello");
		// // Button button = new Button(parent, SWT.PUSH);
		// // button.setText("World");
		olwidget1 = new OpenLayersWidget(parent, SWT.MULTI | SWT.WRAP
				| SWT.BORDER);

		String srs = "EPSG:4326";// Geometries.srs( getCRS() );
		Projection proj = new Projection(srs);
		String units = srs.equals("EPSG:4326") ? "degrees" : "m";
		float maxResolution = srs.equals("EPSG:4326") ? (360 / 256) : 500000;
		// Bounds maxExtent = new Bounds(12.80, 53.00, 14.30, 54.50);
		Bounds bounds = new Bounds(11.0, 50.00, 17.30, 64.50);
//		olwidget1.createMap(proj, proj, units, bounds, maxResolution);
//		olwidget1.prepare();

		map1 = new OpenLayersMap(olwidget1, proj, proj, units, bounds, maxResolution);
		// map.updateSize();

		WMSLayer layer = new WMSLayer(olwidget1, "OSM",
				"http://ows.terrestris.de/osm/service/", "OSM-WMS");
		layer.setIsBaseLayer(true);
		map1.addLayer(layer);
		//
		map1.addControl(new NavigationControl(olwidget1 true));
		map1.addControl(new PanZoomBarControl(olwidget1 ));
		map1.addControl(new LayerSwitcherControl(olwidget1 ));
		map1.addControl(new MousePositionControl(olwidget1));
//		map1.addControl(new ScaleLineControl());
		// map.addControl(new OverviewMapControl(map, layer));

		// map.addControl( new ScaleControl() );
		// map.addControl( new LoadingPanelControl() );

		// map.setRestrictedExtend( maxExtent );
		map1.zoomToExtent(bounds, true);
		map1.zoomTo(2);

		// map events
		// HashMap<String, String> payload = new HashMap<String, String>();
		// payload.put( "left", "event.object.getExtent().toArray()[0]" );
		// payload.put( "bottom", "event.object.getExtent().toArray()[1]" );
		// payload.put( "right", "event.object.getExtent().toArray()[2]" );
		// payload.put( "top", "event.object.getExtent().toArray()[3]" );
		// payload.put( "scale", map1.getJSObjRef() + ".getScale()" );
		// map1.events.register( this, OpenLayersMap.EVENT_MOVEEND, payload );
		// map1.events.register( this, OpenLayersMap.EVENT_ZOOMEND, payload );

	}

	private void createMap2(Composite parent) {
		parent.setLayout(new FillLayout());
		// Button checkbox = new Button(parent, SWT.CHECK);
		// checkbox.setText("Hello");
		// Button button = new Button(parent, SWT.PUSH);
		// button.setText("World");
		olwidget2 = new OpenLayersWidget(parent, SWT.MULTI | SWT.WRAP
				| SWT.BORDER);
		olwidget2.prepare();

		map2 = olwidget2.getMap();
		// map.updateSize();

		WMSLayer layer = new WMSLayer("OSM2",
				"http://ows.terrestris.de/osm/service/", "OSM-WMS");
		layer.setIsBaseLayer(true);
		map2.addLayer(layer);
		//
		map2.addControl(new NavigationControl(true));
		// map.addControl(new LayerSwitcherControl());
		// map.addControl(new ClickControl());
		// map.addControl(new ButtonControl("foo"));
		// map.addControl(new BoxControl());

		//
		// HashMap<String, String> payload = new HashMap<String, String>();
		// map.events.register( this, OpenLayersMap.EVENT_MOVEEND, payload );

		map2.addEventListener(OpenLayersMap.EVENT_MOVEEND,
				new OpenLayersEventListener() {

					@Override
					public void handleEvent(OpenLayersObject src, String name,
							JsonObject properties) {
						log.info(this + ": " + name);
					}

				}, null);

	}
}
