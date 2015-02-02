/* 
 * polymap.org
 * Copyright (C) 2013, Falko Bräutigam. All rights reserved.
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
package org.polymap.rap.openlayers.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Simple helper to build JavaScript arrays.
 */
public final class JSonBuilder {

	Map<String, String> values = new HashMap<String, String>();

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("{");
		for (Entry<String, String> entry : values.entrySet()) {
			if (builder.length() > 1) {
				builder.append(", ");
			}
			builder.append(entry.getKey()).append(": ")
					.append(entry.getValue());
		}
		return builder.append("}").toString();
	}

	public JSonBuilder add(String key, String value) {
		values.put(key, value);
		return this;
	}

	public JSonBuilder addQuoted(String key, String value) {
		return add(key, "'" + value + "'");
	}

	public JSonBuilder add(String key, Double width) {
		return add(key, String.valueOf(width));
	}

	public JSonBuilder addQuoted(String key, Iterable<String> values) {
		StringBuilder builder = new StringBuilder("[");
		for (String value : values) {
			if (builder.length() > 1) {
				builder.append(", ");
			}
			builder.append("'").append(value).append("'");
		}
		builder.append("]");
		return add(key, builder.toString());
	}

	public JSonBuilder add(String key, Iterable<String> values) {
		StringBuilder builder = new StringBuilder("[");
		for (String value : values) {
			if (builder.length() > 1) {
				builder.append(", ");
			}
			builder.append(value);
		}
		builder.append("]");
		return add(key, builder.toString());
	}
}
