/*
 * polymap.org
 * Copyright 2009-2012, Polymap GmbH. All rights reserved.
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

/**
 * JavaScript Part for the OpenLayers RAP Widget
 * 
 * @author Marcus -LiGi- Bueschleb mail to ligi (at) polymap (dot) de
 */
/*
 * function loadScript(url, callback, context) { var script =
 * document.createElement("script");
 * 
 * script.type = "text/javascript";
 * 
 * if (script.readyState) { // IE script.onreadystatechange = function() { if
 * (script.readyState == "loaded" || script.readyState == "complete") {
 * script.onreadystatechange = null; callback(context); } }; } else { // Others
 * script.onload = function() { callback(context); }; }
 * 
 * script.src = url;
 * document.getElementsByTagName("head")[0].appendChild(script); }
 */
(function() {
	'use strict';

	rap.registerTypeHandler("org.polymap.rap.openlayers.OlWidget", {

		factory : function(properties) {
			console.log("widget.factory()");
			return new map.OlWidget(properties);
		},

		destructor : "destroy",

		properties : [],

		methods : [ "execute", "addListener", "removeListener" ]

	});

	if (!window.map) {
		// console.log("window.map created()");
		window.map = {};
	}

	map.OlWidget = function(properties) {
		console.log("map.OlWidget created()");
		console.log("window.map ", window.map);
		this.objs = {};
		this.listenerKeys = {};
		this.events = {};
		bindAll(this, [ "layout", "onRender", "onReady", "createDiv" ]);
		this.parent = rap.getObject(properties.parent);
		console.log("parent ", parent);
		this.element = document.createElement("div");
		this.element.setAttribute("id", properties.parent + properties.parent);
		this.parent.append(this.element);

		this.parent.addListener("Resize", this.layout);

		console.log('element id is ' + this.element.getAttribute("id"));

		rap.on("render", this.onRender);
		//this.remoteObject;
	};

	map.OlWidget.prototype = {
		ready : false,
		repaint : false,

		createDiv : function(id) {
			console.log('create map in ' + id);
			// var parent = rap.getObject(id);
			// console.log('parent is ' + parent);
			// var element = document.createElement('div');
			// element.setAttribute("id", id + id);
			// parent.append(element);
			//
			console.log('element id is ' + this.element.getAttribute("id"));
			// this.remoteObject = rap.getRemoteObject(this);
			return this.element;
		},

		onReady : function() {
			console.log("onRender");
			// TODO [tb] : on IE 7/8 the iframe and body has to be made
			// transparent explicitly
			this.ready = true;
		},

		onRender : function() {

			console.log("onRender");
			// this.remoteObject = rap.getRemoteObject(this);
			// rwt.ui.core.Widget.flushGlobalQueues();
			if (this.element.parentNode) {
				rap.off("render", this.onRender);
				// if (this._map == null) {
				console.log("onRender creating map");
				this.ready = true;
				// this.layout();
				// // OpenLayers.ImgPath = "/rwt-resources/map/img/";
				// this.objs=[];
				// this.objs['map1'] = new OpenLayers.Map(this.element, {
				// numZoomLevels : 20
				// });
				// //this._map.events.register('moveend', this, this._moveEnd);
				// //this._map.events.register('zoomend', this, this._zoomEnd);
				// // this._map.events.register('changelayer', this,
				// // this._changeLayer);
				// OpenLayers.Control.Click = OpenLayers
				// .Class(
				// OpenLayers.Control,
				// {
				// defaultHandlerOptions : {
				// 'single' : true,
				// 'double' : false,
				// 'pixelTolerance' : 0,
				// 'stopSingle' : false,
				// 'stopDouble' : false
				// },
				//
				// initialize : function(options) {
				// this.handlerOptions = OpenLayers.Util
				// .extend(
				// {},
				// this.defaultHandlerOptions);
				// OpenLayers.Control.prototype.initialize
				// .apply(this, arguments);
				// // this.handler = new OpenLayers.Handler.Click(
				// // this, {
				// // 'click' : this.trigger
				// // }, this.handlerOptions);
				// },
				//
				// // trigger : function(e) {
				// // this.widget._click(e.xy);
				// // },
				//
				// setWidget : function(w) {
				// this.widget = w;
				// }
				//
				// });
				// // var click = new OpenLayers.Control.Click();
				// // // pass the map and widget to control
				// // click.setMap(this._map);
				// // click.setWidget(this);
				// // // add and activate click control
				// // this._map.addControl(click);
				// // click.activate();
				rap.getRemoteObject(this).call("handleOnRender", {});
				// }
				// this._map.updateSize();

				// this._synchronizeResizeWithServer();
			}
		},

		/**
		 * Load addins after OpenLayer.js is loaded.
		 * 
		 * FIXME: does not work correctly yet. Can be called *after*
		 * Openlayer.js is loaded.
		 */
		/*
		 * load_addin : function(lib_url) { loadScript(lib_url,
		 * function(context) { // alert( 'Addin loaded: ' + lib_url ); }, this); },
		 */
		layout : function() {
			console.log("layout " + this.ready);
//			if (this.ready) {
//				var area = this.parent.getClientArea();
//				this.element.style.left = area[0] + "px";
//				this.element.style.top = area[1] + "px";
//				this.element.style.width = area[2] + "px";
//				this.element.style.height = area[3] + "px";
//				// this.editor.resize( area[ 2 ], area[ 3 ] );
//			}
		},
		destroy : function() {
			this.element.parentNode.removeChild(this.element);
		},

		execute : function(properties) {
			console.log('eval', properties.code);
			eval(properties.code);
		},

		addListener : function(properties) {
			console.log('addListener:  ', properties);
			console.log('obj', this.objs[properties.src]);
			var key = this.objs[properties.src].on(properties.event, function(
					theEvent) {
				console.log(this.objs[properties.src].getProperties());
				console.log(theEvent);
				var oldTimeOut = this.events[theEvent];
				if (oldTimeOut) {
					window.clearTimeout(oldTimeOut);
				}
				var that = this;
				// wait 100ms for sending the event, newer events will remove
				// this call
				var newTimeOut = window.setTimeout(function() {
					eval(properties.code)
				}, 100);
				this.events[theEvent] = newTimeOut;
			}, this);
			console.log('key:  ', key);
			// listeners stored by key, do later remove them from the global
			// goog context
			this.listenerKeys[properties.hashCode] = key;
		},

		removeListener : function(properties) {
			console.log('remove:  ', properties);
			var key = this.listenerKeys[properties.hashCode];
			console.log('remove:  ', key);
			this.objs[properties.src].unByKey(key);
		}
	};
	var bind = function(context, method) {
		return function() {
			return method.apply(context, arguments);
		};
	};

	var bindAll = function(context, methodNames) {
		for (var i = 0; i < methodNames.length; i++) {
			var method = context[methodNames[i]];
			context[methodNames[i]] = bind(context, method);
		}
	};
}());
