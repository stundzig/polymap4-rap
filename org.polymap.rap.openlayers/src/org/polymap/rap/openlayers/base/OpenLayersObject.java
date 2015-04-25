/*
 * polymap.org
 * Copyright (C) 2009-2013, Polymap GmbH. All rights reserved.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */
package org.polymap.rap.openlayers.base;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.polymap.core.runtime.config.ConfigurationFactory;
import org.polymap.core.runtime.config.Property;

import org.polymap.rap.openlayers.base.OpenLayersEventListener.PayLoad;
import org.polymap.rap.openlayers.util.Stringer;

/**
 * Client Side OpenLayers Object Base Class holding a reference to the widget
 * and keeps track of changes to the object
 * 
 * @author Marcus -LiGi- B&uuml;schleb < mail: ligi (at) polymap (dot) de >
 * @author <a href="http://www.polymap.de">Falko Bräutigam</a>
 * @author <a href="http://mapzone.io">Steffen Stundzig</a>
 */
public abstract class OpenLayersObject {

    public static final String  UNKNOWN_CLASSNAME = "_unknown_";
    
    protected String            jsClassname = UNKNOWN_CLASSNAME;
    
    protected String            objRef;


    public OpenLayersObject( String jsClassname ) {
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

        String options = OpenLayersPropertyConcern.propertiesAsJson( this );
        create( new Stringer( "new ", jsClassname, "(", options, ");" ).toString() );
    }


    protected void create( String code ) {
        OpenLayersSessionHandler wp = OpenLayersSessionHandler.getInstance();
        objRef = wp.generateReference( this );
        wp.executeCommand( new OpenLayersCommand( getJSObjRef() + "=" + code + ";" ) );
    }

    
    protected void lazyCreate() {
        if (objRef == null) {
            create();
        }
    }

    
    public void execute( String code ) {
        OpenLayersSessionHandler.getInstance().executeCommand(
                new OpenLayersCommand( new Stringer( "this.obj=", getJSObjRef(), "; ", code ).toString() ) );
    }


	/**
	 * Add code that calls the given function with the given arguments.
	 * 
	 * @param function
	 *            The name of the function.
	 * @param args
	 *            Can be: {@link String}, kind of {@link Number},
	 *            {@link Boolean} or {@link OpenLayersObject}.
	 */
	private void prepareExecutionCode(String function, Object... args) {
		StringBuilder buf = new StringBuilder(128).append(getJSObjRef())
				.append('.').append(function).append('(');

		for (int i = 0; i < args.length; i++) {
			if (i > 0) {
				buf.append(',');
			}
			Object arg = args[i];
			if (arg instanceof OpenLayersObject) {
				buf.append(((OpenLayersObject) arg).getJSObjRef());
			} else if (arg instanceof Number) {
				buf.append(arg.toString());
			} else if (arg instanceof Boolean) {
				buf.append(arg.toString());
			} else if (arg instanceof String) {
				buf.append('\'').append((String) arg).append('\'');
			} else {
				throw new IllegalArgumentException("Unknown arg type: " + arg);
			}
		}
		execute(buf.append(");").toString());
	}

	public void execute(String function, OpenLayersObject obj) {
		prepareExecutionCode(function, obj);
	}

	public void execute(String function, OpenLayersObject obj, boolean bool) {
		prepareExecutionCode(function, obj, bool);
	}

	public void execute(String function, double dbl, boolean bool) {
		prepareExecutionCode(function, dbl, bool);
	}

	public void execute(String function, int val) {
		prepareExecutionCode(function, val);
	}

	public void execute(String function, boolean val) {
		prepareExecutionCode(function, val);
	}

	public void execute(String function, double val) {
		prepareExecutionCode(function, val);
	}

	/**
	 * Adds code that sets the value of the given attribute.
	 * 
	 * @param attr
	 *            The name of the attribute.
	 * @param args
	 *            Can be: {@link String}, kind of {@link Number},
	 *            {@link Boolean} or {@link OpenLayersObject}.
	 */
	public void setAttribute(String attr, Object arg) {
        StringBuilder buf = new StringBuilder( 128 )
                .append( getJSObjRef() ).append( '.' ).append( attr ).append( '=' );

        if (arg instanceof OpenLayersObject) {
            buf.append( ((OpenLayersObject)arg).getJSObjRef() );
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
        else {
            throw new IllegalArgumentException( "Unknown arg type: " + arg );
        }
        execute( buf.append( ';' ).toString() );
    }


	public String getObjRef() {
		return objRef;
	}

	public String getJSObjRef() {
	    lazyCreate();
		return new Stringer().replaceNulls( "null" ).add( "this.objs['", objRef, "']" ).toString();
	}

	public String getJSObj(OpenLayersObject object) {
		if (object == null)
			return "null";
		else
			return object.getJSObjRef();
	}

	public String getJSObj(OpenLayersObject[] oa) {
		if (oa == null)
			return "[null]";
		else {
			String res = "[";
			for (OpenLayersObject obj : oa) {
				if (!res.equals("["))
					res += ",";
				res += getJSObj(obj);
			}
			return res + "]";
		}

	}

	public void dispose() {
		OpenLayersSessionHandler.getInstance().remove(getObjRef());
		execute(getJSObjRef() + "=null;");
	}

	private Map<String, Set<OpenLayersEventListener>> eventListeners = new HashMap<String, Set<OpenLayersEventListener>>();


    protected void addEventListener( final String event, OpenLayersEventListener listener, PayLoad payload ) {
        Set<OpenLayersEventListener> listeners = eventListeners.get( event );
        if (listeners == null) {
            listeners = new HashSet<OpenLayersEventListener>();
            eventListeners.put( event, listeners );
        }
        listeners.add( listener );

        OpenLayersSessionHandler.getInstance().registerEventListener( this, event, listener, payload );
    }


    protected void removeEventListener( final String event, OpenLayersEventListener listener ) {
        Set<OpenLayersEventListener> listeners = eventListeners.get( event );
        if (listeners != null) {
            listeners.remove( listener );
        }
        OpenLayersSessionHandler.getInstance().unregisterEventListener( this, event, listener );
    }


	public Set<OpenLayersEventListener> getEventListener(String method) {
		return eventListeners.get(method);
	};

}
