package ru.odnoklassniki.tests.ui.api.controls.input;

import static ru.odnoklassniki.tests.ui.api.Messages.LOG_TYPE;
import ru.odnoklassniki.tests.common.Loggers;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;

public class WITextInput extends WIInput<String> {

	public WITextInput(IWIRoad road, String locator, String name) {
		super(road, locator, name);
	}

	@Override
	public void setValue(String value) {
		Loggers.ui.info(LOG_TYPE.getValue(value, this));
		getBrowser().type(getGlobalID(), value);
	}

	@Override
	public String getValue() {
		return getBrowser().getValue(getGlobalID());
	}

}
