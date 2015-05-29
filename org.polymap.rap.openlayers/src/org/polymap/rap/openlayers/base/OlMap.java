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
package org.polymap.rap.openlayers.base;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.rap.rwt.widgets.WidgetUtil;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.polymap.core.runtime.config.Immutable;
import org.polymap.core.runtime.config.Mandatory;
import org.polymap.core.runtime.config.Property2;
import org.polymap.rap.openlayers.base.OlPropertyConcern.Unquoted;
import org.polymap.rap.openlayers.control.Control;
import org.polymap.rap.openlayers.interaction.DrawInteraction;
import org.polymap.rap.openlayers.layer.Layer;
import org.polymap.rap.openlayers.source.Source;
import org.polymap.rap.openlayers.view.View;

/**
 * 
 * @see http://openlayers.org/en/master/apidoc/ol.Map.html
 * 
 * @author <a href="http://www.polymap.de">Falko Bräutigam</a>
 * @author <a href="http://mapzone.io">Steffen Stundzig</a>
 */
public class OlMap
        extends OlObject {

    private final static Log log = LogFactory.getLog( OlMap.class );


    public enum EVENT {
        layerGroup, size, target, view
    }

    @Mandatory
    @Immutable
    public Property2<OlMap,View>      view;

    @Mandatory
    @Immutable
    private Property2<OlMap,Unquoted> target;

    private Composite                 widget;


    public OlMap( Composite parent, int style, View view ) {
        super( "ol.Map" );
        this.view.set( view );

        widget = new Composite( parent, style );
        widget.setLayout( new Layout() {

            private static final long serialVersionUID = 1L;


            @Override
            protected void layout( Composite composite, boolean flushCache ) {
                update();
            }


            @Override
            protected Point computeSize( Composite composite, int wHint, int hHint,
                    boolean flushCache ) {
                return new Point( wHint, hHint );
            }
        } );
        this.target.set( new Unquoted( "this.createDiv('" + WidgetUtil.getId( widget ) + "')" ) );
        // widget.setMap( this );
        //
        // JSONObject options = new JSONObject();
        // options.put( "view", new Unquoted( view.getJSObjRef() ) );
        // options.put( "target",
        // new Unquoted( "this.createDiv('" + WidgetUtil.getId( widget ) + "')" ) );
        // create( new Stringer( "new ", jsClassname, "(", options.toString(), ");"
        // ).toString() );
    }


    public void setLayoutData( Object layoutData ) {
        widget.setLayoutData( layoutData );
    }

    public void addLayer( Layer<? extends Source> layer ) {
        // layer2add.setWidget(widget);
        execute( "addLayer", layer );
    }


    public void removeLayer( Layer<? extends Source> layer ) {
        execute( "removeLayer", layer );
    }


    // /**
    // * Move the given layer to the specified (zero-based) index in the layer
    // * list, changing its z-index in the map display. Use map.getLayerIndex() to
    // * find out the current index of a layer. Note that this cannot (or at least
    // * should not) be effectively used to raise base layers above overlays.
    // *
    // * @param layer
    // * @param index
    // */
    // public void setLayerIndex(Layer layer, int index) {
    // callObjFunction("setLayerIndex", layer, index);
    // }

    public void addControl( Control control ) {
        // control.map.set( this );
        control.setMap( this );
        execute( "addControl", control );
    }


    //
    // public void setProxy(String proxy) {
    // execute("OpenLayers.ProxyHost='" + proxy + "';");
    // }

    public void removeControl( Control control ) {
        // control.map.set( this );
        control.setMap( this );
        execute( "removeControl", control );
    }


    public void addInteraction( DrawInteraction di ) {
        execute( "addInteraction", di );
    }


    public void removeInteraction( DrawInteraction di ) {
        execute( "removeInteraction", di );
    }


    // /**
    // * This property is what allows OpenLayers to know what scale things are
    // * being rendered at, which is important for scale-based methods of zooming
    // * and the Scale display control.
    // *
    // * @param units
    // * The map units. Defaults to "degrees". Possible values are
    // * "degrees" (or "dd"), "m", "ft", "km", "mi", "inches".
    // */
    // public void setUnits(String units) {
    // this.units = units;
    // setAttribute("units", units);
    // }
    //
    // public void updateSize() {
    // execute(new Stringer("setTimeout( function() {", getJSObjRef(),
    // ".updateSize();", "}, 500 );").toString());
    // }

    /**
     * The event contains the new center, resolution and rotation
     * 
     * @param event
     * @param listener
     */
    public void addEventListener( EVENT event, OlEventListener listener ) {
        addEventListener( "change:" + event.name(), listener, null );
    }


    public void removeEventListener( EVENT event, OlEventListener listener ) {
        removeEventListener( "change:" + event.name(), listener );
    }


    private void update() {
        execute( "this.obj.updateSize();" );
    }


    @Override
    public void dispose() {
        // TODO clear the widget and the map

        super.dispose();
    }
}
