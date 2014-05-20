package ru.odnoklassniki.ui.api.controls;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;

public class WIRequiredFieldHint extends WIContainer {

	public static final String WI_REQUIRED_FIELD_HINT_TYPE = "RequiredFieldHint";

	public WIRequiredFieldHint(IWIRoad road, String id, String aName) {
		super(road, id, aName, WI_REQUIRED_FIELD_HINT_TYPE);
	}

}
