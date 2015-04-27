/*
 * polymap.org
 * Copyright 2009-2015, Polymap GmbH. All rights reserved.
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
package org.polymap.rap.openlayers;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;
import org.polymap.rap.openlayers.base.OlMap;

/**
 * Composite part for the OpenLayers RAP Widget
 * 
 * @author Marcus -LiGi- B&uuml;schleb < mail: ligi (at) polymap (dot) de >
 * @author <a href="http://www.polymap.de">Falko Bräutigam</a>
 */
public class OlWidget extends Composite {

	private static final long serialVersionUID = 1L;

	private final static Log log = LogFactory.getLog(OlWidget.class);

	public boolean lib_init_done = false;

	/** reference to the map object - every widget has exactly one Map **/
	private OlMap map;
//
//	/** default external openlayers lib location **/
//	public String js_location = "http://www.openlayers.org/api/OpenLayers.js";


	public OlWidget(Composite parent, int style) {
		super(parent, style);
//		Connection connection = RWT.getUISession().getConnection();
//		remote = connection
//				.createRemoteObject("org.polymap.rap.openlayers.OlWidget");
//		remote.set("parent", WidgetUtil.getId(this));
//		register(
//				"org/polymap/rap/openlayers/internal/resources/OpenLayersWrapper.js",
//				"OpenLayersWrapper.js");
//		loadJavaScript();
//		// map = new OlMap(this, remoteObject);
//
//		remote.setHandler(operationHandler);
//		remote.set("appearance", "composite");
//		remote.set("overflow", "hidden");
		
//		remote.

//		OlSessionHandler.getInstance().setWidget(this);

		// hookContextMenu();
	}
//
//	private final OperationHandler operationHandler = new AbstractOperationHandler() {
//
//		private static final long serialVersionUID = 1L;
//
//		@Override
//		public void handleCall(String method, JsonObject properties) {
//			log.warn(this + ".handleCall " + method + ";"
//					+ properties.toString());
//			if ("handleOnRender".equals(method)) {
//				isRendered = true;
//				for (RemoteCall call : calls) {
//					callRemote(call.method, call.json);
//				}
//				calls.clear();
//			}
//			if (eventListeners.get(method) != null) {
//				JsonValue objRef = properties.get("event_src_obj");
//				OpenLayersObject obj = null;
//				if (objRef != null) {
//					obj = OlSessionHandler.getInstance().getObj(
//							objRef.asString());
//				}
//				for (OlEventListener l : eventListeners.get(method)) {
//					l.handleEvent(obj, method,
//							properties);
//				}
//			}
//		}
//	};
//
//	private void register(String resourceName, String fileName) {
//		ClassLoader classLoader = OlWidget.class.getClassLoader();
//		InputStream inputStream = classLoader.getResourceAsStream(resourceName);
//		if (inputStream == null) {
//			throw new IllegalStateException(resourceName
//					+ " could not be found");
//		}
//		try {
//			RWT.getResourceManager()
//					.register("ol_res/" + fileName, inputStream);
//		} finally {
//			try {
//				inputStream.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	private void loadJavaScript() {
//		JavaScriptLoader jsLoader = RWT.getClient().getService(
//				JavaScriptLoader.class);
//		jsLoader.require(js_location);
//		jsLoader.require(RWT.getResourceManager().getLocation(
//				"ol_res/" + "OpenLayersWrapper.js"));
//	}
//
//	//
//	// public OlWidget(Composite parent, int style, String lib_location)
//	// {
//	// this(parent, style);
//	// js_location = lib_location;
//	// }
//
//	// protected void hookContextMenu() {
//	// final MenuManager contextMenu = new MenuManager();
//	// contextMenu.setRemoveAllWhenShown( true );
//	// contextMenu.addMenuListener( new IMenuListener() {
//	// public void menuAboutToShow( IMenuManager manager ) {
//	// contextMenu.add( new Action( "Text" ) {
//	// public void run() {
//	// }
//	// });
//	// }
//	// } );
//	// Menu menu = contextMenu.createContextMenu( this );
//	// setMenu( menu );
//	// }

	public OlMap getMap() {
		return map;
	}
	
	public void setMap(OlMap map) {
		if (this.map != null) {
			throw new IllegalStateException("only one map per widget is allowed");
		}
		this.map = map;
	}

//	/**
//	 * create the Map with non default Projection createMap must be called
//	 * before getMap()
//	 * 
//	 **/
//	public void createMap(Projection projection, Projection display_projection,
//			String units, Bounds maxExtent, float maxResolution) {
//		map = new OlMap(this, projection, display_projection, units,
//				maxExtent, maxResolution);
//	}
//
//	public void prepare() {
//		// Connection connection = RWT.getUISession().getConnection();
//		// remoteObject = connection
//		// .createRemoteObject("de.itemis.eclipse.rap.map.MapWidget");
//		// remoteObject.set("parent", WidgetUtil.getId(this));
//		// map = new Map(remoteObject, String.valueOf(System
//		// .identityHashCode(this)));
//		//
//		if (map == null) {
//			map = new OlMap(this);
//		}
//	}
//
//	//
//	// public String getJSLocation() {
//	// return js_location;
//	// }
//
//	// remote call
//
//	public void executeCommand(OlCommand command) {
//		// log.info("eval: " + command.getJson().toString());
//		callRemote("eval", command.getJson());
//	}
//
//	private class RemoteCall {
//		String method;
//		JsonObject json;
//
//		public RemoteCall(String method, JsonObject json) {
//			this.method = method;
//			this.json = json;
//		}
//	}
//
//	private List<RemoteCall> calls = new ArrayList<RemoteCall>();
//
//	private boolean isRendered = false;
//
//	private void callRemote(String method, JsonObject json) {
//		if (isRendered) {
//			log.info("callRemote: " + method + " with " + json.toString());
//			remote.call(method, json);
//		} else {
//			calls.add(new RemoteCall(method, json));
//		}
//	}

	// no layout
	@Override
	public void setLayout(final Layout layout) {
		throw new UnsupportedOperationException(
				"Cannot change internal layout of OlMap");
	}
//
//	@Override
//	public void dispose() {
//		if (remote != null) {
//			remote.destroy();
//		}
//		super.dispose();
//	}


}
