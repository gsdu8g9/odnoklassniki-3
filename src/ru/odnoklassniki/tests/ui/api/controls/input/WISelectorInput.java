package ru.odnoklassniki.tests.ui.api.controls.input;

import static ru.odnoklassniki.tests.ui.api.Messages.LOG_SELECTOR_SETVALUE;
import ru.odnoklassniki.tests.common.Scenario;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;


public class WISelectorInput extends WITextInput {

	public WISelectorInput(IWIRoad aRoad, String aId, String aName) {
		super(aRoad, aId, aName);
	}

	@Override
	public void setValue(String aValue) {
		Scenario.ui.info(LOG_SELECTOR_SETVALUE.getProblem(this, aValue));
		getBrowser().select(getGlobalID(), aValue);
	}

	@Override
	public String getValue() {
		return getBrowser().getSelectedLabel(getGlobalID());
	}

}
