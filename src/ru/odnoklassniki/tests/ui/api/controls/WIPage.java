package ru.odnoklassniki.tests.ui.api.controls;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;

public class WIPage extends WIElement {

	public static final String WI_PAGE_TYPE = "page";

	public WIPage(IWIRoad aRoad, String aId, String aName) {
		super(aRoad, aId, aName, WI_PAGE_TYPE);
	}
	
	@Override
	public XPathBuilderBehaviour getRoadBuilderType() {
		return XPathBuilderBehaviour.GLOBAL;
	}
	
}
