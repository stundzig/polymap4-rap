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
package org.polymap.rap.updownload.download;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.widgets.ExternalBrowser;

import org.polymap.core.ui.FormDataFactory;
import org.polymap.core.ui.FormLayoutFactory;

/**
 * 
 * @author <a href="http://www.polymap.de">Falko Bräutigam</a>
 */
public class DownloadDialog
        extends Dialog {

    private String      url;

    private String      windowName;

    private int         windowFlags;

    private String      msg;
    
    
    public DownloadDialog( Shell parentShell, String url ) {
        super( parentShell );
        this.url = url;
        this.msg = "<a href=\"" + url + "\">Link...</a>";
        setShellStyle( SWT.RESIZE | SWT.DIALOG_TRIM | SWT.SHEET );
    }

    @SuppressWarnings("hiding")
    public DownloadDialog openBrowserWindow( String windowName, int windowFlags ) {
        this.windowName = windowName;
        this.windowFlags = windowFlags;
        return this;
    }

    public DownloadDialog setMessage( String msg ) {
        this.msg = msg;
        DownloadService.log.info( msg );
        return this;
    }
    
    @Override
    public int open() {
        if (windowName != null) {
            ExternalBrowser.open( windowName, url, windowFlags );
        }
        return super.open();
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        createButton( parent, IDialogConstants.CANCEL_ID, IDialogConstants.get().CANCEL_LABEL, false );
    }

    @Override
    protected Control createDialogArea( Composite parent ) {
        getShell().setText( "Download" );
        getShell().setSize( 520, 350 );
        Rectangle displaySize = getShell().getDisplay().getClientArea();
        getShell().setLocation( (displaySize.width - 520) / 2, (displaySize.height - 350) / 2 );

        Composite result = (Composite)super.createDialogArea( parent );
        result.setLayout( FormLayoutFactory.defaults().create() );
        
        Label link = new Label( result, SWT.WRAP );
        link.setText( msg );
        // after setText() in order to prevent validation, which fails
        link.setData( RWT.MARKUP_ENABLED, Boolean.TRUE );
        link.setLayoutData( FormDataFactory.filled()/*.clearRight().width( 400 )*/.create() );
        
        return result;
    }
    
}