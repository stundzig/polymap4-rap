package org.polymap.rap.demo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.rap.rwt.service.ServerPushSession;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.polymap.core.runtime.Polymap;

public class DemoEntryPoint
        extends AbstractEntryPoint {

    private final static Log log         = LogFactory.getLog( DemoEntryPoint2.class );

    final ServerPushSession  pushSession = new ServerPushSession();

    //
    // @Override
    // protected Shell createShell(Display display) {
    // // display.setSynchronizer(new ClusteredSynchronizer(display));
    // return super.createShell(display);
    // }
    private Tree             menu;

    private Composite        content;

    private DemoTab          currentTab;


    @Override
    protected void createContents( Composite parent ) {
        // pushSession.start();
        // parent.addDisposeListener( new DisposeListener() {
        //
        // @Override
        // public void widgetDisposed( DisposeEvent arg0 ) {
        // pushSession.stop();
        // }
        // } );
        parent.setLayout( new GridLayout( 2, false ) );
        Color backgroundColor = new Color( parent.getDisplay(), 0x31, 0x61, 0x9C );
        Composite header = new Composite( parent, SWT.NONE );
        header.setBackground( backgroundColor );
        header.setBackgroundMode( SWT.INHERIT_DEFAULT );
        header.setLayoutData( new GridData( SWT.FILL, SWT.NONE, true, false, 2, 1 ) );
        Label label = new Label( header, SWT.NONE );
        label.setText( "Polymap OpenLayers RAP Demo" );
        label.setForeground( parent.getDisplay().getSystemColor( SWT.COLOR_WHITE ) );
        label.setBounds( 40, 30, 250, 30 );

        menu = new Tree( parent, SWT.FULL_SELECTION );
        menu.setLayoutData( new GridData( SWT.NONE, SWT.FILL, false, false, 1, 1 ) );
        content = new Composite( parent, SWT.NONE );
        // content.setLayout( new GridLayout( 1, false ) );
        content.setLayout( new StackLayout() );
        content.setLayoutData( new GridData( SWT.FILL, SWT.FILL, true, true, 1, 1 ) );

        StatusBar status = StatusBar.getInstance().create( parent, SWT.NONE );
        status.addInfo( "Polymap RAP Openlayers Demo started..." );
        // status.setLayoutData( new GridData( SWT.FILL, SWT.NONE, true, false, 2, 1
        // ) );
        status.setLayoutData( new GridData( SWT.FILL, SWT.NONE, true, false, 2, 1 ) );

        fillTree( parent );
    }


    private void fillTree( Composite parent ) {
        // final HashMap<String,DemoTab> exampleMap = new HashMap<String,DemoTab>();
        // final BrowserNavigation navigation = RWT.getClient().getService(
        // BrowserNavigation.class );
        for (DemoTab tab : createExampleTabs()) {
            TreeItem item = new TreeItem( menu, SWT.NONE );
            item.setText( tab.getName() );
            item.setData( tab );
            // tab.setData( item );
            // exampleMap.put( tab.getId(), tab );
        }
        menu.addListener( SWT.Selection, event -> {
            log.info( "select " + ((DemoTab)event.item.getData()).getName() );
            selectTab( (DemoTab)event.item.getData() );
            // navigation.pushState( tab.getId(), null );
            } );
        // navigation.addBrowserNavigationListener( new BrowserNavigationListener() {
        //
        // public void navigated( BrowserNavigationEvent event ) {
        // DemoTab tab = exampleMap.get( event.getState() );
        // if (tab != null) {
        // menu.select( (TreeItem)tab.getData() );
        // menu.showSelection();
        // selectTab( tab );
        // }
        // }
        // } );
        menu.select( menu.getItem( 0 ) );
        selectTab( (DemoTab)menu.getItem( 0 ).getData() );
    }


    private void selectTab( DemoTab tab ) {
        // for (Control children : content.getChildren()) {
        // children.dispose();
        // }
        final Composite next = tab.createContents( content );
        StackLayout layout = (StackLayout)content.getLayout();
        layout.topControl = next;
        // next.setVisible( true );
        // next.moveAbove( current );
        // currentTab = tab;
        // }
        next.redraw();
        content.getParent().layout( true, true );
//        Polymap.executorService().execute( ( ) -> {
//            next.layout( true, true );
//        } );
    }


    //
    // private FormData createLayoutDataForHeader() {
    // FormData layoutData = new FormData();
    // layoutData.left = new FormAttachment( 0, 0 );
    // layoutData.right = new FormAttachment( 100, 0 );
    // layoutData.top = new FormAttachment( 0, 0 );
    // layoutData.height = 80;
    // return layoutData;
    // }
    //
    //
    // private FormData createLayoutDataForTree(Composite top) {
    // FormData layoutData = new FormData();
    // layoutData.top = new FormAttachment( top, 0 );
    // layoutData.left = new FormAttachment( 0, 0 );
    // layoutData.bottom = new FormAttachment( 100, 0 );
    // layoutData.width = 190;
    // return layoutData;
    // }
    //
    //
    // private FormData createLayoutDataForExampleParent(Composite top) {
    // FormData layoutData = new FormData();
    // layoutData.top = new FormAttachment( top, 0 );
    // layoutData.left = new FormAttachment( menu, 10 );
    // layoutData.right = new FormAttachment( 100, 0 );
    // return layoutData;
    // }
    //
    // private FormData createLayoutDataForStatusBar(Composite top) {
    // FormData layoutData = new FormData();
    // layoutData.top = new FormAttachment( top, 0 );
    // layoutData.left = new FormAttachment( 0, 0 );
    // layoutData.right = new FormAttachment( 100, 0 );
    // layoutData.height = 80;
    // layoutData.bottom = new FormAttachment( 100, 0 );
    // return layoutData;
    // }

    private static DemoTab[] createExampleTabs() {
        return new DemoTab[] { new ScaleLineControlTab(), new DateTimeTab(), new ZoomControlTab(),
                new ZoomSliderControlTab() };
    }
}
