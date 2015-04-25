/*
 * polymap.org
 * Copyright 2015, Polymap GmbH. All rights reserved.
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
package org.polymap.rap.openlayers.types;

import org.json.JSONArray;
import org.polymap.rap.openlayers.base.Jsonable;

/**
 * 
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 */
public class Coordinate
        implements Jsonable {

	private int x;

	private int y;

	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

    @Override
    public Object toJson() {
        return new JSONArray().put( x ).put( y );
    }
	
}
