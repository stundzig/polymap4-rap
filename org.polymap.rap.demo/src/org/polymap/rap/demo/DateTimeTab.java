package org.polymap.rap.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.polymap.rap.openlayers.OlWidget;

public class DateTimeTab
        extends DemoTab {

    private OlWidget olwidget;


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
