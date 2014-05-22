package ru.odnoklassniki.tests.ui.api.controls.input;

import static ru.odnoklassniki.tests.ui.api.Messages.TEST_EXPECTED_INVISIBLE;
import static ru.odnoklassniki.tests.ui.api.Messages.TEST_EXPECTED_VISIBLE;
import static ru.odnoklassniki.tests.ui.api.Messages.TEST_VALUE;

import org.testng.Assert;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.controls.WIElement;

/**
 * Class WIInput is an abstract superclass for all WI objects which have get/set
 * value behavior
 * 
 */
public abstract class WIInput<T> extends WIElement {

	private static final String WI_INPUT_TYPE = "input";

	public WIInput(IWIRoad road, String locator, String name) {
		super(road, locator, name, WI_INPUT_TYPE);
	}

	public abstract void setValue(T value);

	public abstract T getValue();

	public void assertValue(T value) {
		Assert.assertEquals(getValue(), value, TEST_VALUE.getValue(this));
	}

	public void assertVisible() {
		Assert.assertTrue(isVisible(), TEST_EXPECTED_VISIBLE.getValue(this));
	}

	public void assertInvisible() {
		Assert.assertTrue(!isVisible(), TEST_EXPECTED_INVISIBLE.getValue(this));
	}

}
