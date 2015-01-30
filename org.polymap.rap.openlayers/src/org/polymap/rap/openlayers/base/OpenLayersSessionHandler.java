/*
 * polymap.org
 * Copyright 2009, Polymap GmbH, and individual contributors as indicated
 * by the @authors tag.
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
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *
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
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.SingletonUtil;
import org.eclipse.rap.rwt.client.service.JavaScriptLoader;
import org.eclipse.rap.rwt.remote.AbstractOperationHandler;
import org.eclipse.rap.rwt.remote.Connection;
import org.eclipse.rap.rwt.remote.OperationHandler;
import org.eclipse.rap.rwt.remote.RemoteObject;
import org.polymap.rap.openlayers.OpenLayersWidget;

/**
 * Widget Provider holding a reference to the widget and generate client side
 * object hash / id's
 * 
 * @author Marcus -LiGi- B&uuml;schleb < mail: ligi (at) polymap (dot) de >
 * 
 */

public class OpenLayersSessionHandler {

	private final static Log log = LogFactory.getLog(OpenLayersWidget.class);
	
	/** default external openlayers lib location **/
	public String js_location = "http://openlayers.org/en/v3.1.0/build/ol.js";

	/**
	 * HashMap to hold references to created objects as value with the client
	 * side id as key
	 */
	private Map<String, OpenLayersObject> ref2obj;

	public Vector<OpenLayersCommand> cmdsBeforeRemoteWasPresent;

	private int obj_ref = 0;

	private final RemoteObject remote;

	private OpenLayersSessionHandler() {
		ref2obj = new HashMap<String, OpenLayersObject>();
		cmdsBeforeRemoteWasPresent = new Vector<OpenLayersCommand>();
		
		Connection connection = RWT.getUISession().getConnection();
		remote = connection
				.createRemoteObject("org.polymap.rap.openlayers.OpenLayersWidget");
//		remote.set("parent", WidgetUtil.getId(this));
		register(
				"org/polymap/rap/openlayers/internal/resources/OpenLayersWrapper.js",
				"OpenLayersWrapper.js");
		loadJavaScript();
		// map = new OpenLayersMap(this, remoteObject);

		remote.setHandler(operationHandler);
		remote.set("appearance", "composite");
		remote.set("overflow", "hidden");
	}

	private final OperationHandler operationHandler = new AbstractOperationHandler() {

		private static final long serialVersionUID = 1L;

		@Override
		public void handleCall(String method, JsonObject properties) {
			log.warn(this + ".handleCall " + method + ";"
					+ properties.toString());
			if ("handleOnRender".equals(method)) {
				isRendered = true;
				for (RemoteCall call : calls) {
					callRemote(call.method, call.json);
				}
				calls.clear();
			}
//			if (eventListeners.get(method) != null) {
//				JsonValue objRef = properties.get("event_src_obj");
//				OpenLayersObject obj = null;
//				if (objRef != null) {
//					obj = OpenLayersSessionHandler.getInstance().getObj(
//							objRef.asString());
//				}
//				for (OpenLayersEventListener l : eventListeners.get(method)) {
//					l.handleEvent(obj, method,
//							properties);
//				}
//			}
		}
	};

	private void register(String resourceName, String fileName) {
		ClassLoader classLoader = OpenLayersWidget.class.getClassLoader();
		InputStream inputStream = classLoader.getResourceAsStream(resourceName);
		if (inputStream == null) {
			throw new IllegalStateException(resourceName
					+ " could not be found");
		}
		try {
			RWT.getResourceManager()
					.register("ol_res/" + fileName, inputStream);
		} finally {
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void loadJavaScript() {
		JavaScriptLoader jsLoader = RWT.getClient().getService(
				JavaScriptLoader.class);
		jsLoader.require(js_location);
		jsLoader.require(RWT.getResourceManager().getLocation(
				"ol_res/" + "OpenLayersWrapper.js"));
	}

	// remote call

	public void executeCommand(OpenLayersCommand command) {
		callRemote("eval", command.getJson());
	}

	private class RemoteCall {
		String method;
		JsonObject json;

		public RemoteCall(String method, JsonObject json) {
			this.method = method;
			this.json = json;
		}
	}

	private List<RemoteCall> calls = new ArrayList<RemoteCall>();

	private boolean isRendered = false;

	private void callRemote(String method, JsonObject json) {
		if (isRendered) {
			log.info("callRemote: " + method + " with " + json.toString());
			remote.call(method, json);
		} else {
			calls.add(new RemoteCall(method, json));
		}
	}

	public synchronized static OpenLayersSessionHandler getInstance() {
		return SingletonUtil.getSessionInstance(OpenLayersSessionHandler.class);
	}

	public synchronized void generateReference(OpenLayersObject src) {
		String newRef = "ol" + obj_ref++;
		ref2obj.put(newRef, src);
		src.setObjRef(newRef);
	}

	public OpenLayersObject getObject(String objRef) {
		return ref2obj.get(objRef);
	}

	public void remove(String jsObjRef) {
		ref2obj.remove(jsObjRef);
	}
}
