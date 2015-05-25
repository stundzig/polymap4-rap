package org.polymap.rap.openlayers.types;

import org.polymap.rap.openlayers.base.Jsonable;

public enum GeometryType implements Jsonable {
    Point, LineString, LinearRing, Polygon, MultiPoint, MultiLineString, MultiPolygon, GeometryCollection, Circle;

    @Override
    public Object toJson() {
        return "/** @type {ol.geom.GeometryType} */ '" + name() + "'";
    }
}
