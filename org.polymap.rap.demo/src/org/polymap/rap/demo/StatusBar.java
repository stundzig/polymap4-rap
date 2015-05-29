/*
 * polymap.org
 * Copyright (C) 2009-2015 Polymap GmbH. All rights reserved.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */
package org.polymap.rap.demo;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.SingletonUtil;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

/**
 * 
 * @author <a href="http://stundzig.it">Steffen Stundzig</a>
 *
 */
public class StatusBar {

    private Label label;


    // private static final Image INFO =
    // Activator.getImageDescriptor("theme/fancy/icons/down.png").createImage();
    public static StatusBar getInstance() {
        return SingletonUtil.getSessionInstance( StatusBar.class );
    }


    public void addInfo( Control parent, String message ) {
        parent.getDisplay().asyncExec( new Runnable() {

            @Override
            public void run() {
                // label.setImage(getImage("down.png"));
                label.setData( RWT.CUSTOM_VARIANT, "info" );
                label.setText( message );
            }
        } );

    }


    public void addError( Control parent, String message ) {
        parent.getDisplay().asyncExec( new Runnable() {

            @Override
            public void run() {
                // label.setImage(getImage("down.png"));
                label.setData( RWT.CUSTOM_VARIANT, "error" );
                label.setText( message );
            }
        } );
    }


    public void setLayoutData( GridData gridData ) {
        label.setLayoutData( gridData );
    }


    public StatusBar create( Composite parent, int style ) {
        label = new Label( parent, style );
        return this;
    }

}
