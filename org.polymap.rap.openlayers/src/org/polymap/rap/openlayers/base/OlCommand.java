/*
 * polymap.org Copyright 2009, Polymap GmbH, and individual contributors as indicated
 * by the @authors tag.
 * 
 * This is free software; you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation; either version 2.1 of the License, or (at your option) any later
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
 * 
 * $Id$
 */

package org.polymap.rap.openlayers.base;

import org.eclipse.rap.json.JsonObject;

/**
 * Command Pattern implementation used for e.g. the remote call.
 * 
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 */
public class OlCommand {

    private final String command;


    public OlCommand( String cmd ) {
        this.command = cmd;
    }


    public String getCommand() {
        return command;
    }


    public JsonObject getJson() {
        return new JsonObject().add( "code", command );
    }


    @Override
    public boolean equals( Object obj ) {
        return command.equals( ((OlCommand)obj).command );
    }
    
    @Override
    public String toString() {
        return command;
    }
}
