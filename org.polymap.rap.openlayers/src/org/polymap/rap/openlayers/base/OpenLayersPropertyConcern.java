/* 
 * polymap.org
 * Copyright (C) 2015, Falko Bräutigam. All rights reserved.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3.0 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */
package org.polymap.rap.openlayers.base;

import java.lang.reflect.Field;

import org.json.JSONObject;
import org.json.JSONString;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.polymap.core.runtime.config.ConfigurationException;
import org.polymap.core.runtime.config.DefaultPropertyConcern;
import org.polymap.core.runtime.config.Property;

/**
 * Synchronizes the value of a {@link Property} of an {@link OpenLayersObject} with
 * the property of the JavaScript object.
 * 
 * @see OpenLayersProperty
 * @author <a href="http://www.polymap.de">Falko Bräutigam</a>
 */
public class OpenLayersPropertyConcern
        extends DefaultPropertyConcern {

    private static Log log = LogFactory.getLog( OpenLayersPropertyConcern.class );
    
    @Override
    public Object doSet( Object obj, Property prop, Object value ) {
        log.info( obj.getClass().getSimpleName() + "." + prop.info().getName() + " = " + value );

        // is the object created as JS on the client already?
        if (((OpenLayersObject)obj).getObjRef() != null) {

            OpenLayersProperty a = prop.info().getAnnotation( OpenLayersProperty.class );
            String propName = a != null ? a.value() : prop.info().getName();

            Object jsonValue = propertyAsJson( prop, value );
            ((OpenLayersObject)obj).setAttribute( propName, jsonValue );
        }
        return value;
    }
    
    
    /**
     * 
     */
    public static Object propertyAsJson( Property prop, Object value ) {
        if (value instanceof Jsonable) {
            return new Unquoted( ((Jsonable)value).toJson().toString() );
        }
        else if (value instanceof OpenLayersObject) {
            return new Unquoted( ((OpenLayersObject)value).getJSObjRef() );
        }
        else {
            return value;
        }
    }
    
    
    /**
     * 
     */
    public static String propertiesAsJson( Object obj ) {
        JSONObject json = new JSONObject();
        try {
            for (Class cl = obj.getClass(); cl != null; cl = cl.getSuperclass()) {
                for (Field f : cl.getDeclaredFields()) {
                    if (Property.class.isAssignableFrom( f.getType() )) {
                        f.setAccessible( true );
                        Property prop = (Property)f.get( obj );
                        Object value = prop.get();
                        Object jsonValue = propertyAsJson( prop, value );

                        OpenLayersProperty a = f.getAnnotation( OpenLayersProperty.class );
                        String name = a != null ? a.value() : f.getName();

                        json.put( name, jsonValue );
                    }
                }
            }
            return json.toString();
        }
        catch (Exception e) {
            throw new ConfigurationException( e );
        }
    }

    
    /**
     * 
     */
    public static class Unquoted
            implements JSONString {

        private String      value;
        
        public Unquoted( String value ) {
            this.value = value;
        }

        @Override
        public String toJSONString() {
            return value;
        }
    }
}
