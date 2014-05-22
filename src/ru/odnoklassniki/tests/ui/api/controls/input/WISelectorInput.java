package ru.odnoklassniki.tests.ui.api.controls.input;

import static ru.odnoklassniki.tests.ui.api.Messages.LOG_SELECTOR_SETVALUE;
import ru.odnoklassniki.tests.common.Loggers;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;

public class WISelectorInput extends WITextInput {

	public WISelectorInput(IWIRoad road, String locator, String name) {
		super(road, locator, name);
	}

	@Override
	public void setValue(String value) {
		Loggers.ui.info(LOG_SELECTOR_SETVALUE.getValue(this, value));
		// Use "select" instead of "type"
		getBrowser().select(getGlobalID(), value);
	}

	@Override
	public String getValue() {
		return getBrowser().getSelectedLabel(getGlobalID());
	}

}
