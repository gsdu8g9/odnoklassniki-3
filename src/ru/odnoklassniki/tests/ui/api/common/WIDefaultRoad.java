package ru.odnoklassniki.tests.ui.api.common;

import ru.odnoklassniki.tests.ui.api.WIBrowser;

/**
 * Default way to find out object if parent object is visible
 * 
 */
public class WIDefaultRoad implements IWIRoad {

	private IWIRoad road;

	public WIDefaultRoad(IWIRoad road) {
		this.road = road;
	}

	@Override
	public WIBrowser getBrowser() {
		return null == road ? null : road.getBrowser();
	}

	@Override
	public void go() {
		if (null != road) {
			road.go();
		}
	}

	@Override
	public IWIRoad getParent() {
		return road;
	}

}
