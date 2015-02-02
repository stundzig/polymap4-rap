package org.polymap.rap.openlayers.types;

public class Attribution {

	private String html;

	public Attribution(String html) {
		this.html = html;
	}

	public String html() {
		return html;
	}

	public String asJson() {
		return "new ol.Attribution({ html: '" + html + "'  })";
	}
}
