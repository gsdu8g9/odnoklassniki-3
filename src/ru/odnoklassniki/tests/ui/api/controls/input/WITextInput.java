package ru.odnoklassniki.tests.ui.api.controls.input;

import ru.odnoklassniki.tests.common.Scenario;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.controls.property.WITextProperty;
import ru.odnoklassniki.tests.ui.api.locale.Locale;


public class WITextInput extends WIInput<String> {

	public static class Label extends WITextInput {
		
		public final WITextProperty propError;
		
		public Label(IWIRoad aRoad, Locale aName) {
			super(aRoad, "//label[text()='" + aName.getValue() + "']/ancestor::div[1]//input", aName.getName());
			propError = new WITextProperty(aRoad, "//label[text()='" + aName.getValue() + "']/ancestor::div[1]//span[2]", aName.getName() + " error");
		}
		
	}
	
	public WITextInput(IWIRoad aRoad, String aId, String name) {
		super(aRoad, aId, name);
	}

	@Override
	public void setValue(String aValue) {
		Scenario.ui.info("Type \"" + aValue + "\" at " + this);
		getBrowser().type(getGlobalID(), aValue);
	}

	@Override
	public String getValue() {
		return getBrowser().getValue(getGlobalID());
	}

}
