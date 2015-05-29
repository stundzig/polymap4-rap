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

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.polymap.rap.openlayers.base.OlMap;
import org.polymap.rap.openlayers.layer.ImageLayer;
import org.polymap.rap.openlayers.source.ImageWMSSource;
import org.polymap.rap.openlayers.source.ImageWMSSource.RequestParams;
import org.polymap.rap.openlayers.types.Coordinate;
import org.polymap.rap.openlayers.types.Extent;
import org.polymap.rap.openlayers.types.Projection;
import org.polymap.rap.openlayers.types.Projection.Units;
import org.polymap.rap.openlayers.view.View;

public abstract class DemoTab {

    private Composite    demoComposite;

    protected Composite  styleComposite;

    private final String name;

    // private Shell shell;

    private Composite    form;


    // private Composite left;

    public DemoTab( String name ) {
        this.name = name;
        // controls = new ArrayList<Control>();
    }


    public String getName() {
        return name;
    }


    //
    //
    // public String getId() {
    // String id = this.getClass().getSimpleName();
    // if (id.endsWith( "Tab" )) {
    // id = id.substring( 0, id.length() - 3 );
    // }
    // return id;
    // }

    //
    // public Object getData() {
    // return data;
    // }
    //
    //
    // public void setData( Object data ) {
    // this.data = data;
    // }

    public Composite createContents( Composite parent ) {
        // shell = parent.getShell();
        if (form == null) {
            form = createSashForm( parent );
        }
        return form;
    }


    protected abstract void createStyleControls( Composite parent );


    protected abstract void createDemoControls( Composite parent );


    private Composite createSashForm( Composite parent ) {
        SashForm horSashForm = new SashForm( parent, SWT.HORIZONTAL );
        createLeft( horSashForm );
        createRight( horSashForm );
        horSashForm.setWeights( new int[] { 80, 20 } );
        return horSashForm;
    }


    private void createLeft( Composite parent ) {
        Composite composite = new Composite( parent, SWT.NONE );
        composite.setBackground( parent.getDisplay().getSystemColor( SWT.COLOR_WHITE ) );
        composite.setLayout( new FormLayout() );
        Label header = new Label( composite, SWT.CENTER );
        header.setFont( getHeaderFont( parent ) );
        header.setText( getName() );
        header.setLayoutData( createLayoutDataForHeader() );
        demoComposite = new Composite( composite, SWT.NONE );
        demoComposite.setLayoutData( createLayoutDataForExampleArea( header ) );
        demoComposite.setLayout( new FillLayout() );
        createDemoControls( demoComposite );
    }


    private void createRight( Composite parent ) {
        Composite composite = new Composite( parent, SWT.NONE );
        composite.setBackground( parent.getDisplay().getSystemColor( SWT.COLOR_WHITE ) );
        composite.setLayout( new FormLayout() );
        Label header = new Label( composite, SWT.LEFT );
        header.setText( "Styles and Parameters" );
        header.setLayoutData( createLayoutDataForHeader() );
        styleComposite = new Composite( composite, SWT.NONE );
        styleComposite.setLayoutData( createLayoutDataForExampleArea( header ) );
        styleComposite.setLayout( new RowLayout( SWT.VERTICAL ) );
        createStyleControls( styleComposite );
    }


    private static Font getHeaderFont( Composite parent ) {
        String fontName = parent.getFont().getFontData()[0].getName();
        return new Font( parent.getDisplay(), fontName, 18, SWT.BOLD );
    }


    private static Object createLayoutDataForHeader() {
        FormData formData = new FormData();
        formData.top = new FormAttachment( 0, 5 );
        formData.left = new FormAttachment( 0, 10 );
        formData.right = new FormAttachment( 100, -10 );
        return formData;
    }


    private static Object createLayoutDataForExampleArea( Control control ) {
        FormData formData = new FormData();
        formData.top = new FormAttachment( 0, 35 );
        formData.left = new FormAttachment( 0, 5 );
        formData.right = new FormAttachment( 100, -5 );
        formData.bottom = new FormAttachment( 100, -5 );
        return formData;
    }


    protected OlMap defaultMap( Composite parent ) {
        OlMap map = new OlMap( parent, SWT.MULTI | SWT.WRAP | SWT.BORDER,
                new View().projection.put( new Projection( "EPSG:3857", Units.m ) ).extent
                        .put( new Extent( 12.80, 53.00, 14.30, 54.50 ) ).zoom.put( 3 ).center
                        .put( new Coordinate( 0, 0 ) ) );

        // map.addLayer( new TileLayer( newLayer ->
        // newLayer.source.set( new MapQuestSource( MapQuestSource.Type.hyb ) ) ) );

        map.addLayer( new ImageLayer().source.put( new ImageWMSSource().url
                .put( "http://ows.terrestris.de/osm/service/" ).params
                .put( new RequestParams().layers.put( "OSM-WMS" ) ) ).opacity.put( 0.5f ) );
        return map;
    }
}
