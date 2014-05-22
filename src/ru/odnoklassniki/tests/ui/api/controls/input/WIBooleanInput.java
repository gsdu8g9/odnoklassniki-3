package ru.odnoklassniki.tests.ui.api.controls.input;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;

/**
 * Boolean input web element
 *
 */
public abstract class WIBooleanInput extends WIInput<Boolean> {

	public WIBooleanInput(IWIRoad road, String localor, String name) {
		super(road, localor, name);
	}

}
