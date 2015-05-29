/*
 * polymap.org Copyright 2009-2013, Polymap GmbH. All rights reserved.
 * 
 * This is free software; you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 */
package org.polymap.rap.demo;

import java.util.Arrays;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.swt.widgets.Composite;
import org.polymap.rap.openlayers.base.OlMap;
import org.polymap.rap.openlayers.control.ZoomControl;
import org.polymap.rap.openlayers.format.GeoJSONFormat;
import org.polymap.rap.openlayers.geom.GeometryType;
import org.polymap.rap.openlayers.interaction.DrawInteraction;
import org.polymap.rap.openlayers.layer.VectorLayer;
import org.polymap.rap.openlayers.source.VectorSource;
import org.polymap.rap.openlayers.style.FillStyle;
import org.polymap.rap.openlayers.style.StrokeStyle;
import org.polymap.rap.openlayers.style.Style;
import org.polymap.rap.openlayers.types.Attribution;
import org.polymap.rap.openlayers.types.Color;

public class DrawInteractionTab
        extends DemoTab {

    public DrawInteractionTab() {
        super( "DrawInteraction" );
    }

    private final static Log log = LogFactory.getLog( DrawInteractionTab.class );


    @Override
    protected void createDemoControls( Composite parent ) {
        OlMap map = defaultMap( parent );
        
        VectorSource source = new VectorSource().format.put( new GeoJSONFormat() ).attributions
                .put( Arrays.asList( new Attribution( "Steffen Stundzig" ) ) );
        
        VectorLayer vector = new VectorLayer().style.put( new Style().fill
                .put( new FillStyle().color.put( new Color( 0, 0, 255, 0.1f ) ) ).stroke
                .put( new StrokeStyle().color.put( new Color( "red" ) ).width.put( 1f ) ) ).source
                .put( source );

        map.addLayer( vector );

        // vector.addEventListener(VectorSource.EVENT.addfeature, event ->
        // System.out.println(event.getProperties()));

        DrawInteraction di = new DrawInteraction( source, GeometryType.LineString );
        di.addEventListener( DrawInteraction.EVENT.drawstart,
                event -> System.out.println( event.getProperties() ) );
        di.addEventListener( DrawInteraction.EVENT.drawend,
                event -> System.out.println( event.getProperties() ) );
        map.addInteraction( di );
        
    }


    @Override
    protected void createStyleControls( Composite parent ) {
        // TODO Auto-generated method stub

    }
}
