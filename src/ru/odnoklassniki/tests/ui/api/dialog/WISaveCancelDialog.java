package ru.odnoklassniki.tests.ui.api.dialog;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.controls.WIButton;
import ru.odnoklassniki.tests.ui.api.locale.Locale;


public abstract class WISaveCancelDialog extends WIDialog {

	public WISaveCancelDialog(IWIRoad road, Locale aName) {
		super(road, aName);
	}

	public abstract WIButton getSave();
	
	public abstract WIButton getCancel();
	
	public void save() {
		getSave().click();
		waitInvisible();
	}
	
	public void cancel() {
		getCancel().click();
		waitInvisible();
	}
	
}
