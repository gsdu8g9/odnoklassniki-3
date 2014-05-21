package ru.odnoklassniki.tests.ui.api.controls;

import static ru.odnoklassniki.tests.ui.api.Messages.LOG_CLICK;
import ru.odnoklassniki.tests.common.Loggers;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.common.WIDefaultRoad;

public class WIClickable extends WIElement {

	public static class Road extends WIDefaultRoad {

		private WIClickable clickable;

		public Road(WIClickable aClickable) {
			super(aClickable);
			clickable = aClickable;
		}

		@Override
		public void go() {
			clickable.click();
		}

	}

	protected WIClickable(IWIRoad aRoad, String aId, String aName, String aType) {
		super(aRoad, aId, aName, aType);
	}

	public IWIRoad getRoad() {
		return new Road(this);
	}

	public void click() {
		go();
		getBrowser().click(getGlobalID());
		Loggers.ui.info(LOG_CLICK.getValue(this));
	}

}
