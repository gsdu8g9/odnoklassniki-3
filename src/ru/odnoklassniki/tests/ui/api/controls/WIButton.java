package ru.odnoklassniki.tests.ui.api.controls;

import static ru.odnoklassniki.tests.ui.api.Messages.LOG_CLICK;
import static ru.odnoklassniki.tests.ui.api.Messages.TEST_EXPECTED_VISIBLE;

import org.testng.Assert;

import ru.odnoklassniki.tests.common.Loggers;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.common.WIDefaultRoad;
import ru.odnoklassniki.tests.ui.api.locale.Text;

public class WIButton extends WIElement {

	public static class Road extends WIDefaultRoad {

		private WIButton button;

		public Road(WIButton button) {
			super(button);
			this.button = button;
		}

		@Override
		public void go() {
			button.click();
		}

	}

	public static class Submit extends WIButton {

		public Submit(IWIRoad road, Text name) {
			super(road, "//input[@type='submit' and @value='"
			        + name.getValue() + "']", name.getName());
		}

	}

	public static class Link extends WIButton {

		public Link(IWIRoad road, Text name) {
			super(road, "//a[text()='" + name.getValue() + "']", name
			        .getValue());
		}

	}

	public static final String WI_BUTTON_TYPE = "button";

	public WIButton(IWIRoad road, String locator, String name) {
		super(road, locator, name, WI_BUTTON_TYPE);
	}

	public void click() {
		Loggers.ui.info(LOG_CLICK.getValue(this));
		if (isVisible()) {
			getBrowser().click(getGlobalID());
		} else {
			Assert.fail(TEST_EXPECTED_VISIBLE.getValue(this));
		}
	}

}
