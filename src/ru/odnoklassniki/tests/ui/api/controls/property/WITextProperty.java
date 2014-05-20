package ru.odnoklassniki.tests.ui.api.controls.property;

import org.testng.Assert;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.locale.Locale;


public class WITextProperty extends WIProperty<String> {

	public WITextProperty(IWIRoad road, String id, String name) {
		super(road, id, name);
	}

	public WITextProperty(IWIRoad road, Locale aName) {
		super(road, "//*[text()='" + aName.getValue() + "']", aName.getName());
	}
	
	@Override
	protected String convert(String text) {
		return text;
	}

	public void assertEmpty() {
		Assert.assertEquals(getValue(), "", this + " value");
	}
	
	public void assertValue(Locale value) {
		super.assertValue(value.getValue());
	}
	
}