package org.polymap.rap.openlayers.base;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class OlCommandTest {

    @Test
    public void testSimpleConversation() {
        OlCommand c = new OlCommand( "doSomething()");
        OlCommand c2 = new OlCommand( "doSomething()");
        
        assertEquals( c,  c2 );
        assertEquals( "doSomething()", c.getCommand() );
        assertEquals( "doSomething()", c.toString() );
        assertEquals( "{\"code\":\"doSomething()\"}", c.getJson().toString() );
    }
}
