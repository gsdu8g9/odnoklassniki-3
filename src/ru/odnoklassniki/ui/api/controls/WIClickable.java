package ru.odnoklassniki.ui.api.controls;

import ru.odnoklassniki.tests.common.Scenario;
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
		// Simple "click" doesn't work here
//		getBrowser().clickAt(getGlobalID(), "1,1");
		getBrowser().click(getGlobalID());
		Scenario.ui.info("Click " + this);
	}
	
}
