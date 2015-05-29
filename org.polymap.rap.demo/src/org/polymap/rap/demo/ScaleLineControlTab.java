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
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.polymap.rap.openlayers.base.OlMap;
import org.polymap.rap.openlayers.control.ScaleLineControl;

public class ScaleLineControlTab
        extends DemoTab {

    private OlMap map;


    public ScaleLineControlTab() {
        super( "ScaleLineControl" );
    }

    private final static Log log = LogFactory.getLog( ScaleLineControlTab.class );
    private ScaleLineControl control;


    @Override
    protected void createDemoControls( Composite parent ) {
        map = defaultMap( parent );
        control = new ScaleLineControl( null, null );
        control.units.set( ScaleLineControl.Units.degrees );
        map.addControl( control );
    }


    @Override
    protected void createStyleControls( Composite parent ) {
        Composite group = new Composite( parent, SWT.NONE );
        group.setLayout( new GridLayout( 2, true ) );
        
        new Label( group, SWT.NONE ).setText( "Units" );
        
        final Combo combo = new Combo( group, SWT.READ_ONLY );
        combo.setItems( Arrays.stream( ScaleLineControl.Units.values() ).map( unit -> unit.toString() ).toArray( String[]::new ));
        combo.select( 0 );
        combo.addSelectionListener( new SelectionAdapter() {
          @Override
          public void widgetSelected( SelectionEvent e ) {
            int index = combo.getSelectionIndex();
            String selection = combo.getItem( index );
            control.units.set( ScaleLineControl.Units.valueOf( selection ) );
          }
        } );
        
    }
}
