package ru.odnoklassniki.tests.ui.api.common;

/**
 * Callback reference for web object to find out it in browser.
 *
 */
public interface IWIRoad extends IWIBrowerContext {

	/**
	 * Go to object in browser
	 */
	void go();

	/**
	 * Returns parent web object
	 * @return parent
	 */
	IWIRoad getParent();

}
