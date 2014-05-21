package ru.odnoklassniki.tests.ui.api.controls.input;

import ru.odnoklassniki.tests.common.Loggers;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;


public class WITextInput extends WIInput<String> {

	public WITextInput(IWIRoad aRoad, String aId, String name) {
		super(aRoad, aId, name);
	}

	@Override
	public void setValue(String aValue) {
		Loggers.ui.info("Type \"" + aValue + "\" at " + this);
		getBrowser().type(getGlobalID(), aValue);
	}

	@Override
	public String getValue() {
		return getBrowser().getValue(getGlobalID());
	}

}
