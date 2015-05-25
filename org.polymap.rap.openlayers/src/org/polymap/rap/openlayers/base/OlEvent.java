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

import org.eclipse.rap.json.JsonObject;

public class OlEvent {

    private OlObject   src;

    private String     name;

    private JsonObject properties;


    public OlEvent( OlObject src, String name, JsonObject properties ) {
        this.src = src;
        this.name = name;
        this.properties = properties;

    }


    public OlObject getSrc() {
        return src;
    }


    public void setSrc( OlObject src ) {
        this.src = src;
    }


    public String getName() {
        return name;
    }


    public void setName( String name ) {
        this.name = name;
    }


    public JsonObject getProperties() {
        return properties;
    }


    public void setProperties( JsonObject properties ) {
        this.properties = properties;
    }
}
