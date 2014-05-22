package ru.odnoklassniki.tests.ui.api.controls;

import static ru.odnoklassniki.tests.ui.api.Messages.LOG_CLICK;
import ru.odnoklassniki.tests.common.Loggers;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.common.WIDefaultRoad;

/**
 * Clickable web element 
 *
 */
public class WIClickable extends WIElement {

	public static class Road extends WIDefaultRoad {

		private WIClickable clickable;

		public Road(WIClickable clickable) {
			super(clickable);
			this.clickable = clickable;
		}

		@Override
		public void go() {
			clickable.click();
		}

	}

	protected WIClickable(IWIRoad road, String locator, String name, String type) {
		super(road, locator, name, type);
	}

	public IWIRoad getRoad() {
		return new Road(this);
	}

	/**
	 * Click web element
	 */
	public void click() {
		go();
		getBrowser().click(getGlobalID());
		Loggers.ui.info(LOG_CLICK.getValue(this));
	}

}
