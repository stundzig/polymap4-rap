/*
 * polymap.org Copyright 2009, Polymap GmbH, and individual contributors as indicated
 * by the @authors tag.
 * 
 * This is free software; you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License along
 * with this software; if not, write to the Free Software Foundation, Inc., 51
 * Franklin St, Fifth Floor, Boston, MA 02110-1301 USA, or see the FSF site:
 * http://www.fsf.org.
 */
package org.polymap.rap.openlayers.base;

import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.eclipse.rap.json.JsonObject;
import org.eclipse.rap.json.JsonValue;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.SingletonUtil;
import org.eclipse.rap.rwt.client.service.JavaScriptLoader;
import org.eclipse.rap.rwt.remote.AbstractOperationHandler;
import org.eclipse.rap.rwt.remote.Connection;
import org.eclipse.rap.rwt.remote.RemoteObject;

import org.polymap.rap.openlayers.OlWidget;
import org.polymap.rap.openlayers.base.OlEventListener.PayLoad;
import org.polymap.rap.openlayers.util.Stringer;

/**
 * Widget Provider holding a reference to the widget and generate client side object
 * hash / id's
 * 
 * @author Marcus -LiGi- B&uuml;schleb < mail: ligi (at) polymap (dot) de >
 */
public class OlSessionHandler {

    private final static Log     log   = LogFactory.getLog( OlSessionHandler.class );

    /**
     * HashMap to hold references to created objects as value with the client side id
     * as key
     */
    private Map<String,OlObject> ref2obj;

    public Vector<OlCommand>     cmdsBeforeRemoteWasPresent;

    private int                  referenceCounter;

    private final RemoteObject   remote;

    private List<RemoteCall>     calls = new ArrayList<RemoteCall>();

    private boolean              isRendered;


    private OlSessionHandler() {
        ref2obj = new HashMap<String,OlObject>();
        cmdsBeforeRemoteWasPresent = new Vector<OlCommand>();

        Connection connection = RWT.getUISession().getConnection();
        remote = connection.createRemoteObject( "org.polymap.rap.openlayers.OlWidget" );
        loadJavaScript();

        remote.setHandler( operationHandler );
        remote.set( "appearance", "composite" );
        remote.set( "overflow", "hidden" );
    }

    private final AbstractOperationHandler operationHandler = new AbstractOperationHandler() {

                                                                private static final long serialVersionUID = 1L;


                                                                @Override
                                                                public void handleCall(
                                                                        String method,
                                                                        JsonObject properties ) {
                                                                    // log.warn(this
                                                                    // +
                                                                    // ".handleCall "
                                                                    // + method + ";"
                                                                    // +
                                                                    // properties.toString());
                                                                    if ("handleOnRender"
                                                                            .equals( method )) {
                                                                        isRendered = true;
                                                                        for (RemoteCall call : calls) {
                                                                            callRemote(
                                                                                    call.method,
                                                                                    call.json );
                                                                        }
                                                                        calls.clear();
                                                                    }
                                                                    else {
                                                                        JsonValue objRefJS = properties
                                                                                .get( "event_src_obj" );
                                                                        if (objRefJS != null) {
                                                                            String objRef = objRefJS
                                                                                    .asString();
                                                                            properties
                                                                                    .remove( "event_src_obj" );
                                                                            OlObject obj = getObject( objRef );
                                                                            if (obj != null) {
                                                                                OlEvent event = new OlEvent(
                                                                                        obj,
                                                                                        method,
                                                                                        properties );
                                                                                for (OlEventListener l : obj
                                                                                        .getEventListener( method )) {
                                                                                    l.handleEvent( event );
                                                                                }
                                                                            }
                                                                        }
                                                                    }
                                                                }
                                                            };


    private void loadJavaScript() {
        JavaScriptLoader jsLoader = RWT.getClient().getService( JavaScriptLoader.class );
        jsLoader.require( "/ol_js/ol-3.5.0.js" );

        register( "org/polymap/rap/openlayers/internal/resources/OlWrapper.js", "OlWrapper.js" );
        jsLoader.require( RWT.getResourceManager().getLocation( "ol_res/" + "OlWrapper.js" ) );
    }


    private void register( String resourceName, String fileName ) {
        ClassLoader classLoader = OlWidget.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream( resourceName );
        if (inputStream == null) {
            throw new IllegalStateException( resourceName + " could not be found" );
        }
        try {
            RWT.getResourceManager().register( "ol_res/" + fileName, inputStream );
        }
        finally {
            try {
                inputStream.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // remote call

    public void executeCommand( OlCommand command ) {
        callRemote( "execute", command.getJson() );
    }


    private class RemoteCall {

        String     method;

        JsonObject json;


        public RemoteCall( String method, JsonObject json ) {
            this.method = method;
            this.json = json;
        }
    }


    private void callRemote( String method, JsonObject json ) {
        if (isRendered) {
            log.info( "callRemote: " + method + " with "
                    + json.toString().replaceAll( "\\\\\"", "'" ) );
            remote.call( method, json );
        }
        else {
            calls.add( new RemoteCall( method, json ) );
        }
    }


    public synchronized static OlSessionHandler getInstance() {
        return SingletonUtil.getSessionInstance( OlSessionHandler.class );
    }


    public synchronized String generateReference( OlObject src ) {
        String newRef = "ol" + referenceCounter++;
        if (ref2obj.put( newRef, src ) != null) {
            throw new IllegalStateException( "objRef already added: " + newRef );
        }
        return newRef;
    }


    public OlObject getObject( String objRef ) {
        return ref2obj.get( objRef );
    }


    public void remove( String objRef ) {
        ref2obj.remove( objRef );
    }


    public void registerEventListener( OlObject src, String event, OlEventListener listener,
            PayLoad payload ) {
        Stringer payloadStringer = new Stringer();
        if (payload != null) {
            payload.values().forEach(
                    value -> payloadStringer.add( "result.", value.key, " = ", value.value, ";" ) );
        }
        if (src.getObjRef() == null) {
            // the source was not created before, but here we need the id
            src.create();
        }
        String command = new Stringer(
                // "console.log('", event, "');",
                "var result = that.objs['", src.getObjRef(), "'].getProperties();",
                "result['event_src_obj'] = '" + src.getObjRef() + "';", payloadStringer,
                "rap.getRemoteObject(that).call( '", event, "', result);" ).toString();
        callRemote(
                "addListener",
                new JsonObject().add( "src", src.getObjRef() ).add( "code", command )
                        .add( "event", event ).add( "hashCode", event + "_" + listener.hashCode() ) );
    }


    public void unregisterEventListener( OlObject src, String event, OlEventListener listener ) {
        callRemote(
                "removeListener",
                new JsonObject().add( "src", src.getObjRef() ).add( "hashCode",
                        event + "_" + listener.hashCode() ) );
    }
}
