package ru.odnoklassniki.tests.ui.api.dialog;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.controls.WIButton;
import ru.odnoklassniki.tests.ui.api.controls.input.WISelectorInput;
import ru.odnoklassniki.tests.ui.api.controls.input.WITextInput;
import ru.odnoklassniki.tests.ui.api.controls.property.WITextProperty;
import ru.odnoklassniki.tests.ui.api.locale.Locale;


public class WIProfilePersoanlDialog extends WIDialog {

	public final WITextInput.Label inpName = new WITextInput.Label(this, Locale.FIELD_NAME);

	public final WITextInput.Label inpSurname = new WITextInput.Label(this, Locale.FIELD_SURNAME);

	public final WISelectorInput inpBirthDay = new WISelectorInput(this, "field_bday", "Birth day");

	public final WISelectorInput inpBirthMonth = new WISelectorInput(this, "field_bmonth", "Birth month");

	public final WISelectorInput inpBirthYear = new WISelectorInput(this, "field_byear", "Birth year");

	public final WITextInput.Label inpCity = new WITextInput.Label(this, Locale.FIELD_CITY);

	public final WITextInput.Label inpBirthCity = new WITextInput.Label(this, Locale.FIELD_BIRTH_CITY);
	
	public final WITextProperty propNotification = new WITextProperty(this, Locale.MSG_PERSONAL_INFO_CHANGED);

	public final WIButton btnSave = new WIButton.Submit(this, Locale.BTN_SAVE);

	public final WIButton btnCancel = new WIButton.Link(this, Locale.BTN_CANCEL);

	public WIProfilePersoanlDialog(IWIRoad aRoad) { 
		super(aRoad, Locale.TITLE_CHANGE_PERSONAL_INFO);
	}

	public void save() {
		btnSave.click();
		// FIXME Incomplete
		waitInvisible();
	}
	
	public void cancel() {
		btnCancel.click();
		waitInvisible();
	}

}