package ru.odnoklassniki.tests.ui.api.controls;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;

public class WIPage extends WIElement {

	public static final String WI_PAGE_TYPE = "page";

	public WIPage(IWIRoad road, String locator, String name) {
		super(road, locator, name, WI_PAGE_TYPE);
	}
	
	@Override
	public XPathBuilderBehaviour getRoadBuilderType() {
		return XPathBuilderBehaviour.GLOBAL;
	}
	
}
