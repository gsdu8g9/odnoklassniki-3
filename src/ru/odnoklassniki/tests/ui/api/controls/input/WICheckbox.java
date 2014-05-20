package ru.odnoklassniki.tests.ui.api.controls.input;

import static ru.odnoklassniki.tests.ui.api.Messages.LOG_CHECKBOX_CHECK;
import static ru.odnoklassniki.tests.ui.api.Messages.LOG_CHECKBOX_UNCHECK;
import static ru.odnoklassniki.tests.ui.api.Messages.TEST_EXPECTED_ENABLED;
import ru.odnoklassniki.tests.common.Scenario;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;

import com.thoughtworks.selenium.Wait;

public class WICheckbox extends WIBooleanInput {

	public WICheckbox(IWIRoad aRoad, String aId, String aName) {
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

	public boolean isChecked(){
		return getValue();
	}

	public WICheckbox waitChecked(){
		new Wait() {
			@Override
			public boolean until() {
				return isChecked();
			}
		}.wait(TEST_EXPECTED_ENABLED.getProblem(this));
		return this;
	}
	
	public WICheckbox waitUnchecked(){
		new Wait() {
			@Override
			public boolean until() {
				return ! isChecked();
			}
		}.wait(TEST_EXPECTED_ENABLED.getProblem(this));
		return this;
	}

	public void check() {
		if (!getValue()){
			Scenario.ui.info(LOG_CHECKBOX_CHECK.getProblem(this));
			click();
			waitChecked();
		}
	}
	
	public void uncheck() {
		if (getValue()){
			Scenario.ui.info(LOG_CHECKBOX_UNCHECK.getProblem(this));
			click();
			waitUnchecked();
		}
	}

}
