package org.polymap.rap.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.polymap.rap.openlayers.OlWidget;
import org.polymap.rap.openlayers.base.OlMap;
import org.polymap.rap.openlayers.layer.ImageLayer;
import org.polymap.rap.openlayers.layer.TileLayer;
import org.polymap.rap.openlayers.source.ImageWMSSource;
import org.polymap.rap.openlayers.source.ImageWMSSource.RequestParams;
import org.polymap.rap.openlayers.source.MapQuestSource;
import org.polymap.rap.openlayers.types.Coordinate;
import org.polymap.rap.openlayers.types.Extent;
import org.polymap.rap.openlayers.types.Projection;
import org.polymap.rap.openlayers.types.Projection.Units;
import org.polymap.rap.openlayers.view.View;

public class DemoEntryPoint2
        extends AbstractEntryPoint {

    private final static Log log = LogFactory.getLog( DemoEntryPoint2.class );

//    private OlMap            map;


    @Override
    protected void createContents( Composite parent ) {
        parent.setLayout( new FillLayout() );
        // Composite mainComposite = new Composite(parent, SWT.NONE);
        // mainComposite.setLayout(new RowLayout());

        Composite left = new Composite( parent, SWT.BORDER );
        Composite right = new Composite( parent, SWT.BORDER );
        Composite buttons = new Composite( parent, SWT.BORDER );
        left.setLayout( new FillLayout() );
        
        SashForm horSashForm = new SashForm( left, SWT.HORIZONTAL );
        
//        Composite left2 = new Composite(left, SWT.BORDER);
//        left.setLayout( new RowLayout( SWT.VERTICAL ) );
        Composite left2l = new Group(horSashForm, SWT.BORDER);
//        left2l.setLayout( new FillLayout() );
        Composite left2r = new Group(horSashForm, SWT.BORDER);
        horSashForm.setWeights( new int[] { 80, 20 } );
        createMap( left2l );
        createMap( left2r );
//        createMap( buttons );
//        left2.layout();
//        createMap2( left2s );
//        createMap2( right );
//        createButtons( buttons );
    }

//
//    private void createButtons( Composite parent ) {
//        parent.setLayout( new FillLayout() );
//        Button button = new Button( parent, SWT.PUSH );
//        button.setText( "addScaleLineControlToLeftMap" );
//        button.addSelectionListener( new SelectionListener() {
//
//            @Override
//            public void widgetSelected( SelectionEvent e ) {
//                map.addControl( new ScaleLineControl( null, null) );
//                // view.removeEventListener( View.EVENT.center, listener );
//            }
//
//
//            @Override
//            public void widgetDefaultSelected( SelectionEvent e ) {
//                // TODO Auto-generated method stub
//
//            }
//        } );
//    }


    private OlWidget createMap( Composite parent ) {
        parent.setLayout( new FillLayout() );
        OlWidget olwidget = new OlWidget( parent, SWT.MULTI | SWT.WRAP | SWT.BORDER );

        // String srs = "EPSG:4326";// Geometries.srs( getCRS() );
        // Projection proj = new Projection(srs);
        // String units = srs.equals("EPSG:4326") ? "degrees" : "m";
        // float maxResolution = srs.equals("EPSG:4326") ? (360 / 256) : 500000;
        // // Bounds maxExtent = new Bounds(12.80, 53.00, 14.30, 54.50);
        // Bounds bounds = new Bounds(11.0, 50.00, 17.30, 64.50);
        // // olwidget1.createMap(proj, proj, units, bounds, maxResolution);
        // // olwidget1.prepare();
        //
        // map1 = new OlMap(olwidget1, proj, proj, units, bounds,
        // maxResolution);
        // // map.updateSize();

        OlMap map = new OlMap( olwidget,
                new View(olwidget).projection.put( new Projection(olwidget, "EPSG:3857", Units.m ) ).extent
                        .put( new Extent( 12.80, 53.00, 14.30, 54.50 ) ).zoom.put( 3 ).center
                        .put( new Coordinate( 0, 0 ) ) );

        // map.addLayer( new TileLayer( newLayer ->
        // newLayer.source.set( new MapQuestSource( MapQuestSource.Type.hyb ) ) ) );

        map.addLayer( new ImageLayer(olwidget).source.put( new ImageWMSSource(olwidget).url
                .put( "http://ows.terrestris.de/osm/service/" ).params
                .put( new RequestParams().layers.put( "OSM-WMS" ) ) ).opacity.put( 0.5f ) );

        // //
        // map1.addControl(new NavigationControl(true));
        // map1.addControl(new PanZoomBarControl( ));
        // map1.addControl(new LayerSwitcherControl( ));
        // map1.addControl(new MousePositionControl());
        // map.addControl(new ScaleLineControl());
        // map.addControl(new OverviewMapControl(map, layer));

//        map.addControl( new ZoomControl() );
        // map.addControl( new ZoomSliderControl() );
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
        // map1.events.register( this, OlMap.EVENT_MOVEEND, payload );
        // map1.events.register( this, OlMap.EVENT_ZOOMEND, payload );
        return olwidget;
    }


    private void createMap2( Composite parent ) {
        parent.setLayout( new FillLayout() );
        OlWidget olwidget = new OlWidget( parent, SWT.MULTI | SWT.WRAP | SWT.BORDER );

        Projection epsg3857 = new Projection(olwidget, "EPSG:3857", Units.m );
        OlMap map = new OlMap( olwidget, new View(olwidget)
        // .projection.put( epsg3857 )
                .center.put( new Coordinate( -8161939, 6095025 ) ).zoom.put( 8 ) );

        // map.addLayer( new ImageLayer().source.put( new ImageWMSSource().url
        // .put( "http://ows.terrestris.de/osm/service/" ).params
        // .put( new RequestParams().layers.put( "OSM-WMS" ) )).opacity.put( 0.5f ));
        map.addLayer( new TileLayer(olwidget).source.put( new MapQuestSource( MapQuestSource.Type.hyb ) ) );
//        //
//        VectorSource source = new VectorSource().format.put( new GeoJSONFormat() ).url.put( RWT
//                .getResourceManager().getLocation( "/polygon-samples.geojson" ) ).attributions
//                .put( Arrays.asList( new Attribution( "Steffen Stundzig" ) ) );
//
//        VectorLayer vector = new VectorLayer().style.put( new Style().fill
//                .put( new FillStyle().color.put( new Color( 0, 0, 255, 0.1f ) ) ).stroke
//                .put( new StrokeStyle().color.put( new Color( "red" ) ).width.put( 1f ) ) ).source
//                .put( source );
//
//        map.addLayer( vector );

        // vector.addEventListener(VectorSource.EVENT.addfeature, event ->
        // System.out.println(event.getProperties()));
//
//        DrawInteraction di = new DrawInteraction( source, GeometryType.LineString );
//        di.addEventListener( DrawInteraction.EVENT.drawstart,
//                event -> System.out.println( event.getProperties() ) );
//        di.addEventListener( DrawInteraction.EVENT.drawend,
//                event -> System.out.println( event.getProperties() ) );
//        map.addInteraction( di );
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
        // map.events.register( this, OlMap.EVENT_MOVEEND, payload );
        // OlEventListener listener = new OlEventListener() {
        //
        // @Override
        // public void handleEvent( OlEvent event ) {
        // System.out.println( event.getProperties() );
        // }
        // };
//        map.view.get().addEventListener( View.EVENT.center, event -> {
//            System.out.println( event.getProperties() );
//        } );
        // view2.addEventListener(View.EVENT.resolution, listener);
        // new OlEventListener() {
        //
        // @Override
        // public void handleEvent(OpenLayersObject src, String name,
        // JsonObject properties) {
        // log.info(this + ": " + name);
        // }
        //
        // }, null);

        // map2.addEventListener(OlMap.EVENT_MOVEEND,
        // new OlEventListener() {
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
