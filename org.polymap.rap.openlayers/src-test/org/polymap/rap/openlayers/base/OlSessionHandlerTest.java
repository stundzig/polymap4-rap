//package org.polymap.rap.openlayers.base;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertFalse;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Matchers.anyObject;
//import static org.mockito.Mockito.doCallRealMethod;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.*;
//
//import org.eclipse.rap.json.JsonObject;
//import org.json.JSONArray;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.polymap.rap.openlayers.base.OlEventListener.PayLoad;
//import org.polymap.rap.openlayers.base.OlPropertyConcern.Unquoted;
//import org.polymap.rap.openlayers.view.View;
//
//@RunWith(MockitoJUnitRunner.class)
//public class OlSessionHandlerTest {
//
//
//    @Test
//    public void testEventListener() {
//        OlSessionHandler osh = spy(OlSessionHandler.class);
//        OlEventListener listener = mock( OlEventListener.class );
//        PayLoad payload = new PayLoad();
//        payload.add( "p1_key", "p1_value" );
//        ol.addEventListener( "event", listener, payload );
//        
//        doCallRealMethod().when( osh).registerEventListener( anyObject(), eq("event"), eq(listener), eq(payload) );
//
//        ArgumentCaptor<JsonObject> argument = ArgumentCaptor.forClass( JsonObject.class );
//        verify( osh ).callRemote( eq("addListener"), argument.capture() );
//        assertEquals( "", argument.getValue().toString() );
//    }
//
//}
