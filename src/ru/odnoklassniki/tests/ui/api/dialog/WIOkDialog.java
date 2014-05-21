package ru.odnoklassniki.tests.ui.api.dialog;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.controls.WIButton;
import ru.odnoklassniki.tests.ui.api.locale.Text;


public abstract class WIOkDialog extends WIDialog {
	
	public final WIButton btnOk = new WIButton.Submit(this, Text.BTN_CLOSE);
	
	public WIOkDialog(IWIRoad road, Text aName) {
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
