package ru.odnoklassniki.tests.ui.api.controls.input;

import static ru.odnoklassniki.tests.ui.api.Messages.TEST_VALUE;

import org.testng.Assert;

import ru.odnoklassniki.tests.common.Requirements;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.controls.WIElement;

/**
 * Class WIInput is an abstract superclass for all web objects which have
 * get/set value behavior
 * 
 */
public abstract class WIInput<T> extends WIElement {

	private static final String WI_INPUT_TYPE = "input";

	public WIInput(IWIRoad road, String locator, String name) {
		super(road, locator, name, WI_INPUT_TYPE);
	}

	/**
	 * Set input value
	 * 
	 * @param value
	 *            value
	 */
	public abstract void setValue(T value);

	/**
	 * Get input value
	 * 
	 * @return value
	 */
	public abstract T getValue();

	/**
	 * Assert input has specified value
	 * @param value expected value
	 */
	public void assertValue(T value) {
		Requirements.notNull(value, "value");
		Assert.assertEquals(getValue(), value, TEST_VALUE.getValue(this));
	}

}
