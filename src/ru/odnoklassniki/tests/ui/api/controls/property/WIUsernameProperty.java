package ru.odnoklassniki.tests.ui.api.controls.property;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;

public class WIUsernameProperty extends WITextProperty {

	public WIUsernameProperty(IWIRoad road, String id, String name) {
		super(road, id, name);
	}

	@Override
	protected String convert(String text) {
		// There is no space before user name since they are placed in two
		// different SPANs
		return text.replaceFirst("Logged in as(.*)", "$1");
	}

}
