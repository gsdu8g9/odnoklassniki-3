package ru.odnoklassniki.tests.ui.api.common;

import ru.odnoklassniki.tests.ui.api.WIBrowser;

public class WIDefaultRoad implements IWIRoad {

	private IWIRoad m_parentRoad;
	
	public WIDefaultRoad(IWIRoad aRoad) {
		m_parentRoad = aRoad;
	}
	
	@Override
	public WIBrowser getBrowser() {
		return m_parentRoad == null ? null : m_parentRoad.getBrowser();
	}

	@Override
	public void go() {
		if (null != m_parentRoad) {
			m_parentRoad.go();
		}
	}

	@Override
	public IWIRoad getParent() {
		return m_parentRoad;
	}

}
