package org.polymap.rap.openlayers.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.polymap.rap.openlayers.OpenLayersWidget;
import org.polymap.rap.openlayers.base.OpenLayersEvent;
import org.polymap.rap.openlayers.base.OpenLayersEventListener;
import org.polymap.rap.openlayers.base.OpenLayersMap;
import org.polymap.rap.openlayers.control.ScaleLineControl;
import org.polymap.rap.openlayers.control.ZoomSliderControl;
import org.polymap.rap.openlayers.interaction.DrawInteraction;
import org.polymap.rap.openlayers.layer.ImageLayer;
import org.polymap.rap.openlayers.layer.TileLayer;
import org.polymap.rap.openlayers.layer.VectorLayer;
import org.polymap.rap.openlayers.source.GeoJSONSource;
import org.polymap.rap.openlayers.source.GeoJSONSource.GeoJSONSourceOptions;
import org.polymap.rap.openlayers.source.ImageWMSSource;
import org.polymap.rap.openlayers.source.MapQuestSource;
import org.polymap.rap.openlayers.source.VectorSource;
import org.polymap.rap.openlayers.style.FillStyle;
import org.polymap.rap.openlayers.style.StrokeStyle;
import org.polymap.rap.openlayers.style.Style;
import org.polymap.rap.openlayers.types.GeometryType;
import org.polymap.rap.openlayers.view.View;

public class DemoEntryPoint extends AbstractEntryPoint {

	private final static Log log = LogFactory.getLog(DemoEntryPoint.class);
	private OpenLayersWidget olwidget1;
	private OpenLayersMap map1;
	private OpenLayersWidget olwidget2;
	private OpenLayersMap map2;
	private OpenLayersEventListener listener;
	private View view2;

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
		button.setText("addScaleLineControlToLeftMap");
		button.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				map1.addControl(new ScaleLineControl(null, null,
						ScaleLineControl.Units.metric, null));
				view2.removeEventListener(View.EVENT.center, listener);
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

		// String srs = "EPSG:4326";// Geometries.srs( getCRS() );
		// Projection proj = new Projection(srs);
		// String units = srs.equals("EPSG:4326") ? "degrees" : "m";
		// float maxResolution = srs.equals("EPSG:4326") ? (360 / 256) : 500000;
		// // Bounds maxExtent = new Bounds(12.80, 53.00, 14.30, 54.50);
		// Bounds bounds = new Bounds(11.0, 50.00, 17.30, 64.50);
		// // olwidget1.createMap(proj, proj, units, bounds, maxResolution);
		// // olwidget1.prepare();
		//
		// map1 = new OpenLayersMap(olwidget1, proj, proj, units, bounds,
		// maxResolution);
		// // map.updateSize();
		map1 = new OpenLayersMap(olwidget1, new View());

		// WMSLayer layer = new WMSLayer( "OSM",
		// "http://ows.terrestris.de/osm/service/", "OSM-WMS");
		// layer.setIsBaseLayer(true);
		TileLayer layer = new TileLayer(new MapQuestSource(
				MapQuestSource.Type.hyb));
		map1.addLayer(layer);

		ImageLayer ilayer = new ImageLayer(new ImageWMSSource(
				"http://ows.terrestris.de/osm/service/", "OSM-WMS", null));
		ilayer.setOpacity(10.0);
		map1.addLayer(ilayer);
		// //
		// map1.addControl(new NavigationControl(true));
		// map1.addControl(new PanZoomBarControl( ));
		// map1.addControl(new LayerSwitcherControl( ));
		// map1.addControl(new MousePositionControl());
		// // map1.addControl(new ScaleLineControl());
		// map.addControl(new OverviewMapControl(map, layer));

		// map.addControl( new ScaleControl() );
		// map.addControl( new LoadingPanelControl() );

		// map.setRestrictedExtend( maxExtent );
		// map1.zoomToExtent(bounds, true);
		// map1.zoomTo(2);

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
		// olwidget2.prepare();
		view2 = new View();
		map2 = new OpenLayersMap(olwidget2, view2);
		// map.updateSize();
		map2.addLayer(new TileLayer(new MapQuestSource(MapQuestSource.Type.osm)));
		// map2.addControl(new ScaleLineControl(null, null,
		// ScaleLineControl.Units.degrees, null));
		map2.addControl(new ZoomSliderControl());

		// VectorSource vector = new VectorSource();
		GeoJSONSource source = new GeoJSONSource(
				new GeoJSONSourceOptions()
						.withProjection("EPSG:3857")
						.withUrl(
								"/rwt-resources/demo/polygon-samples.geojson")
						.withAttribution("Steffen Stundzig"));
		map2.addLayer(VectorLayer
				.builder()
				.withSource(source)
				.withStyle(
						Style.builder()
								.withFill(
										FillStyle.builder().withColor(0, 0, 255, 0.1)
												.build())
								.withStroke(
										StrokeStyle.builder().withWidth(1.0)
												.withColor("red").build())
								.build()).build());
		// vector.addEventListener(VectorSource.EVENT.addfeature, event ->
		// System.out.println(event.getProperties()));

		// DrawInteraction di = new DrawInteraction(source,
		// GeometryType.LineString);
		// di.addEventListener(DrawInteraction.EVENT.drawstart, event ->
		// System.out.println(event.getProperties()));
		// di.addEventListener(DrawInteraction.EVENT.drawend, event ->
		// System.out.println(event.getProperties()));
		// map2.addInteraction(di);
		// WMSLayer layer = new WMSLayer("OSM2",
		// "http://ows.terrestris.de/osm/service/", "OSM-WMS");
		// layer.setIsBaseLayer(true);
		// map2.addLayer(layer);
		// //
		// map2.addControl(new NavigationControl(true));
		// map.addControl(new LayerSwitcherControl());
		// map.addControl(new ClickControl());
		// map.addControl(new ButtonControl("foo"));
		// map.addControl(new BoxControl());

		//
		// HashMap<String, String> payload = new HashMap<String, String>();
		// map.events.register( this, OpenLayersMap.EVENT_MOVEEND, payload );
		listener = new OpenLayersEventListener() {

			@Override
			public void handleEvent(OpenLayersEvent event) {
				System.out.println(event.getProperties());
			}
		};
		view2.addEventListener(View.EVENT.center, listener);
		// view2.addEventListener(View.EVENT.resolution, listener);
		// new OpenLayersEventListener() {
		//
		// @Override
		// public void handleEvent(OpenLayersObject src, String name,
		// JsonObject properties) {
		// log.info(this + ": " + name);
		// }
		//
		// }, null);

		// map2.addEventListener(OpenLayersMap.EVENT_MOVEEND,
		// new OpenLayersEventListener() {
		//
		// @Override
		// public void handleEvent(OpenLayersObject src, String name,
		// JsonObject properties) {
		// log.info(this + ": " + name);
		// }
		//
		// }, null);

	}
}
