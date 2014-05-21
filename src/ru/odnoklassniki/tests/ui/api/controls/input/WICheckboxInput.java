package ru.odnoklassniki.tests.ui.api.controls.input;

import static ru.odnoklassniki.tests.ui.api.Messages.LOG_CHECKBOX_CHECK;
import static ru.odnoklassniki.tests.ui.api.Messages.LOG_CHECKBOX_UNCHECK;
import static ru.odnoklassniki.tests.ui.api.Messages.TEST_CHECKED;
import static ru.odnoklassniki.tests.ui.api.Messages.TEST_UNCHECKED;
import ru.odnoklassniki.tests.common.Loggers;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;

import com.thoughtworks.selenium.Wait;

public class WICheckboxInput extends WIBooleanInput {

	public WICheckboxInput(IWIRoad aRoad, String aId, String aName) {
		super(aRoad, aId, aName);
	}

	@Override
	public void setValue(Boolean aValue) {
		if (aValue) {
			check();
		} else {
			uncheck();
		}
	}

	@Override
	public Boolean getValue() {
		return getBrowser().isChecked(getGlobalID());
	}

	private void click() {
		getBrowser().click(getGlobalID());
	}

	public boolean isChecked() {
		return getValue();
	}

	public WICheckboxInput waitChecked() {
		new Wait() {
			@Override
			public boolean until() {
				return isChecked();
			}
		}.wait(TEST_CHECKED.getProblem(this));
		return this;
	}

	public WICheckboxInput waitUnchecked() {
		new Wait() {
			@Override
			public boolean until() {
				return !isChecked();
			}
		}.wait(TEST_UNCHECKED.getProblem(this));
		return this;
	}

	public void check() {
		if (!getValue()) {
			Loggers.ui.info(LOG_CHECKBOX_CHECK.getProblem(this));
			click();
			waitChecked();
		}
	}

	public void uncheck() {
		if (getValue()) {
			Loggers.ui.info(LOG_CHECKBOX_UNCHECK.getProblem(this));
			click();
			waitUnchecked();
		}
	}

}
