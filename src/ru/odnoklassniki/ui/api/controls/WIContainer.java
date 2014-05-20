package ru.odnoklassniki.ui.api.controls;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;

public abstract class WIContainer extends WIElement {
	
	public WIContainer(IWIRoad aRoad, String aId, String aName, String aType) {
		super(aRoad, aId, aName, aType);
	}

}
