package ru.odnoklassniki.tests.ui.api.dialog;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.controls.WIButton;
import ru.odnoklassniki.tests.ui.api.locale.Locale;


public abstract class WIOkDialog extends WIDialog {
	
	public final WIButton btnOk = new WIButton.Submit(this, Locale.BTN_CLOSE);
	
	public WIOkDialog(IWIRoad road, Locale aName) {
		super(road, aName);
	}

	public void waitAndClose() {
		waitAccessible();
		close();
	}

	public void close() {
		btnOk.click();
		waitInvisible();
	}

}
