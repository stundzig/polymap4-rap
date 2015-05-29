/*
 * polymap.org Copyright 2009-2013, Polymap GmbH. All rights reserved.
 * 
 * This is free software; you can redistribute it and/or modify it under the terms of
 * the GNU Lesser General Public License as published by the Free Software
 * Foundation; either version 3 of the License, or (at your option) any later
 * version.
 * 
 * This software is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Lesser General Public License for more details.
 */
package org.polymap.rap.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;

public class DateTimeTab
        extends DemoTab {


    public DateTimeTab() {
        super( "DateTime" );
    }

    private final static Log log = LogFactory.getLog( DateTimeTab.class );


    @Override
    protected void createDemoControls( Composite parent ) {
        // parent.setLayout( new FillLayout() );

        DateTime date = new DateTime( parent, SWT.BORDER );
    }


    @Override
    protected void createStyleControls( Composite parent ) {
        // TODO Auto-generated method stub

    }
}
