/*
 * polymap.org Copyright (C) 2009-2013, Polymap GmbH. All rights reserved.
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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.polymap.core.runtime.config.ConfigurationFactory;
import org.polymap.core.runtime.config.Property;

import org.polymap.rap.openlayers.base.OlEventListener.PayLoad;
import org.polymap.rap.openlayers.base.OlPropertyConcern.Unquoted;
import org.polymap.rap.openlayers.util.Stringer;

/**
 * Client Side OpenLayers Object Base Class holding a reference to the widget and
 * keeps track of changes to the object
 * 
 * @author Marcus -LiGi- B&uuml;schleb < mail: ligi (at) polymap (dot) de >
 * @author <a href="http://www.polymap.de">Falko Bräutigam</a>
 * @author <a href="http://mapzone.io">Steffen Stundzig</a>
 */
public abstract class OlObject {

    public static final String UNKNOWN_CLASSNAME = "_unknown_";

    protected String           jsClassname       = UNKNOWN_CLASSNAME;

    protected String           objRef;


    public OlObject( String jsClassname ) {
        this.jsClassname = jsClassname;

        // initialize Property instances
        ConfigurationFactory.inject( this );
    }


    /**
     * Create the JS instance on the client side using the values of all
     * {@link Property} members.
     */
    protected void create() {
        assert jsClassname != null && !jsClassname.equals( UNKNOWN_CLASSNAME );

        String options = OlPropertyConcern.propertiesAsJson( this );
        create( jsClassname, options );
    }


    protected void create( @SuppressWarnings("hiding") String jsClassname, String options ) {
        create( new Stringer( "new ", jsClassname, "(", options, ")" ).toString() );        
    }
    
    
    protected void create( String code ) {
        OlSessionHandler wp = OlSessionHandler.getInstance();
        objRef = wp.generateReference( this );
        wp.executeCommand( new OlCommand( getJSObjRef() + "=" + code + ";" ) );
    }


    protected void lazyCreate() {
        if (objRef == null) {
            create();
        }
    }


    public void execute( String code ) {
        OlSessionHandler.getInstance().executeCommand(
                new OlCommand( new Stringer( "this.obj=", getJSObjRef(), "; ", code ).toString() ) );
    }


    /**
     * Add code that calls the given function with the given arguments.
     * 
     * @param function The name of the function.
     * @param args Can be: {@link String}, kind of {@link Number}, {@link Boolean} or
     *        {@link OlObject}.
     */
    private void prepareExecutionCode( String function, Object... args ) {
        StringBuilder buf = new StringBuilder( 128 ).append( getJSObjRef() ).append( '.' )
                .append( function ).append( '(' );

        for (int i = 0; i < args.length; i++) {
            if (i > 0) {
                buf.append( ',' );
            }
            Object arg = args[i];
            if (arg instanceof OlObject) {
                buf.append( ((OlObject)arg).getJSObjRef() );
            }
            else if (arg instanceof Number) {
                buf.append( arg.toString() );
            }
            else if (arg instanceof Boolean) {
                buf.append( arg.toString() );
            }
            else if (arg instanceof String) {
                buf.append( '\'' ).append( (String)arg ).append( '\'' );
            }
            else {
                throw new IllegalArgumentException( "Unknown arg type: " + arg );
            }
        }
        execute( buf.append( ");" ).toString() );
    }


    public void execute( String function, Object obj ) {
        prepareExecutionCode( function, obj );
    }

//
//    public void execute( String function, OlObject obj, boolean bool ) {
//        prepareExecutionCode( function, obj, bool );
//    }
//
//
//    public void execute( String function, double dbl, boolean bool ) {
//        prepareExecutionCode( function, dbl, bool );
//    }


    public void execute( String function, int val ) {
        prepareExecutionCode( function, val );
    }


    public void execute( String function, boolean val ) {
        prepareExecutionCode( function, val );
    }


    public void execute( String function, double val ) {
        prepareExecutionCode( function, val );
    }


    /**
     * Adds code that sets the value of the given attribute.
     * 
     * @param attr The name of the attribute.
     * @param args Can be: {@link String}, kind of {@link Number}, {@link Boolean} or
     *        {@link OlObject}.
     */
    public void setAttribute( String attr, Object arg ) {
        StringBuilder buf = new StringBuilder( 128 ).append( getJSObjRef() ).append( '.' )
                .append( "set('" ).append( attr ).append( "'," );

        if (arg instanceof OlObject) {
            buf.append( ((OlObject)arg).getJSObjRef() );
        }
        else if (arg instanceof Number) {
            buf.append( arg.toString() );
        }
        else if (arg instanceof Boolean) {
            buf.append( arg.toString() );
        }
        else if (arg instanceof String) {
            buf.append( '\'' ).append( (String)arg ).append( '\'' );
        }
        else if (arg instanceof Enum) {
            buf.append( '\'' ).append( ((Enum)arg).name() ).append( '\'' );
        }
        else if (arg instanceof Unquoted) {
            buf.append( ((Unquoted)arg).toJSONString() );
        }
        else {
            throw new IllegalArgumentException( "Unknown arg type: " + arg );
        }
        execute( buf.append( ");" ).toString() );
    }


    public String getObjRef() {
//        lazyCreate();
        return objRef;
    }


    public String getJSObjRef() {
        lazyCreate();
        return new Stringer().replaceNulls( "null" ).add( "this.objs['", objRef, "']" ).toString();
    }


    public String getJSObj( OlObject object ) {
        if (object == null)
            return "null";
        else
            return object.getJSObjRef();
    }


    public String getJSObj( OlObject[] oa ) {
        if (oa == null)
            return "[null]";
        else {
            String res = "[";
            for (OlObject obj : oa) {
                if (!res.equals( "[" ))
                    res += ",";
                res += getJSObj( obj );
            }
            return res + "]";
        }

    }


    public void dispose() {
        // TODO remove also all controls and other stuff of this map
        OlSessionHandler.getInstance().remove( getObjRef() );
        execute( getJSObjRef() + "=null;" );
    }

    private Map<String,Set<OlEventListener>> eventListeners = new HashMap<String,Set<OlEventListener>>();


    protected void addEventListener( final String event, OlEventListener listener, PayLoad payload ) {
        Set<OlEventListener> listeners = eventListeners.get( event );
        if (listeners == null) {
            listeners = new HashSet<OlEventListener>();
            eventListeners.put( event, listeners );
        }
        listeners.add( listener );

        OlSessionHandler.getInstance().registerEventListener( this, event, listener, payload );
    }


    protected void removeEventListener( final String event, OlEventListener listener ) {
        Set<OlEventListener> listeners = eventListeners.get( event );
        if (listeners != null) {
            listeners.remove( listener );
        }
        OlSessionHandler.getInstance().unregisterEventListener( this, event, listener );
    }


    public Set<OlEventListener> getEventListener( String method ) {
        return eventListeners.get( method );
    }


    public Object getAttribute( String propName ) {
        // TODO Auto-generated method stub
        return null;
    };

}
