# OpenLayers Widget

This plugin adds a [OpenLayers3](http://openlayers.org) based map widget to the eclipse RAP framework.

## Sample Usage

```java
OlWidget olwidget = new OlWidget( parent, SWT.MULTI | SWT.WRAP | SWT.BORDER );
OlMap map = new OlMap( olwidget, new View()
        .projection.put( new Projection( "EPSG:3857", Units.m ) )
        .center.put( new Coordinate( 0, 0 ) )
        .zoom.put( 1 ) );

map.addLayer( new TileLayer()
        .source.put( new MapQuestSource( MapQuestSource.Type.hyb ) ) );
```

See also [DemoEntryPoint.java](src/org/polymap/rap/openlayers/demo/DemoEntryPoint.java)

## Todos

* resources should be load locally
* migrate all controls, layers and types to new property API (see issue #4)
* more tests
* maven/tycho based builds?
 


