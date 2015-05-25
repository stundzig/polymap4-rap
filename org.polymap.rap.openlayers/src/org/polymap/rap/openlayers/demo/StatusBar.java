package org.polymap.rap.openlayers.demo;

import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.SingletonUtil;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class StatusBar {

	private Label label;

	// private static final Image INFO =
	// Activator.getImageDescriptor("theme/fancy/icons/down.png").createImage();
	public static StatusBar getInstance() {
		return SingletonUtil.getSessionInstance(StatusBar.class);
	}

	public void addInfo(String message) {
		// label.setImage(getImage("down.png"));
		label.setData(RWT.CUSTOM_VARIANT, "info");
		label.setText(message);
	}

	public void addError(String message) {
		label.setData(RWT.CUSTOM_VARIANT, "error");
		label.setText(message);
	}

	public void setLayoutData(GridData gridData) {
		label.setLayoutData(gridData);
	}

	public StatusBar create(Composite parent, int style) {
		label = new Label(parent, style);
		return this;
	}

}
