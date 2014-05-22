package ru.odnoklassniki.tests.ui.api.common;

import ru.odnoklassniki.tests.ui.api.WIBrowser;

public class WIDefaultRoad implements IWIRoad {

	private IWIRoad parentRoad;

	public WIDefaultRoad(IWIRoad aRoad) {
		parentRoad = aRoad;
	}

	@Override
	public WIBrowser getBrowser() {
		return null == parentRoad ? null : parentRoad.getBrowser();
	}

	@Override
	public void go() {
		if (null != parentRoad) {
			parentRoad.go();
		}
	}

	@Override
	public IWIRoad getParent() {
		return parentRoad;
	}

}
