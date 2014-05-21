package ru.odnoklassniki.tests.ui.api.dialog;

import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.controls.WIButton;
import ru.odnoklassniki.tests.ui.api.controls.input.WICheckboxInput;
import ru.odnoklassniki.tests.ui.api.controls.input.WISelectorInput;
import ru.odnoklassniki.tests.ui.api.controls.input.WITextInput;
import ru.odnoklassniki.tests.ui.api.controls.property.WITextProperty;
import ru.odnoklassniki.tests.ui.api.locale.Text;

public class WIProfilePersoanlDialog extends WIDialog {

	public static class WIGenderInput extends WICheckboxInput {

		public WIGenderInput(IWIRoad aRoad, Text aLabel) {
			super(aRoad, "//label[text()='" + aLabel.getValue() + "']/ancestor::li//input", aLabel.getName());
		}

	}

	public static class WILabeledTextInput extends WITextInput {

		public final WITextProperty propError;

		public WILabeledTextInput(IWIRoad aRoad, Text aName) {
			super(aRoad, "//label[text()='" + aName.getValue() + "']/ancestor::div[1]//input", aName.getName());
			propError = new WITextProperty(aRoad, "//label[text()='" + aName.getValue()
			        + "']/ancestor::div[1]//span[2]", aName.getName() + " Error Message");
		}

	}

	public final WILabeledTextInput inpName = new WILabeledTextInput(this, Text.FIELD_NAME);
	public final WILabeledTextInput inpSurname = new WILabeledTextInput(this, Text.FIELD_SURNAME);

	public final WISelectorInput inpBirthDay = new WISelectorInput(this, "field_bday", "Birth Day");
	public final WISelectorInput inpBirthMonth = new WISelectorInput(this, "field_bmonth", "Birth Month");
	public final WISelectorInput inpBirthYear = new WISelectorInput(this, "field_byear", "Birth Year");

	public final WICheckboxInput inpMale = new WIGenderInput(this, Text.MALE);
	public final WICheckboxInput inpFemale = new WIGenderInput(this, Text.FEMALE);

	public final WILabeledTextInput inpCity = new WILabeledTextInput(this, Text.FIELD_CITY);
	public final WILabeledTextInput inpBirthCity = new WILabeledTextInput(this, Text.FIELD_BIRTH_CITY);

	public final WIButton btnSave = new WIButton.Submit(this, Text.BTN_SAVE);
	public final WIButton btnCancel = new WIButton.Link(this, Text.BTN_CANCEL);

	public final WITextProperty propNotification = new WITextProperty(this, Text.MSG_PERSONAL_INFO_CHANGED);

	public final WIButton btnClose = new WIButton.Submit(this, Text.BTN_CLOSE);

	public WIProfilePersoanlDialog(IWIRoad aRoad) {
		super(aRoad, Text.TITLE_CHANGE_PERSONAL_INFO);
	}

	public void save() {
		btnSave.click();

		propNotification.waitVisible();

		// NOTE: Since notification dialog has same title as original one
		// need to check previous content disappeared here but this should be
		// done in separate test

		btnClose.click();
		waitInvisible();
	}

	public void cancel() {
		btnCancel.click();
		waitInvisible();
	}

}