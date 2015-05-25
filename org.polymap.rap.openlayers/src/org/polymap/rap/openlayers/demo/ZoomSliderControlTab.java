package org.polymap.rap.openlayers.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.polymap.rap.openlayers.OlWidget;
import org.polymap.rap.openlayers.base.OlMap;
import org.polymap.rap.openlayers.control.ZoomSliderControl;
import org.polymap.rap.openlayers.layer.ImageLayer;
import org.polymap.rap.openlayers.source.ImageWMSSource;
import org.polymap.rap.openlayers.source.ImageWMSSource.RequestParams;
import org.polymap.rap.openlayers.types.Coordinate;
import org.polymap.rap.openlayers.types.Extent;
import org.polymap.rap.openlayers.types.Projection;
import org.polymap.rap.openlayers.types.Projection.Units;
import org.polymap.rap.openlayers.view.View;

public class ZoomSliderControlTab
        extends DemoTab {

    private OlWidget olwidget;


    public ZoomSliderControlTab() {
        super( "ZoomSliderControl" );
    }

    private final static Log log = LogFactory.getLog( ZoomSliderControlTab.class );


    @Override
    protected void createControls( Composite parent ) {
        parent.setLayout( new GridLayout() );
        olwidget = new OlWidget( parent, SWT.MULTI | SWT.WRAP | SWT.BORDER );
        olwidget.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true, 1, 1 ) );

        OlMap map = new OlMap( olwidget,
                new View().projection.put( new Projection( "EPSG:3857", Units.m ) ).extent
                        .put( new Extent( 12.80, 53.00, 14.30, 54.50 ) ).zoom.put( 3 ).center
                        .put( new Coordinate( 0, 0 ) ) );

        // map.addLayer( new TileLayer( newLayer ->
        // newLayer.source.set( new MapQuestSource( MapQuestSource.Type.hyb ) ) ) );

        map.addLayer( new ImageLayer().source.put( new ImageWMSSource().url
                .put( "http://ows.terrestris.de/osm/service/" ).params
                .put( new RequestParams().layers.put( "OSM-WMS" ) ) ).opacity.put( 0.5f ) );

        map.addControl( new ZoomSliderControl() );
    }


    @Override
    protected void disposeControls() {
        if (olwidget != null) {
            olwidget.dispose();
        }

    }
}
