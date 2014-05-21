package ru.odnoklassniki.tests.ui.api.dialog;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.controls.WIButton;
import ru.odnoklassniki.tests.ui.api.locale.Text;


public abstract class WISaveCancelDialog extends WIDialog {

	public WISaveCancelDialog(IWIRoad road, Text aName) {
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
