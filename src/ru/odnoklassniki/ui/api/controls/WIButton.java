package ru.odnoklassniki.ui.api.controls;

import static ru.odnoklassniki.tests.ui.api.Messages.TEST_EXPECTED_ENABLED;
import static ru.odnoklassniki.tests.ui.api.Messages.TEST_EXPECTED_VISIBLE;

import org.testng.Assert;

import ru.odnoklassniki.tests.common.Scenario;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.common.WIDefaultRoad;
import ru.odnoklassniki.tests.ui.api.locale.Locale;


public class WIButton extends WIElement {
	
	public static class Road extends WIDefaultRoad {

		private WIButton mButton;

		public Road(WIButton aButton) {
			super(aButton);
			mButton = aButton;
		}

		@Override
		public void go() {
			mButton.click();
		}

	}
	
	public static class Submit extends WIButton {

		public Submit(IWIRoad aRoad, Locale aName) {
			super(aRoad, "//input[@type='submit' and @value='" + aName.getValue() + "']", aName.getValue());
		}
		
	}
	
	public static class Link extends WIButton {

		public Link(IWIRoad aRoad, Locale aName) {
			super(aRoad, "//a[text()='" + aName.getValue() + "']", aName.getValue());
		}
		
	}
	

	public static final String WI_BUTTON_TYPE = "button";

	public WIButton(IWIRoad aRoad, String aId, String aName) {
		super(aRoad, aId, aName, WI_BUTTON_TYPE);		
	}
	
	public void click() {
		Scenario.ui.info("Click " + this);
		if (isVisible()) {
			getBrowser().click(getGlobalID());
		} else {
			Assert.fail(TEST_EXPECTED_VISIBLE.getProblem(this));
		}
	}

}
