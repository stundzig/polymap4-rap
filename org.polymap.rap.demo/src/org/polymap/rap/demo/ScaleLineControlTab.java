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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.widgets.Composite;
import org.polymap.rap.openlayers.base.OlMap;
import org.polymap.rap.openlayers.control.ScaleLineControl;

public class ScaleLineControlTab
        extends DemoTab {

    private OlMap map;


    public ScaleLineControlTab() {
        super( "ScaleLineControl" );
    }

    private final static Log log = LogFactory.getLog( ScaleLineControlTab.class );


    @Override
    protected void createDemoControls( Composite parent ) {
        map = defaultMap(parent);
        map.addControl( new ScaleLineControl(null, null) );
    }






    @Override
    protected void createStyleControls( Composite parent ) {
        // TODO Auto-generated method stub
        
    }

//
//    @Override
//    public void redraw() {
//        olwidget.getMap().render();
//    }
}
