package ru.odnoklassniki.tests.ui.api.controls;

import static ru.odnoklassniki.tests.ui.api.Messages.LOG_ELEMENT_INVISIBLE;
import static ru.odnoklassniki.tests.ui.api.Messages.LOG_ELEMENT_VISIBLE;
import static ru.odnoklassniki.tests.ui.api.Messages.LOG_ELEMENT_WAIT_INVISIBLE;
import static ru.odnoklassniki.tests.ui.api.Messages.LOG_ELEMENT_WAIT_VISIBLE;
import static ru.odnoklassniki.tests.ui.api.Messages.TEST_EXPECTED_INVISIBLE;
import static ru.odnoklassniki.tests.ui.api.Messages.TEST_EXPECTED_VISIBLE;

import org.testng.Assert;

import ru.odnoklassniki.tests.common.Loggers;
import ru.odnoklassniki.tests.common.Utils;
import ru.odnoklassniki.tests.common.Wait;
import ru.odnoklassniki.tests.ui.api.WIBrowser;
import ru.odnoklassniki.tests.ui.api.common.IWIRoad;
import ru.odnoklassniki.tests.ui.api.common.WIDefaultRoad;

import com.thoughtworks.selenium.SeleniumException;

public class WIElement implements IWIRoad {

	public enum XPathBuilderBehaviour {
		SKIP, USE, GLOBAL
	}

	private class Road extends WIDefaultRoad {

		public Road(IWIRoad road) {
			super(road);
		}

		@Override
		public final void go() {
			if (isVisible())
				return;
			super.go();
			waitVisible();
		}

	}

	private IWIRoad road;
	private String locator;
	private String name;
	private String type;

	public WIElement(IWIRoad road, String locator, String name, String type) {
		this.road = new Road(road);
		this.locator = locator;
		this.name = name;
		this.type = type;
	}

	@Override
	public WIBrowser getBrowser() {
		return road.getBrowser();
	}

	@Override
	public void go() {
		road.go();
	}

	@Override
	public IWIRoad getParent() {
		return road.getParent();
	}

	public XPathBuilderBehaviour getRoadBuilderType() {
		return XPathBuilderBehaviour.SKIP;
	}

	/**
	 * Method returns local (short) ID value. This value unique only inside root
	 * section. Use getRootID() to find out ID of root section and to make
	 * global unique ID. Don't use it for any Selenium actions.
	 * 
	 * @return Local ID value
	 */
	public String getLocalID() {
		return locator;
	}

	/**
	 * Method returns global unique ID value. Use this value for Selenium
	 * commands.
	 * 
	 * @return Global unique ID value
	 */
	public String getGlobalID() {
		if (null == locator) {
			return null;
		}
		String root = getRootID();
		return (null == root || root == "") ? Utils.toXPath(locator) : Utils
		        .concateXPath(root, locator);
	}

	private String getRootID() {
		if (XPathBuilderBehaviour.GLOBAL == getRoadBuilderType()) {
			return null;
		}
		IWIRoad r = road;
		while (null != r) {
			if (r instanceof WIElement
			        && (XPathBuilderBehaviour.SKIP != ((WIElement) r)
			                .getRoadBuilderType())) {
				return ((WIElement) r).getGlobalID();
			}
			r = r.getParent();
		}
		return null;
	}

	public String getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getType() + " \""
		        + (null == getName() ? getLocalID() : getName()) + "\"";
	}

	public boolean isVisible() {
		// Don't use "return isElementPresent && isVisaible" since
		// isVisible throws an exception if element isn't present
		// and compiler/vm can execute both conditions
		if (getBrowser().isElementPresent(getGlobalID())) {
			// For slow connection element can disappear after
			// isElementPresent call so isVisible will throw
			// SeleniumException
			try {
				return getBrowser().isVisible(getGlobalID());
			} catch (SeleniumException e) {
				return false;
			}
		}
		return false;
	}

	public void assertVisible() {
		if (!isVisible()) {
			Assert.fail(TEST_EXPECTED_VISIBLE.getValue(this));
		}
	}

	public void assertInvisible() {
		if (isVisible()) {
			Assert.fail(TEST_EXPECTED_INVISIBLE.getValue(this));
		}
	}

	public void waitVisible() {
		Loggers.ui.info(LOG_ELEMENT_WAIT_VISIBLE.getValue(this));
		new Wait() {
			@Override
			public boolean until() {
				return isVisible();
			}
		}.wait(TEST_EXPECTED_VISIBLE.getValue(this));
		Loggers.ui.info(LOG_ELEMENT_VISIBLE.getValue(this));
	}

	public void waitInvisible() {
		Loggers.ui.info(LOG_ELEMENT_WAIT_INVISIBLE.getValue(this));
		new Wait() {
			@Override
			public boolean until() {
				return !isVisible();
			}
		}.wait(TEST_EXPECTED_INVISIBLE.getValue(this));
		Loggers.ui.info(LOG_ELEMENT_INVISIBLE.getValue(this));
	}

}
