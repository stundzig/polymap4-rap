/*
 * polymap.org Copyright 2009-2015, Polymap GmbH. All rights reserved.
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
package org.polymap.rap.openlayers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Layout;
import org.polymap.rap.openlayers.base.OlMap;

/**
 * Composite part for the OpenLayers RAP Widget
 * 
 * @author Marcus -LiGi- B&uuml;schleb < mail: ligi (at) polymap (dot) de >
 * @author <a href="http://www.polymap.de">Falko Bräutigam</a>
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 */
public class OlWidget
        extends Composite {

    private static final long serialVersionUID = 1L;

    private final static Log  log              = LogFactory.getLog( OlWidget.class );

//    public boolean            lib_init_done    = false;

    /** reference to the map object - every widget has exactly one Map **/
    private OlMap             map;


    public OlWidget( Composite parent, int style ) {
        super( parent, style );

        // simple layoutmanager to update map js
        super.setLayout( new Layout() {
            
            private static final long serialVersionUID = 1L;


            @Override
            protected void layout( Composite composite, boolean flushCache ) {
                log.info( "Layout.layout" );
                if (map != null) {
                    map.update();
                }
            }
            
            
            @Override
            protected Point computeSize( Composite composite, int wHint, int hHint, boolean flushCache ) {
                return new Point( wHint, hHint );
            }
        } );
    }


    public OlMap getMap() {
        return map;
    }


    public void setMap( OlMap map ) {
        if (this.map != null) {
            throw new IllegalStateException( "only one map per widget is allowed" );
        }
        this.map = map;
    }


    // no external layout
    @Override
    public void setLayout( final Layout layout ) {
        throw new UnsupportedOperationException( "Cannot change internal layout of OlMap" );
    }


    @Override
    public void dispose() {
        // TODO clear the widget and the map
        if (map != null) {
            map.dispose();
        }
        super.dispose();
    }
}
