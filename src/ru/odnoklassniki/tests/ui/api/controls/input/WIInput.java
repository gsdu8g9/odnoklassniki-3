package ru.odnoklassniki.tests.ui.api.controls.input;

import org.testng.Assert;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.ui.api.controls.WIElement;


/**
 * Class WIInput is an abstract superclass for all WI objects which have get/set
 * value behavior
 * 
 */
public abstract class WIInput<T> extends WIElement {

	private static final String WI_INPUT_TYPE = "input";
	
	@Deprecated
	public WIInput(IWIRoad aRoad, String aId, String aName) {
		super(aRoad, aId, aName, WI_INPUT_TYPE);
	}
	
	public abstract void setValue(T aValue);

	public abstract T getValue();

	public void assertValue(T value) {
		Assert.assertEquals(getValue(), value, "Input " + this + " value");
	}
	
	public void assertVisible() {
		Assert.assertTrue(this.isVisible(), "Input " + this + " expected to be visible");
	}

	public void assertInvisible() {
		Assert.assertTrue(!this.isVisible(), "Input " + this + " expected to be invisible");
	}

}
