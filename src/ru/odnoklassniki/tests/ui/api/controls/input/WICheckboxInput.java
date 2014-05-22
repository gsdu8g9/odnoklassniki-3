package ru.odnoklassniki.tests.ui.api.controls.input;

import static ru.odnoklassniki.tests.ui.api.Messages.LOG_CHECKBOX_CHECK;
import static ru.odnoklassniki.tests.ui.api.Messages.LOG_CHECKBOX_UNCHECK;
import static ru.odnoklassniki.tests.ui.api.Messages.TEST_CHECKED;
import static ru.odnoklassniki.tests.ui.api.Messages.TEST_UNCHECKED;
import ru.odnoklassniki.tests.common.Loggers;
import ru.odnoklassniki.tests.common.Requirements;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;

import com.thoughtworks.selenium.Wait;

/**
 * Checkbox web element
 * 
 */
public class WICheckboxInput extends WIBooleanInput {

	public WICheckboxInput(IWIRoad road, String locator, String name) {
		super(road, locator, name);
	}

	@Override
	public void setValue(Boolean value) {
		Requirements.notNull(value, "value");
		if (value) {
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

	/**
	 * Check checkbox state
	 * 
	 * @return checkbox state
	 */
	public boolean isChecked() {
		return getValue();
	}

	/**
	 * Wait checkbox is checked
	 * 
	 * @return self
	 */
	public WICheckboxInput waitChecked() {
		new Wait() {
			@Override
			public boolean until() {
				return isChecked();
			}
		}.wait(TEST_CHECKED.getValue(this));
		return this;
	}

	/**
	 * Wait checkbox is unchecked
	 * 
	 * @return self
	 */
	public WICheckboxInput waitUnchecked() {
		new Wait() {
			@Override
			public boolean until() {
				return !isChecked();
			}
		}.wait(TEST_UNCHECKED.getValue(this));
		return this;
	}

	/**
	 * Check checkbox
	 */
	public void check() {
		if (!getValue()) {
			Loggers.ui.info(LOG_CHECKBOX_CHECK.getValue(this));
			click();
			waitChecked();
		}
	}

	/**
	 * Uncheck checkbox
	 */
	public void uncheck() {
		if (getValue()) {
			Loggers.ui.info(LOG_CHECKBOX_UNCHECK.getValue(this));
			click();
			waitUnchecked();
		}
	}

}
